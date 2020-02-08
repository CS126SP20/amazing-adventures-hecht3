package student.adventure;

import static org.junit.Assert.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class AdventureTest {
    private ObjectMapper mapper;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

    //The following are tests using the standard siebel.json file

    //The helper function tests
    //readInput does not need to be tested because it is trivial

    @Test
    public void evaluateInputInvalidString() {
        Adventure.evaluateInput("Hello");
        assertEquals("I don't understand 'Hello'\n", systemOutRule.getLog());
    }
    @Test
    public void evaluateInputInvalidStringNumber() {
        Adventure.evaluateInput("5");
        assertEquals("I don't understand '5'\n", systemOutRule.getLog());
    }
    @Test
    public void evaluateInputInvalidStringCapitalization() {
        Adventure.evaluateInput("hELlo");
        assertEquals("I don't understand 'hELlo'\n", systemOutRule.getLog());
    }
    @Test
    public void initialGoEastExtraLength() {
        Adventure.evaluateInput("go East and then go NORth");
        assertEquals("I don't understand 'go East and then go NORth'\n",
                systemOutRule.getLog());
    }
    @Test
    public void initialGoEast() {
        Adventure.evaluateInput("go east");
        assertEquals("You are in the west entry of Siebel Center. " +
                "You can see the elevator, the ACM office, and hallways to the north and east.\n" +
                "From here, you can go: West, Northeast, North, or East\n", systemOutRule.getLog());
    }
    @Test
    public void initialGoEastCapitalization() {
        Adventure.evaluateInput("gO EaSt");
        assertEquals("You are in the west entry of Siebel Center. " +
                "You can see the elevator, the ACM office, and hallways to the north and east.\n" +
                "From here, you can go: West, Northeast, North, or East\n", systemOutRule.getLog());
    }
    @Test
    public void initialGoEastExtraLengthSpaces() {
        Adventure.evaluateInput("   go East     ");
        assertEquals("You are in the west entry of Siebel Center. " +
                "You can see the elevator, the ACM office, and hallways to the north and east.\n" +
                "From here, you can go: West, Northeast, North, or East\n", systemOutRule.getLog());
    }
}