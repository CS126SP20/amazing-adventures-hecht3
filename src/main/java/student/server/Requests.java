package student.server;

import java.util.List;

/**
 * Represents a new game request.
 */
class NewGame {
    private String url;

    public NewGame() { }

    public NewGame(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

/**
 * Represents a request to add items.
 */
class AddItems {
    private List<String> items;

    public AddItems() { }

    public AddItems(List<String> items) {
        this.setItems(items);
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}

/**
 * Represents a request move in a direction.
 */
class Go {
    private String direction;

    public Go() { }

    public Go(String direction) {
        setDirection(direction);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}