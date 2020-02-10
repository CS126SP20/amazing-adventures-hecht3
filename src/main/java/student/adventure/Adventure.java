package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import student.Directions;
import student.Room;
import student.RoomExplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Adventure {
    ObjectMapper mapper;
    RoomExplorer explorer;
    List<Room> rooms;

    public Adventure(File defaultFile) {
        this.mapper = new ObjectMapper();
        try {
            this.explorer = mapper.readValue(defaultFile, RoomExplorer.class);
            this.rooms = explorer.getRooms();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readInput(Room currentRoom) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        evaluateInput(currentRoom, input);
    }

    public void evaluateInput(Room currentRoom, String input) {
        // Standardize the input
        String standardizedInput = standardizeInput(input);
        if (standardizedInput.equals("exit") || standardizedInput.equals("quit")) {
            System.exit(0);
        }
        currentRoom = checkStartingRoom(currentRoom);
        String direction = null;
        Room newRoom;

        ArrayList<Directions> directions = currentRoom.getDirections();
        try {
            for (int i = 0; i < directions.size(); i++) {
                if (directions.get(i).getDirectionName().equals(standardizedInput)) {
                    direction = standardizedInput;
                }
            }
            if (direction == null) {
                throw new Exception("Input not valid");
            }
            newRoom = changeRoom(currentRoom, direction);
            if (checkEndOfGame(newRoom)) {
                System.exit(0);
            }
            System.out.println(newRoom.getDescription() + "\nFrom here, you can go: "
                                                        + directionsAsString(newRoom));
        } catch (Exception e) {
            if (!isDeadEnd(currentRoom)) {
                System.out.println("I don't understand '" + input + "'");
            } else {
                System.out.println("You have reached a dead end! Your game has ended.");
                System.exit(0);
            }
        }


        ///// Be sure to evaluate the input and check for the end of the game before you print the
        ///// possible directions
    }
    public Room checkStartingRoom(Room currentRoom) {
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
            return true;
        } else {
            return false;
        }
    }
    public String standardizeInput(String input) {
        String inputAsLowerTrimmed = input.toLowerCase().trim();
        if (inputAsLowerTrimmed.indexOf("go") == 0) {
            String lowercaseStandardizedInput =
                    inputAsLowerTrimmed.substring(inputAsLowerTrimmed.indexOf(" ") + 1);
            String standardizedInput = lowercaseStandardizedInput.substring(0, 1).toUpperCase()
                                       + lowercaseStandardizedInput.substring(1);
            return standardizedInput;
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
                } else {
                    toReturn = toReturn + directionName + ", ";
                }
            }
        } else {
            toReturn = "You are stuck!";
        }
        return toReturn;
    }
}