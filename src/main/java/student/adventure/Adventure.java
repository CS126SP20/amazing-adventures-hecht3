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

    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        evaluateInput(input);
    }

    public void evaluateInput(String input) {
        // Standardize the input
        String inputAsLower = input.toLowerCase().trim();
        String direction = null;
        Room currentRoom = null;
        // We don't know what order the directionName and room will be in in the given json
        // so we need a nested for loop for each dimension
        ArrayList<Directions> directions = currentRoom.getDirections();
        try {
            direction = directions.get(directions.indexOf(inputAsLower)).getDirectionName();
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
        String toPrint = "";
        boolean isEndingRoom = checkEndOfGame(currentRoom, direction);
        if (isEndingRoom) {
            System.exit(0);
        }
    }
    public Room changeRoom(Room currentRoom, String direction) {
        if (currentRoom == null) {
            List<Room> rooms = explorer.getRooms();
            for (Room r : rooms) {
                if (explorer.getStartingRoom().equals(r.getName())) {
                    currentRoom = r;
                    break;
                }
            }
        }
        //Room newRoom = currentRoom.getDirectionsAsHashMap().get(direction).getRoom();
        return null;
    }
    public boolean isDeadEnd(Room currentRoom) {
        return false;
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