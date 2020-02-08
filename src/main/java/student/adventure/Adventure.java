package student.adventure;

import student.RoomExplorer;

import java.util.Scanner;

public class Adventure {


    public static void readInput() { /////Not sure what parameters should go in this method declaration
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        evaluateInput(input);

    }
    public static void evaluateInput(String input) {
        //Also is in charge of changing the state of the game (i.e. what room the user is "in")
        String inputAsLower = input.toLowerCase().trim();
        String direction = null;
        String[][] possibleDirections = RoomExplorer.getDirections();
        String currentRoom;

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
        System.out.println("You have found the ending room!");
        return false;
    }
}