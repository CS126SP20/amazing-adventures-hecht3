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

    public Adventure(File defaultFile) {
        this.mapper = new ObjectMapper();
        try {
            this.explorer = mapper.readValue(defaultFile, RoomExplorer.class);
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
        String inputAsLower = input.toLowerCase().trim();
        if (inputAsLower.equals("exit") || inputAsLower.equals("quit")) {
            System.exit(0);
        }
        String direction = null;
        Room newRoom = null;

        // We don't know what order the directionName and room will be in in the given json
        // so we need a nested for loop for each dimension
        ArrayList<Directions> directions = currentRoom.getDirections();
        try {
            direction = directions.get(directions.indexOf(inputAsLower)).getDirectionName();
            newRoom = changeRoom(currentRoom, direction);
        } catch (Exception e) {
            if (!isDeadEnd(currentRoom)) {
                System.out.println("I don't understand '" + input + "'");
            } else {
                System.out.println("You have reached a dead end! Your game has ended.");
                System.exit(0);
            }
        }
        System.out.println(newRoom.getDescription());


        ///// Be sure to evaluate the input and check for the end of the game before you print the
        ///// possible directions
        String toPrint = "";
        boolean isEndingRoom = checkEndOfGame(currentRoom, direction);
        if (isEndingRoom) {
            System.exit(0);
        }
    }
    public Room changeRoom(Room currentRoom, String direction) {
        List<Room> rooms = explorer.getRooms();
        if (currentRoom == null) {
            for (Room r : rooms) {
                if (explorer.getStartingRoom().equals(r.getName())) {
                    return r;
                }
            }
        } else {
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
        }
        //Room newRoom = currentRoom.getDirectionsAsHashMap().get(direction).getRoom();
        return null;
    }
    public boolean isDeadEnd(Room currentRoom) {
        if (currentRoom.getDirections().size() == 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkEndOfGame(Room currentRoom, String direction) {
        if (currentRoom.getName().equals(explorer.getEndingRoom())) {
            System.out.println("You have found the ending room!");
            return true;
        } else {
            return false;
        }
    }
}