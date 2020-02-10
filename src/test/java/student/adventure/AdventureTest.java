package student.adventure;

import static org.junit.Assert.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import student.Room;
import student.RoomExplorer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;


public class AdventureTest {
    private ObjectMapper mapper;
    private Adventure adventure;
    private RoomExplorer explorer;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

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

    //The following are tests using the standard siebel.json file.

    //The helper function tests
    //readInput does not need to be tested because it is trivial

    //evaluateInput tests
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
        assertEquals("I don't understand 'go East and then go NORth'\n",
                systemOutRule.getLog());
    }
    @Test
    public void initialGoEast() {
        adventure.evaluateInput(explorer.getRooms().get(0), "go east");
        assertEquals("You are in the west entry of Siebel Center. " +
                "You can see the elevator, the ACM office, and hallways to the north and east.\n" +
                "From here, you can go: West, Northeast, North, or East\n", systemOutRule.getLog());
    }
    @Test
    public void initialGoEastCapitalization() {
        adventure.evaluateInput(explorer.getRooms().get(0), "gO EaSt");
        assertEquals("You are in the west entry of Siebel Center. " +
                "You can see the elevator, the ACM office, and hallways to the north and east.\n" +
                "From here, you can go: West, Northeast, North, or East\n", systemOutRule.getLog());
    }
    @Test
    public void initialGoEastExtraLengthSpaces() {
        adventure.evaluateInput(explorer.getRooms().get(0), "   go EasT     ");
        assertEquals("You are in the west entry of Siebel Center. " +
                "You can see the elevator, the ACM office, and hallways to the north and east.\n" +
                "From here, you can go: West, Northeast, North, or East\n", systemOutRule.getLog());
    }
    @Test
    public void exitGameInput() {
        adventure.evaluateInput(null, "eXIt");
        assertEquals("You have decided to quit the game.\n", systemOutRule.getLog());
    }
    @Test
    public void quitGameInput() {
        adventure.evaluateInput(null, "quIt");
        assertEquals("You have decided to quit the game.\n", systemOutRule.getLog());
    }

    //changeRoom tests
    //changeRoom will never receive a bad input if evaluateInput works properly
//    @Test
//    public void changeRoomInitial() {
//        assertEquals("testRoom",
//                adventure.changeRoom("MatthewsStreet", "East"));
//    }
//    @Test
//    public void changeRoomLater() {
//        assertEquals("SiebelNorthHallWay",
//                adventure.changeRoom("SiebelNorthHallway", "North"));
//    }
//    @Test
//    public void changeRoomToEnd() {
//        assertEquals("Siebel1314",
//                adventure.changeRoom("SiebelEastHallway", "South"));
//    }
//
//    //IsEndOfGame tests
//    @Test
//    public void checkEndOfGameEndingRoom() {
//        exit.expectSystemExit();
//        adventure.checkEndOfGame("SiebelEastHallway", "south");
//    }
//    @Test
//    public void checkEndOfGameStartingRoom() {
//        assertEquals(adventure.checkEndOfGame("MatthewsStreet", "south"), false);
//    }
//    @Test
//    public void checkEndOfGameIntermediateRoom() {
//        assertEquals(adventure.checkEndOfGame("AcmOffice", "north"), false);
//    }

    //Tests for parsing JSON
    @Test
    public void checkStartingRoom() {
        assertEquals( "MatthewsStreet", explorer.getStartingRoom());
    }
    @Test
    public void checkEndingRoom() {
        assertEquals( "Siebel1314", explorer.getEndingRoom());
    }
}