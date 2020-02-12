package student;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.ArrayList;

public class RoomExplorer { // The Wrapper class
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
  public void setRooms(ArrayList<Room> roomsList) {
    this.rooms = roomsList;
  }

  @JsonGetter("startingRoom")
  public String getStartingRoom() {
    return startingRoom;
  }

  @JsonSetter("startingRoom")
  public void setStartingRoom(String startRoom) {
    this.startingRoom = startRoom;
  }

  @JsonGetter("endingRoom")
  public String getEndingRoom() {
    return endingRoom;
  }

  @JsonSetter("endingRoom")
  public void setEndingRoom(String endRoom) {
    this.endingRoom = endRoom;
  }
}
