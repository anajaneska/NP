package lab3.zad2;

public class InvalidNameException extends Exception{
    public InvalidNameException(String name) {
        super(name+" is not valid");
    }
}
