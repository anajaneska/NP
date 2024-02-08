package lab8.zad2;

import java.util.ArrayList;
import java.util.List;

public class XMLComposite extends XML{
    List<XMLComponent> components;

    public XMLComposite(String tag) {
        super(tag);
        components=new ArrayList<>();
    }

    public void addComponent(XMLComponent component) {
        components.add(component);
    }

    @Override
    public String getType() {
        return "COMPOSITE";
    }
    String toStringRecursive(XMLComposite xml,int level){
        StringBuilder sb=new StringBuilder();
    }
}
