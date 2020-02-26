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
import java.util.Arrays;

public class AdventureTest {
  private ObjectMapper mapper;
  private Adventure adventure;
  private RoomExplorer explorer;

  @Rule public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
  @Rule public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  @Before
  public void setUp() {
    File file = new File("src/main/resources/siebel.json");
    this.mapper = new ObjectMapper();
    adventure = new Adventure(file);
    try {
      this.explorer = mapper.readValue(file, RoomExplorer.class);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // The following are tests using the standard siebel.json file.

  // The helper function tests
  // readInput does not need to be tested because it is trivial

  // evaluateInput tests
  @Test
  public void invalidString() {
    adventure.evaluateInput(null, "Hello");
    assertEquals("I don't understand 'Hello'\n", systemOutRule.getLog());
  }

  @Test
  public void evaluateInputInvalidStringNumber() {
    adventure.evaluateInput(null, "5");
    assertEquals("I don't understand '5'\n", systemOutRule.getLog());
  }

  @Test
  public void evaluateInputInvalidStringCapitalization() {
    adventure.evaluateInput(null, "hELlo");
    assertEquals("I don't understand 'hELlo'\n", systemOutRule.getLog());
  }

  @Test
  public void initialGoEastExtraLength() {
    adventure.evaluateInput(null, "go East and then go NORth");
    assertEquals("I can't 'go East and then go NORth'\n", systemOutRule.getLog());
  }

  @Test
  public void initialGoEast() {
    adventure.evaluateInput(explorer.getRooms().get(0), "go east");
    assertEquals(
        "You are in the west entry of Siebel Center. "
            + "You can see the elevator, the ACM office, and hallways to the north and east.\n"
            + "From here, you can go: West, Northeast, North, or East\n"
            + "Items visible: sweatshirt, key\n",
        systemOutRule.getLog());
  }

  @Test
  public void initialGoEastCapitalization() {
    adventure.evaluateInput(explorer.getRooms().get(0), "gO EaSt");
    assertEquals(
        "You are in the west entry of Siebel Center. "
            + "You can see the elevator, the ACM office, and hallways to the north and east.\n"
            + "From here, you can go: West, Northeast, North, or East\n"
            + "Items visible: sweatshirt, key\n",
        systemOutRule.getLog());
  }

  @Test
  public void initialGoEastExtraLengthSpaces() {
    adventure.evaluateInput(explorer.getRooms().get(0), "   go EasT     ");
    assertEquals(
        "You are in the west entry of Siebel Center. "
            + "You can see the elevator, the ACM office, and hallways to the north and east.\n"
            + "From here, you can go: West, Northeast, North, or East\n"
            + "Items visible: sweatshirt, key\n",
        systemOutRule.getLog());
  }
  @Test
  public void endingRoomEvaluateInput() {
    adventure.evaluateInput(explorer.getRooms().get(5), "go souTH  ");
    assertEquals("You have found the ending room!\n" + "The ending room was: " +
            explorer.getEndingRoom(), systemOutRule.getLog());
  }
  @Test
  public void exitGameInput() {
    exit.expectSystemExit();
    adventure.evaluateInput(null, "eXIt");
    assertEquals("You have decided to quit the game.\n", systemOutRule.getLog());
  }

  @Test
  public void quitGameInput() {
    exit.expectSystemExit();
    adventure.evaluateInput(null, "quIt");
    assertEquals("You have decided to quit the game.\n", systemOutRule.getLog());
  }

  // changeRoom tests
  // changeRoom will never receive a bad input if evaluateInput works properly
  @Test
  public void changeRoomInitial() {
    assertEquals("SiebelEntry", adventure.changeRoom(explorer.getRooms().get(0), "East").getName());
  }

  @Test
  public void changeRoomLater() {
    assertEquals(
        "SiebelNorthHallway", adventure.changeRoom(explorer.getRooms().get(1), "North").getName());
  }

  @Test
  public void changeRoomToEnd() {
    assertEquals("Siebel1314", adventure.changeRoom(explorer.getRooms().get(5), "South").getName());
  }

  // isEndOfGame tests
  @Test
  public void checkEndOfGameEndingRoom() {
    assertTrue(adventure.checkEndOfGame(explorer.getRooms().get(explorer.getRooms().size() - 2)));
  }

  @Test
  public void checkEndOfGameStartingRoom() {
    assertFalse(adventure.checkEndOfGame(explorer.getRooms().get(0)));
  }

  @Test
  public void checkEndOfGameIntermediateRoom() {
    assertFalse(adventure.checkEndOfGame(explorer.getRooms().get(4)));
  }

  // checkStartingRoomTests
  @Test
  public void checkStartingRoomIsStarting() {
    assertEquals(
        adventure.checkForStartingRoom(null).getName(), explorer.getRooms().get(0).getName());
  }

  @Test
  public void checkStartingRoomNotStarting() {
    assertEquals(
        adventure.checkForStartingRoom(explorer.getRooms().get(1)).getName(),
        explorer.getRooms().get(1).getName());
  }

  // standardizeInput tests
  @Test
  public void standardizeInputWithGo() {
    assertEquals("north", adventure.standardizeInput("go NoRth   "));
  }

  @Test
  public void standardizeInputWithNoActionWord() {
    assertEquals("north", adventure.standardizeInput("NoRth   "));
  }

  // directionsAsString tests
  @Test
  public void directionsAsStringFourDirections() {
    assertEquals(
        "West, Northeast, North, or East",
        adventure.getDirectionsAsString(explorer.getRooms().get(1)));
  }

  @Test
  public void directionsAsStringTwoDirections() {
    assertEquals("South or NorthEast", adventure.getDirectionsAsString(explorer.getRooms().get(3)));
  }

  @Test
  public void directionsAsStringOneDirection() {
    assertEquals("East", adventure.getDirectionsAsString(explorer.getRooms().get(0)));
  }

  // Tests for parsing JSON
  @Test
  public void checkStartingRoom() {
    assertEquals(explorer.getStartingRoom(), "MatthewsStreet");
  }

  @Test
  public void checkEndingRoom() {
    assertEquals(explorer.getEndingRoom(), "Siebel1314");
  }

  @Test
  public void getRoomName() {
    assertEquals(explorer.getRooms().get(4).getName(), "Siebel1112");
  }

  @Test
  public void getRoomDescription() {
    assertEquals(
        explorer.getRooms().get(4).getDescription(),
        "You are in Siebel 1112.  There is space for two code reviews in this room.");
  }

  @Test
  public void getDirectionName() {
    assertEquals(explorer.getRooms().get(4).getDirections().get(0).getDirectionName(), "West");
  }

  @Test
  public void getRoomNameInDirection() {
    assertEquals(explorer.getRooms().get(4).getDirections().get(0).getRoom(), "SiebelNorthHallway");
  }

  // editItems tests
  @Test
  public void editItemsRemoveSingleWord() {
    Room currentRoom = explorer.getRooms().get(0);
    adventure.editItems(currentRoom, Adventure.REMOVE, "coin");
    assertEquals(0, currentRoom.getItems().size());
  }

  @Test
  public void editItemsRemoveMultiWord() {
    Room currentRoom = explorer.getRooms().get(4);
    adventure.editItems(currentRoom, Adventure.REMOVE, "USB-C connector");
    assertEquals(Arrays.asList("grading rubric"), currentRoom.getItems());
  }

  @Test
  public void editItemsRemoveDoesNotContain() {
    Room currentRoom = explorer.getRooms().get(4);
    adventure.editItems(currentRoom, Adventure.REMOVE, "car");
    assertEquals(Arrays.asList("USB-C connector", "grading rubric"), currentRoom.getItems());
  }

  @Test
  public void editItemsAddSingleWord() {
    Room currentRoom = explorer.getRooms().get(4);
    adventure.editItems(currentRoom, Adventure.ADD, "spaceship");
    assertEquals(Arrays.asList("USB-C connector", "grading rubric", "spaceship"),
            currentRoom.getItems());
  }

  @Test
  public void editItemsAddMultiWord() {
    Room currentRoom = explorer.getRooms().get(4);
    adventure.editItems(currentRoom, Adventure.ADD, "space ship");
    assertEquals(Arrays.asList("USB-C connector", "grading rubric", "space ship"),
            currentRoom.getItems());
  }

  @Test
  public void editItemsAlreadyContains() {
    Room currentRoom = explorer.getRooms().get(4);
    adventure.editItems(currentRoom, Adventure.ADD, "grading rubric");
    assertEquals(Arrays.asList("USB-C connector", "grading rubric"),
            currentRoom.getItems());
  }

  @Test
  public void inspectItemEmpty() {
    Room currentRoom = explorer.getRooms().get(4);
    assertEquals(currentRoom, adventure.inspectItem(currentRoom, ""));
    assertEquals("There is no such item in this room.\n", systemOutRule.getLog());
  }

  @Test
  public void inspectItemNotTeleportationItem() {
    String itemNotTeleportable = adventure.teleportationItems.get(0);
    int i = 0;
    while (adventure.teleportationItems.contains(itemNotTeleportable)) {
      itemNotTeleportable = adventure.allItems.get(i);
      i++;
    }
    Room currentRoom = null;
    for (Room r : adventure.rooms) {
      if (r.getItems().contains(itemNotTeleportable)) {
        currentRoom = r;
        break;
      }
    }
    assertEquals(currentRoom, adventure.inspectItem(currentRoom, itemNotTeleportable));
    assertEquals("There is nothing special about this item.\n", systemOutRule.getLog());
  }

  @Test
  public void inspectItemIsTeleportationItem() {
    String itemTeleportable = adventure.teleportationItems.get(0);
    Room currentRoom = null;
    for (Room r : adventure.rooms) {
      r.getItems();
      if (r.getItems().contains(itemTeleportable)) {
        currentRoom = r;
        break;
      }
    }
    assertNotEquals(currentRoom, adventure.inspectItem(currentRoom, itemTeleportable.toLowerCase()));
    assertEquals("You have teleported to ",
            systemOutRule.getLog().substring(0, "You have teleported to ".length()));
  }

}
