package student.adventure;

import student.RoomExplorer;

import java.util.Scanner;

public class Adventure {


    public static void readInput() { ///// Not sure what parameters should go in this method declaration
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        evaluateInput(input);

    }
    public static void evaluateInput(String input) {
        // Also is in charge of changing the state of the game (i.e. what room the user is "in")
        String inputAsLower = input.toLowerCase().trim();
        String direction = null;
        RoomExplorer.Directions[][] possibleDirections = RoomExplorer.Directions.getDirectionName();
        String currentRoom;
        // We don't know what order the directionName and room will be in in the given json
        // so we need a nested for loop for each dimension
        for (int i = 0; i < possibleDirections.length; i++) {
            if (possibleDirections[i].toLowerCase().equals(inputAsLower)) {
                direction = possibleDirections[i];
            }
        }

        if (direction != null) {
            newRoom = changeRoom(currentRoom, direction);
        } else {
            System.out.println("I don't understand '" + input + "'");
            //readInput();
        }

        String toPrint = "";
        //isEndOfGame(currentRoom);
    }
    public static String changeRoom(String currentRoom, String direction) {
        String currentRoom = "";
        return currentRoom;
    }
    public static boolean checkEndOfGame(String currentRoom) {
        if (currentRoom.equals(RoomExplorer.getEndingRoom())) {
            System.out.println("You have found the ending room!");
            return true;
        } else {
            return false;
        }
    }
}