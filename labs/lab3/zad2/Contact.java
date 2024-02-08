package lab3.zad2;

import java.util.List;

public class Contact {
    String name;
    List<String> numbers;
    Contact(String name, String... phonenumber) throws InvalidNameException, MaximumSizeExceddedException, InvalidNumberException {
        if(!isValidName(name))
            throw new InvalidNameException(name);
        if(phonenumber.length>5)
            throw new MaximumSizeExceddedException();
        if(!isValidNumbers(phonenumber))
            throw new InvalidNumberException();

    }

    public boolean isValidName(String name){
        if(name.length()<=4 || name.length()>10)
            return false;
        for(int i=0;i<name.length();i++){
            char c=name.charAt(i);
            if(!Character.isLetter(c) && !Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
    public boolean isValidNumbers(String[] number){
        for(String num : numbers){
            if(!)
        }
    }
    public boolean isValidNumber(String number){
        String [] validPrefix={"070", "071", "072", "075", "076", "077", "078"};
        if(number.length()!=9){
            return false;
        }
    }
}
