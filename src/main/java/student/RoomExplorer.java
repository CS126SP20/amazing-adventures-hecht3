package student;

import org.glassfish.grizzly.utils.ArrayUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomExplorer { //The Wrapper class
    private static List<Room> rooms;
    private static Room startingRoom;
    private static Room endingRoom;

    public static List<Room> getRooms() {
        return rooms;
    }
    public static Room getStartingRoom() {
        return startingRoom;
    }
    public static Room getEndingRoom() {
        return endingRoom;
    }

    // Add Constructor and getter methods
    public static class Room {
        private static String name;
        private static String description;
        private static String[] items;
        private static Directions[][] directions;

        public static String getName() {
            return name;
        }
        public static String getDescription() {
            return description;
        }
        public static String[] getItems() {
            return items;
        }
        public static Map<Directions, Directions> getDirectionsAsHashMap() {
            final Map<Directions, Directions> map = new HashMap<Directions, Directions>(directions.length);
            for (Directions[] direction : directions)
            {
                map.put(direction[0], direction[1]);
            }
            return map;
            // This way we know that the directions will be paired with their respective rooms in
            // the HashMap
        }

    }
    public static class Directions {
        private static String directionName;
        private static String room;

        public static String getDirectionName() {
            return directionName;
        }
        public static String getRoom() {
            return room;
        }
    }
}