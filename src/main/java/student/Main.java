package student;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Scanner;
import student.adventure.Adventure;

public class Main {
  static File defaultFile;
  static Adventure adventure;
  static File userFile;

  /**
   * The intial application method.
   *
   * @param args the command-line arguments for the game
   */
  public static void main(String[] args) {
    defaultFile = new File("src/main/resources/siebel.json");

    // Initial directions for the user to provide a JSON
    System.out.println(
        "Please input a filepath with a valid JSON for your adventure game.\n"
            + "If you do not provide a valid file, the default file for siebel will be used.");

    // Request user input
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    userFile = new File(input);
    ObjectMapper mapper = new ObjectMapper();

    // Attempt to parse the user input as a JSON. If this fails, the default JSON is used instead
    // in the catch block.
    try {
      mapper.readValue(userFile, RoomExplorer.class);
      System.out.println("You have provided a valid JSON. "
              + "\nPlease press enter to continue. Enjoy your game!");
      adventure = new Adventure(userFile);
      adventure.readInput(null);
    } catch (Exception e) {
      // Another try-catch block is necessary because readValue could still throw an error
      try {
        System.out.println("You have not provided a valid JSON. The default json will be used."
                + "\nPlease press enter to continue");
        mapper.readValue(defaultFile, RoomExplorer.class);
        adventure = new Adventure(defaultFile);
        adventure.readInput(null);
      } catch (Exception exception) {
        System.out.println(exception);
      }
    }
  }
}
