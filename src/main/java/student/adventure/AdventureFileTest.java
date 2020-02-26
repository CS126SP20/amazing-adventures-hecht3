package student.adventure;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import student.AdventureFile;

import java.io.File;

public class AdventureFileTest {
  private ObjectMapper mapper;
  private Adventure adventure;
  private RoomExplorer explorer;

  @Test
  public void validDefaultJSONAsFilePath() {
    String[] args = new String[1];
    args[0] = "src/main/resources/siebel.json";
    File defaultFile = new File("src/main/resources/twoRoomJSON.json");
    assertEquals(new File("src/main/resources/siebel.json"),
            AdventureFile.validate(args, defaultFile));
  }

  @Test
  public void validDefaultJSONAsURL() {
    String[] args = new String[1];
    args[0] = "https://courses.grainger.illinois.edu/cs126/sp2020/resources/siebel.json";
    File defaultFile = new File("src/main/resources/twoRoomJSON.json");
    assertEquals(new File("src/main/resources/userFile.json"),
            AdventureFile.validate(args, defaultFile));
  }

  @Test
  public void validNonDefaultJSONAsURL() {
    String[] args = new String[1];
    args[0] = "https://pastebin.com/raw/1aS9p7h9";
    File defaultFile = new File("src/main/resources/twoRoomJSON.json");
    assertEquals(new File("src/main/resources/userFile.json"),
            AdventureFile.validate(args, defaultFile));
  }

  @Test
  public void validNonDefaultJSONAsFilePath() {
    String[] args = new String[1];
    args[0] = "src/main/resources/fourRoomJSON.json";
    File defaultFile = new File("src/main/resources/twoRoomJSON.json");
    assertEquals(new File("src/main/resources/fourRoomJSON.json"),
            AdventureFile.validate(args, defaultFile));
  }

  @Test
  public void invalidNonDefaultJSONAsURL() {
    String[] args = new String[1];
    args[0] = "https://pastebin.com/raw/1aS9p7";
    File defaultFile = new File("src/main/resources/twoRoomJSON.json");
    assertEquals(defaultFile,
            AdventureFile.validate(args, defaultFile));
  }

  @Test
  public void invalidNonDefaultJSONAsFilePath() {
    String[] args = new String[1];
    args[0] = "src/main/resources/";
    File defaultFile = new File("src/main/resources/twoRoomJSON.json");
    assertEquals(defaultFile,
            AdventureFile.validate(args, defaultFile));
  }
}
