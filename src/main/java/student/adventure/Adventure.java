package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Contains the information and logic for a new adventure. Handles taking user input and deciding
 * what to do with user commands.
 */
public class Adventure {
  /** The Jackson JSON parser */
  ObjectMapper mapper;
  /** The instance of the JSON wrapper class */
  RoomExplorer explorer;
  /** The rooms for the given JSON */
  List<Room> rooms;
  public boolean isEndOfGame;
  /** The constants that represent the user's command on an item */
  public static final int REMOVE = 0;
  public static final int ADD = 1;

  /**
   * Constructor for the Adventure class.
   * An invalid file will never be passed to this constructor because of the try-catch
   * logic in main, but the exception must still be checked.
   *
   * @param   file      the file to be parsed for this instance of the class
   * @throws  Exception if the file is invalid
  */
  public Adventure(File file) {
    mapper = new ObjectMapper();
    try {
      explorer = mapper.readValue(file, RoomExplorer.class);
      rooms = explorer.getRooms();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * In charge of taking user input and passing it to evaluateInput().
   * @param   currentRoom the room the user is currently in
   */
  public void readInput(Room currentRoom) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    currentRoom = evaluateInput(currentRoom, input);
    if (!isEndOfGame) {
      readInput(currentRoom);
    }
    System.exit(0);
  }

  /**
   * The main method for deciding what to do with a user input. Calls on each helper method.
   * @param   currentRoom the room the user is currently in
   * @param   input the user's input as a String
   */
  Room evaluateInput(Room currentRoom, String input) {

    // Standardize the input
    String standardizedInput = standardizeInput(input);
    if (standardizedInput.equals("exit") || standardizedInput.equals("quit")) {
      System.exit(0);
    }

    // Make sure that our currentRoom exists or is initialized to startingRoom
    currentRoom = checkForStartingRoom(currentRoom);
    String direction = null;
    String item = null;
    Room newRoom;

    // Extra directions in case the user is confused. Is also executed when the user presses enter.
    if (input.isEmpty()) {
      System.out.println(
          "Please input a direction to move by typing 'go' "
              + "followed by the direction you want to move, or just type the direction." +
                  "\n'examine', 'add [item]', and 'remove [item]' are also valid commands.\n");
      System.out.println(getRoomInfo(currentRoom));
      return currentRoom;
    }

    // A try catch block to ensure the user has provided valid input. Fails if the input is invalid.
    // Also checks for the various valid commands that the user can provide and acts on them.
    try {
      for (Directions d : currentRoom.getDirections()) {
        if (d.getDirectionName().toLowerCase().equals(standardizedInput)) {
          direction = d.getDirectionName();
        }
      }
      if (input.toLowerCase().contains("go ") && direction == null) {
        System.out.println("I can't '" + input + "'");
        return currentRoom;
      } else if (input.toLowerCase().contains("remove ")) {
        editItems(currentRoom, REMOVE, standardizedInput);
        return currentRoom;
      } else if (input.toLowerCase().contains("add ")) {
        editItems(currentRoom, ADD, standardizedInput);
        return currentRoom;
      } else if (input.toLowerCase().contains("teleport ")) {
        currentRoom = teleportUser(currentRoom, standardizedInput);
        checkEndOfGame(currentRoom);
        return currentRoom;
      } else if (standardizedInput.contains("examine")
              && standardizedInput.length() == "examine".length()) {
        System.out.println(getRoomInfo(currentRoom));
        return currentRoom;
      } else if (direction == null) {
        throw new Exception("Input not valid");
      }
      newRoom = changeRoom(currentRoom, direction);
      checkEndOfGame(newRoom);
      if (!isEndOfGame) {
        System.out.println(getRoomInfo(newRoom));
      }
      return newRoom;
    } catch (Exception e) {
      if (!isDeadEnd(currentRoom)) {
        System.out.println("I don't understand '" + input + "'");
      }
      return currentRoom;
    }
  }

  Room teleportUser(Room CurrentRoom, String standardizedInput) {
    for(Room room : rooms) {
      if (room.getName().toLowerCase().equals(standardizedInput)) {
        System.out.println("You have teleported to " + room.getName() + "!");
        return room;
      }
    }
    System.out.println("Room not found! You have not teleported.");
    return CurrentRoom;
  }

  /**
   * Makes sure that the startingRoom is where the user actually starts.
   * @param   currentRoom the room the user is currently in
   * @return  currentRoom if the starting room was not passed in. Otherwise return starting room
   */
  Room checkForStartingRoom(Room currentRoom) {
    if (currentRoom == null) {
      for (Room r : rooms) {
        if (explorer.getStartingRoom().equals(r.getName())) {
          return r;
        }
      }
    }
    return currentRoom;
  }

  /**
   * Changes the room in the given direction.
   * @param   currentRoom the room the user is currently in
   * @param   direction   the direction to move in
   * @return  null if there is no room in the given direction, otherwise the new room.
   */
  public Room changeRoom(Room currentRoom, String direction) {
    for (Directions d : currentRoom.getDirections()) {
      if (d.getDirectionName().equals(direction)) {
        String newRoomName = d.getRoom();
        for (Room r : rooms) {
          if (newRoomName.equals(r.getName())) {
            return r;
          }
        }
      }
    }
    return null;
  }

  /**
   * Changes the room in the given direction.
   * @param   currentRoom the room the user is currently in
   * @return  true if the user is in a dead-end room with no way out, false if there is a way out
   */
  boolean isDeadEnd(Room currentRoom) {
    if (!currentRoom.getDirections().isEmpty()) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Checks to see if the user is in the end room. Changes the class variable isEndOfGame to order
   * a game exit from readInput().
   * @param   currentRoom the room the user is currently in
   * @return  true if the user is in the end room, false if they are not
   */
  boolean checkEndOfGame(Room currentRoom) {
    if (currentRoom.getName().equals(explorer.getEndingRoom())) {
      System.out.println("You have found the ending room!");
      System.out.print("The ending room was: " + currentRoom.getName());
      isEndOfGame = true;
      return true;
    } else {
      isEndOfGame = false;
      return false;
    }
  }

  /**
   * Standardizes the user input so that it can be checked against the directions for equality in
   * evaluateInput(). Also checks for an exit command from the user.
   * @param   input the user input
   * @return  the input standardized as lowercase and without leading or trailing spaces
   */
  String standardizeInput(String input) {
    String inputAsLowerTrimmed = input.toLowerCase().trim();
    if (inputAsLowerTrimmed.indexOf("go") == 0 || inputAsLowerTrimmed.indexOf("remove") == 0 ||
          inputAsLowerTrimmed.indexOf("add") == 0 || inputAsLowerTrimmed.indexOf("teleport") == 0) {
      String lowercaseStandardizedInput =
          inputAsLowerTrimmed.substring(inputAsLowerTrimmed.indexOf(" ") + 1);
      return lowercaseStandardizedInput;
    } else if (inputAsLowerTrimmed.equals("exit") || inputAsLowerTrimmed.equals("quit")) {
      System.exit(0);
      // Return doesn't matter here as the game is over.
    }
    return inputAsLowerTrimmed;
  }

  /**
   * Nicely formats the directions so they are output according to the CS126 website documentation.
   * Also calls on isDeadEnd() to check for a dead-end room and exits game if it is.
   * @param   currentRoom the room the user is currently in
   * @return  the directions as an easy to read String
   */
  String directionsAsString(Room currentRoom) {
    int directionsSize = currentRoom.getDirections().size();
    StringBuilder toReturn = new StringBuilder();
    // Unfortunately, there is no good way that I could think of to cleanly format the string so
    // that the output reads "direction1, direction2, or direction3." This was the best I could do:
    if (!isDeadEnd(currentRoom) && directionsSize > 0) {
      for (int i = 0; i < directionsSize; i++) {
        String directionName = currentRoom.getDirections().get(i).getDirectionName();
        if (directionsSize > 1 && i == directionsSize - 1) {
          toReturn.append("or " + directionName);
        } else if (i == directionsSize - 1) {
          toReturn.append(directionName);
        } else if (directionsSize != 2) {
          toReturn.append(directionName + ", ");
        } else {
          toReturn.append(directionName + " ");
        }
      }
    } else {
      System.out.println("You have reached a dead end and are stuck!");
      isEndOfGame = true;
      System.exit(0);
    }
    return toReturn.toString();
  }

  /**
   * Nicely formats the items into a readable list.
   * @param   currentRoom the room the user is currently in
   * @return  the items as an easy to read String
   */
  String getItemsAsString(Room currentRoom) {
    StringBuilder toReturn = new StringBuilder();
    int itemsLength = currentRoom.getItems().size();
    List<String> items = currentRoom.getItems();

    if (itemsLength == 0) {
      toReturn.append("There are no items visible");
    } else {
      toReturn.append("Items visible:");
      for (int i = 0; i < itemsLength; i++) {
        toReturn.append(" " + items.get(i));
        if (i != items.size() - 1) {
          toReturn.append(",");
        }
      }
    }
    return toReturn.toString();
  }

  /**
   * Gives the String that has all of the Room information in it
   * @param   currentRoom the room the user is currently in
   * @return  the String with the Room information for the user to read
   */
  String getRoomInfo(Room currentRoom) {
    return currentRoom.getDescription() + "\nFrom here, you can go: "
            + directionsAsString(currentRoom) + "\n" + getItemsAsString(currentRoom);
  }

  /**
   * Changes the items list depending on what the user's command. Calls on some basic helper methods
   * in Room.java.
   * @param   currentRoom the room the user is currently in
   * @param   action an integer representing the action the user wishes to take on the item
   * @param   item the item the user wishes to add or remove
   */
  public void editItems(Room currentRoom, int action, String item) {
    if (action == REMOVE) {
      if (currentRoom.getItems().contains(item)) {
        currentRoom.removeItem(item);
        System.out.println(item + " removed");
      } else {
        System.out.println("Room does not contain '" + item + "'");
      }
    } else if (action == ADD) {
      if (!currentRoom.getItems().contains(item)) {
        currentRoom.addItem(item);
        System.out.println(item + " added");
      } else {
        System.out.println("Room already contains " + item);
      }
    }
  }

}
