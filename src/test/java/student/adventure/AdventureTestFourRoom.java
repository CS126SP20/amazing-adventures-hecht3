package student.adventure;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
/**
 * https://stefanbirkner.github.io/system-rules/apidocs/org/junit/contrib/java/lang/system/SystemOutRule.html
 * The SystemOutRule API will be used for these tests instead of byteStream because it works with my
 * helper functions better.
 */
import java.io.File;

public class AdventureTestFourRoom {
  private ObjectMapper mapper;
  private Adventure adventure;
  private RoomExplorer explorer;

  @Rule public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
  @Rule public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  @Before
  public void setUp() {
    File file = new File("src/main/java/student/adventure/fourRoomJSON.json");
    this.mapper = new ObjectMapper();
    adventure = new Adventure(file);
    try {
      this.explorer = mapper.readValue(file, RoomExplorer.class);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // The helper functions will behave the same no matter the input except for isDeadEnd which hasn't
  // really been tested yet because there is no dead end in the default JSON. I will also test some
  // of evaluateInput just to make sure it is still working properly since it interacts with almost
  // all of the other functions.
  // I have also tested for some directions other than the standard "south, north" etc in this json.

  @Test
  public void initialGoSouth() {
    adventure.evaluateInput(explorer.getRooms().get(0), "go south");
    assertEquals(
            "You are in IntermediateRoom1\n"
                    + "From here, you can go: North, Upstairs, or Forwards\n",
            systemOutRule.getLog());
  }

  @Test
  public void intermediateRoomGoForwardsCapitalization() {
    adventure.evaluateInput(explorer.getRooms().get(1), "go noRTh    ");
    assertEquals(
            "You are in the starting room\n"
                    + "From here, you can go: West or South\n",
            systemOutRule.getLog());
  }

  @Test
  public void isDeadEndTest() {
    assertTrue(adventure.isDeadEnd(explorer.getRooms().get(2)));
  }

  @Test
  public void isNotDeadEndTest() {
    assertFalse(adventure.isDeadEnd(explorer.getRooms().get(1)));
  }


  @Test
  public void endingRoomEvaluateInput() {
    adventure.evaluateInput(explorer.getRooms().get(1), "go forWarDS  ");
    assertEquals("You have found the ending room!\n" + "The ending room was: " +
            explorer.getEndingRoom(), systemOutRule.getLog());
  }
}
