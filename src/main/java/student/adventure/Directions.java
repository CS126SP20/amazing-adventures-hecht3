package student.adventure;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Directions {
  @JsonProperty("directionName")
  private String directionName;

  @JsonProperty("room")
  private String room;

  @JsonGetter("directionName")
  public String getDirectionName() {
    return directionName;
  }

  @JsonSetter("directionName")
  public void setDirectionName(String directionN) {
    this.directionName = directionN;
  }

  @JsonGetter("room")
  public String getRoom() {
    return room;
  }

  @JsonGetter("room")
  public void setRoom(String roomName) {
    this.room = roomName;
  }
}
