package student;

import java.util.List;

public class RoomExplorer { //The Wrapper class
    public List<Rooms> rooms;
    public String startingRoom;
    public String endingRoom;

    public List<Rooms> getRooms() {
        return rooms;
    }
    public String getStartingRoom() {
        return startingRoom;
    }
    public String[] getEndingRoom() {
        return endingRoom;
    }

    // Add Constructor and getter methods
    public static class Rooms {
        public String name;
        public String description;
        public String[] items;
        public Directions[][] directions;

        public Directions[][] getDirections() {
            return directions;
        }
        public String getDescription() {
            return description;
        }
        public String[] getItems() {
            return items;
        }
        public String getName() {
            return name;
        }

    }
    public static class Directions {
        public String directionName;
        public String room;

        public String getDirectionName() {
            return directionName;
        }
        public String getRoom() {
            return room;
        }
    }
}