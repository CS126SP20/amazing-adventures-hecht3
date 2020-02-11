package student;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.glassfish.grizzly.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomExplorer { //The Wrapper class
    @JsonProperty("rooms")
    private ArrayList<Room> rooms;
    @JsonProperty("startingRoom")
    private String startingRoom;
    @JsonProperty("endingRoom")
    private String endingRoom;

    @JsonGetter("rooms")
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    @JsonSetter("rooms")
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    @JsonGetter("startingRoom")
    public String getStartingRoom() {
        return startingRoom;
    }
    @JsonSetter("startingRoom")
    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    @JsonGetter("endingRoom")
    public String getEndingRoom() {
        return endingRoom;
    }
    @JsonSetter("endingRoom")
    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }
}