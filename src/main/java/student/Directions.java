package student;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Directions {
    @JsonProperty("directionName")
    private String directionName;
    @JsonProperty("room")
    private String room;

    @JsonGetter("directionName")
    public String getDirectionName() {
        return directionName;
    }
    @JsonGetter("room")
    public String getRoom() {
        return room;
    }
}
