package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import student.Directions;
import student.Room;
import student.RoomExplorer;

public class Adventure {
    ObjectMapper mapper;
    RoomExplorer explorer;
    List<Room> rooms;
    boolean isEndOfGame;

    public Adventure(File defaultFile) {
        mapper = new ObjectMapper();
        try {
            explorer = mapper.readValue(defaultFile, RoomExplorer.class);
            rooms = explorer.getRooms();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readInput(Room currentRoom) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        currentRoom = evaluateInput(currentRoom, input);
        if (!isEndOfGame) {
            readInput(currentRoom);
        }
        System.exit(0);
    }

    public Room evaluateInput(Room currentRoom, String input) {
        // Standardize the input
        String standardizedInput = standardizeInput(input);
        if (standardizedInput.equals("exit") || standardizedInput.equals("quit")) {
            System.exit(0);
        }
        currentRoom = checkForStartingRoom(currentRoom);
        String direction = null;
        Room newRoom = null;
        if (input.length() == 0) {
            System.out.println("Please input a direction to move by typing 'go' " +
                    "followed by the direction you want to move.");
            System.out.println(currentRoom.getDescription() + "\nFrom here, you can go: "
                    + directionsAsString(currentRoom));
            return currentRoom;
        }
        ArrayList<Directions> directions = currentRoom.getDirections();
        try {
            for (int i = 0; i < directions.size(); i++) {
                if (directions.get(i).getDirectionName().toLowerCase().equals(standardizedInput)) {
                    direction = directions.get(i).getDirectionName();
                }
            }
            if (direction == null) {
                throw new Exception("Input not valid");
            }
            newRoom = changeRoom(currentRoom, direction);
            checkEndOfGame(newRoom);
            if (!isEndOfGame) {
                System.out.println(newRoom.getDescription() + "\nFrom here, you can go: "
                        + directionsAsString(newRoom));
            }
            return newRoom;
        } catch (Exception e) {
            if (!isDeadEnd(currentRoom)) {
                System.out.println("I don't understand '" + input + "'");

//                System.out.println(currentRoom.getDescription() + "\nFrom here, you can go: "
//                        + directionsAsString(newRoom));
            } else {
                System.out.println("You have reached a dead end! Your game has ended.");
                System.exit(0);
            }
            return currentRoom;
        }
        /// Be sure to evaluate the input and check for the end of the game before you print the
        /// possible directions
    }

    public Room checkForStartingRoom(Room currentRoom) {
        if (currentRoom == null) {
            for (Room r : rooms) {
                if (explorer.getStartingRoom().equals(r.getName())) {
                    return r;
                }
            }
        }
        return currentRoom;
    }

    public Room changeRoom(Room currentRoom, String direction) {
        List<Room> rooms = explorer.getRooms();
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
//Create javadoc comments
//Show start room for the user <------ create a function that displays the description.
//Be able to run tests without commenting out the readinput loop
//Verify that json is valid
//Test for alternate json
//Maybe reorganize
    public boolean isDeadEnd(Room currentRoom) {
        if (currentRoom.getDirections().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkEndOfGame(Room currentRoom) {
        if (currentRoom.getName().equals(explorer.getEndingRoom())) {
            System.out.println("You have found the ending room!");
            System.out.println("The ending room was: " + currentRoom.getName());
            isEndOfGame = true;
            return true;
        } else {
            isEndOfGame = false;
            return false;
        }
    }

    public String standardizeInput(String input) {
        String inputAsLowerTrimmed = input.toLowerCase().trim();
        if (inputAsLowerTrimmed.indexOf("go") == 0) {
            String lowercaseStandardizedInput =
                    inputAsLowerTrimmed.substring(inputAsLowerTrimmed.indexOf(" ") + 1);
            return lowercaseStandardizedInput;
        } else {
            return inputAsLowerTrimmed;
        }
    }

    public String directionsAsString(Room currentRoom) {
        String toReturn = "";
        int directionsSize = currentRoom.getDirections().size();
        if (!isDeadEnd(currentRoom)) {
            for (int i = 0; i < directionsSize; i++) {
                String directionName = currentRoom.getDirections().get(i).getDirectionName();
                if (directionsSize > 1 && i == directionsSize - 1) {
                    toReturn = toReturn + "or " + directionName;
                } else if (i == directionsSize - 1) {
                    toReturn = toReturn + directionName;
                } else if (directionsSize != 2) {
                    toReturn = toReturn + directionName + ", ";
                } else {
                    toReturn = toReturn + directionName + " ";
                }
            }
        } else {
            toReturn = "You are stuck!";
        }
        return toReturn;
    }
}