package lab7.zad1;

public class NoSuchRoomException extends Exception{
    public NoSuchRoomException(String roomName) {
        super(roomName + " does not exist.");
    }
}
