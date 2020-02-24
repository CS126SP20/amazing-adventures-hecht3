package student.server;

import student.adventure.Room;

/**
 * This class is used to report any errors.
 */
class Error {
    private String message;

    public Error() { }

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

/**
 * Represents the current state of the game.
 */
class GameStatus {
    private int id;
    private Room currentRoom;
    private boolean isOver;

    public GameStatus() { }

    public GameStatus(int id, Room currentRoom, boolean isOver) {
        this.id = id;
        this.currentRoom = currentRoom;
        this.isOver = isOver;
    }

    public int getId() {
        return id;
    }

    public boolean getIsOver() {
        return isOver;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsOver(boolean isOver) {
        this.isOver = isOver;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}