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
  /** The URL, if any, passed through the command line */
  static URL adventureURL;
  /** The file, if any, passed through the command line */
  static File adventureFile;
  /** The default JSON, used in the case that the user provides no JSON file or URL */
  static File defaultFile;

  /**
   * The initial application method.
   *
   * @param args the command-line arguments for the game
   */
  public static void main(String[] args) {
    defaultFile = new File("src/main/resources/siebel.json");
    ObjectMapper mapper = new ObjectMapper();
    if (args.length != 0) {
      try {
        adventureURL = new URL(args[0]);
        // If the URL is not a valid URL then an exception will be thrown and the program will jump
        // to the catch block. Additionally, if the URL does not contain a file an exception will
        // be thrown.
        mapper.readValue(adventureURL, RoomExplorer.class);
        adventureFile = new File("src/main/resources/userFile.json");
        FileUtils.copyURLToFile(adventureURL, adventureFile);
        if (!adventureFile.isFile()) {
          throw new Exception("URL did not contain valid file");
        } else {
          System.out.println(
              "You have provided a URL for a valid JSON. "
                  + "\nPlease press enter to continue. Enjoy your game!");
        }
      } catch (Exception e) {
        adventureFile = new File(args[0]);
        if (adventureFile.isFile()) {
          System.out.println(
              "You have provided a filepath for a valid JSON. "
                  + "\nPlease press enter to continue. Enjoy your game!");
        } else {
          System.out.println(
              "You have not provided a valid JSON. The default json will be used."
                  + "\nPlease press enter to continue");
          adventureFile = defaultFile;
        }
      }
    } else {
      adventureFile = defaultFile;
    }

    adventure = new Adventure(adventureFile);
    adventure.readInput(null);
  }
}
