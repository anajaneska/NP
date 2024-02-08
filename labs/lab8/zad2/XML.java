package lab8.zad2;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class XML implements XMLComponent{
    String tag;
    Map<String,String> attributes;

    public XML(String tag) {
        this.tag = tag;
        attributes=new TreeMap<>(Comparator.reverseOrder());
    }

    @Override
    public void addAttribute(String attribute, String value) {
        attributes.put(attribute,value);
    }

    @Override
    public String getType() {
        return "COMPONENT";
    }
}
