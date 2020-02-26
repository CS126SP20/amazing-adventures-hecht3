package student;

import java.io.File;
import student.adventure.Adventure;

public class Main {
  /** The adventure that the user will interact with */
  static Adventure adventure;
  /** The default JSON, used in the case that the user provides no JSON file or URL */
  static File defaultFile;
  /** The file, if any, passed through the command line. Is changed to default if invalid */
  static File adventureFile;

  /**
   * The initial application method.
   *
   * @param args the command-line arguments for the game
   */
  public static void main(String[] args) {
    defaultFile = new File("src/main/resources/siebel.json");
    adventureFile = AdventureFile.validate(args, defaultFile);
    adventure = new Adventure(adventureFile);
    adventure.readInput(null);
  }
}
