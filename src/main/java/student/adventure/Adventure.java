package student.adventure;

import java.util.Scanner;

public class Adventure {
    public void displayMessage(String message) {

    }
    public void readInput() { /////Not sure what parameters should go in this method declaration
        Scanner scanner = new Scanner(System.in);
        String directionToMove = scanner.nextLine();

    }
    public void evaluateInput(String input) {
        //Also is in charge of changing the state of the game (i.e. what room the user is "in")
    }
    public boolean isEndOfGame(String currentRoom) {
        return false;
    }
}