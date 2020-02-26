package student.adventure;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

  @JsonGetter("items")
  public List<String> getItems() {
    if (items == null) {
      return new ArrayList<String>();
    }
    return Arrays.asList(items);
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
  /**
   * The method for removing an item from the items list for this room. Will never be called with
   * and item that is not known to be in the room because the Adventure class checks for that before
   * calling this method.
   *
   * @param itemToRemove the item the user wants to remove
   */
  public void removeItem(String itemToRemove) {
    ArrayList<String> newItemsArray = new ArrayList<>();
    for (int i = 0; i < items.length; i++) {
      if (!itemToRemove.equals(items[i])) {
        newItemsArray.add(items[i]);
      }
    }
    String[] arrayAsStrings = new String[newItemsArray.size()];
    arrayAsStrings = newItemsArray.toArray(arrayAsStrings);
    items = arrayAsStrings;
  }

  /**
   * The method for adding an item to the items list for this room. Will never be called with
   * and item that is already in the room because the Adventure class checks for that before
   * calling this method.
   *
   * @param itemToAdd the item the user wants to add
   */
  public void addItem(String itemToAdd) {
    String[] newItemsArray = new String[items.length + 1];
    for (int i = 0; i < newItemsArray.length; i++) {
      if (i != newItemsArray.length - 1) {
        newItemsArray[i] = items[i];
      } else {
        newItemsArray[i] = itemToAdd;
      }
    }
    items = newItemsArray;
  }
}
