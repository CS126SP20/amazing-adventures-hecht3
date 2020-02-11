package student;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

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
    @JsonSetter("name")
    public void setName(String roomName) {
        this.name = roomName;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }
    @JsonSetter("items")
    public void setItems(String[] itemsArray) {
        this.items = itemsArray;
    }

    @JsonGetter("directions")
    public ArrayList<Directions> getDirections() {
        return directions;
    }
    @JsonSetter("directions")
    public void setDirections(ArrayList<Directions> directs) {
        this.directions = directs;
    }

}
