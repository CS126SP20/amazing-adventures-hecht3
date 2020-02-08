package student;

import java.util.List;

public class RoomExplorer { //The Wrapper class
    public List<Rooms> rooms;
    public String startingRoom;
    public String endingRoom;

    // Add Constructor and getter methods
    protected static class Rooms {
        public String name;
        public String description;
        public String[] items;
        public List<Directions> directions;
        // -- Constructor and getter methods omitted for brevity --
    }
    protected static class Directions {
        public String directionName;
        public String room;
    }
}