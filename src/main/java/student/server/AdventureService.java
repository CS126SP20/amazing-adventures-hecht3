package student.server;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import student.adventure.Adventure;


/**
 * Singleton class which acts as a store for adventure games.
 */
public class AdventureService {
    // A Map from game id to Adventure game instance.
    private final Map<Integer, Adventure> games;
    private int counter;
    private static AdventureService service = null;

    public AdventureService() {
        games = new HashMap<>();
        counter = 0;
    }

    /**
     * Returns an instance of the service.
     */
    public static AdventureService instance() {
        if (service == null) {
            reset();
        }

        return service;
    }

    /**
     * Resets the service to its initial state.
     *
     * Note that this is not the best design, we should be
     * using "Dependency Injection", but this would likely be too complicated
     * to setup for this assignment.
     */
    public static void reset() {
        service = new AdventureService();
    }

    /**
     * Creates a new Adventure game.
     * @param url the url of the JSON.
     * @return the id of the game.
     *
     * TODO: Make Exception thrown more specific.
     */
    int newGame(String url) throws Exception {
        // TODO: Complete this method.
        return -1;
    }

    /**
     * Returns the game corresponding to the id. Returns null if non-existent.
     * @param id the id of the game.
     * @return the game; null if non-existent.
     */
    Adventure getGame(int id) {
        // TODO: Complete this method.
        return null;
    }

    /**
     * Removes the game corresponding to the id from the store.
     * @param id the id of the game.
     * @return true if the game was found and destroyed; false otherwise.
     */
    boolean destroyGame(int id) {
        // TODO: Complete this method.
        return false;
    }

    /**
     * Attempts to move in the specified direction.
     * @param id the id of the game.
     * @param direction the direction to move.
     * @param logger logs anything the Adventure game reports (e.g., invalid direction).
     * @return the updated game; null if something went wrong.
     */
    Adventure goInDirection(int id, String direction, OutputStream logger) {
        // TODO: Complete this method.
        return null;
    }

    /**
     * adds/updates items to the current room of the game with the corresponding id.
     * if an item already exists, the item should not be added again.
     * @param id the id of the game.
     * @param items list of items to add.
     * @return true if items were successfully added; false otherwise.
     */
    boolean addItems(int id, List<String> items) {
        // TODO: Complete this method.
        return false;
    }

    /**
     * adds/updates items to the current room of the game with the corresponding id.
     * if an item already exists, the item should not be added again.
     * @param id the id of the game.
     * @param item the item to remove.
     * @return true if items were successfully added; false otherwise.
     */
    boolean removeItem(int id, String item) {
        // TODO: Complete this method.
        return false;
    }
}