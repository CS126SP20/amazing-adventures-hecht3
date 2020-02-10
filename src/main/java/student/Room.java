package student;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("items")
    private String[] items;
    @JsonProperty("directions")
    private ArrayList<Directions> directions;


    @JsonGetter("name")
    public String getName() {
        return name;
    }
    @JsonGetter("description")
    public String getDescription() {
        return description;
    }
    @JsonGetter("items")
    public String[] getItems() {
        return items;
    }
    @JsonGetter("directions")
    public ArrayList<Directions> getDirections() {
        return directions;
    }

}
