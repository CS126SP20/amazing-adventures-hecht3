package student;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.net.URL;

import org.glassfish.grizzly.http.server.HttpServer;
import student.adventure.Adventure;
import org.apache.commons.io.FileUtils;
import student.adventure.RoomExplorer;

public class Main {
  /** The adventure that the user will interact with */
  static Adventure adventure;
  /** The default JSON, used in the case that the user provides no JSON file or URL */
  static File defaultFile;
  /** The file, if any, passed through the command line */
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
