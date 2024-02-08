package lab7.zad1;

import java.util.Set;
import java.util.TreeSet;

public class ChatRoom {
    String name;
    Set<String> users;

    public ChatRoom(String name) {
        this.name = name;
        this.users=new TreeSet<>();
    }
    public void addUser(String username){
        users.add(username);
    }
    public void removeUser(String username){
        users.remove(username);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(name).append("\n");
        if(users.isEmpty()){
            sb.append("EMPTY").append("\n");
        }else{
            users.forEach(i->sb.append(i).append("\n"));
        }
        return sb.toString();
    }
    public boolean hasUser(String username){
        return users.contains(username);
    }
    public int numUsers(){
        return users.size();
    }

    public String getName() {
        return name;
    }
}
