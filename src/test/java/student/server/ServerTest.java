package student.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

/**
 * These are not unit tests; however, they are still automatic tests
 * which have good code coverage.
 */
public class ServerTest {
    private static final String SIEBEL_JSON =
        "https://courses.engr.illinois.edu/cs126/sp2020/resources/siebel.json";

    private AdventureResource server;

    @Before
    public void setUp() {
        // Should really use dependency injection here, but it is
        // a bit complicated to setup with Jersey/JAX-RS.
        AdventureService.reset();
        server = new AdventureResource();
    }

    @Test
    public void testPong() {
        String response = server.ping();
        assertEquals("pong", response);
    }

    @Test
    public void testCreateValidUrl() {
        NewGame newGame = new NewGame(SIEBEL_JSON);
        Response response = server.create(newGame);

        // Check that something is returned.
        assertTrue(response.hasEntity());

        // Check that the object is of the correct type.
        Object object = response.getEntity();
        assertThat(object, CoreMatchers.instanceOf(GameStatus.class));

        GameStatus gameStatus = (GameStatus) response.getEntity();

        // ... or whatever id you are expecting.
        assertEquals(0, gameStatus.getId());
        assertEquals("MatthewsStreet", gameStatus.getCurrentRoom().getName());

        // Maybe more assertions/walk-through.
    }

    // TODO: Add more tests.
}