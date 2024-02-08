package lab7.zad1;

public class NoSuchUserException extends Exception{
    public NoSuchUserException(String username) {
        super(username + " does not exist.");
    }
}
