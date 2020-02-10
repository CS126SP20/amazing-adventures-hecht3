package student.adventure;

import student.RoomExplorer;

import java.util.Map;
import java.util.Scanner;

public class Adventure {


    public static void readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        evaluateInput(input);
    }

    public static void evaluateInput(String input) {
        // Standardize the input
        String inputAsLower = input.toLowerCase().trim();
        String direction = null;
        RoomExplorer.Room currentRoom = null;
        // We don't know what order the directionName and room will be in in the given json
        // so we need a nested for loop for each dimension
        Map<RoomExplorer.Directions, RoomExplorer.Directions> directionsAsHashMap
                = RoomExplorer.Room.getDirectionsAsHashMap();
        try {
            direction = directionsAsHashMap.get(inputAsLower).getDirectionName();
        } catch (Exception e) {
            if (isDeadEnd(currentRoom, direction) == false) {
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
        if (isEndingRoom == true) {
            System.exit(0);
        }
    }
    public static String changeRoom(RoomExplorer.Room currentRoom, String direction) {
        if (currentRoom == null) {
            currentRoom = RoomExplorer.getStartingRoom();
        }
        RoomExplorer.Room newRoom = currentRoom.getDirectionsAsHashMap().get(direction).getRoom();

    }
    public static boolean isDeadEnd(String currentRoom) {
        return false;
    }
    public static boolean checkEndOfGame(String currentRoom, String direction) {
        if (currentRoom.equals(RoomExplorer.getEndingRoom())) {
            System.out.println("You have found the ending room!");
            return true;
        } else {
            return false;
        }
    }
}