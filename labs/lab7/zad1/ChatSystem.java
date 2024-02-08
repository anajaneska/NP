package lab7.zad1;

import java.util.*;

public class ChatSystem {

    Map<String,ChatRoom> rooms;
    Set<String> registeredUsers;

    public ChatSystem() {
        rooms=new TreeMap<>();
        registeredUsers=new HashSet<>();
    }

    public void addRoom(String roomName){
        rooms.put(roomName,new ChatRoom(roomName));
    }
    public void removeRoom(String roomName){
        rooms.remove(roomName);
    }
    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        ChatRoom room=rooms.get(roomName);
        if(room==null){
            throw new NoSuchRoomException(roomName);
        }
        return rooms.get(roomName);
    }
    public void register(String userName){
        registeredUsers.add(userName);
        rooms.values().stream()
                .min(Comparator.comparing(ChatRoom::numUsers).thenComparing(ChatRoom::getName))
                .ifPresent(x->x.addUser(userName));
    }
    private boolean isRegistered(String userName){
        return registeredUsers.contains(userName);
    }
    public void registerAndJoin(String userName, String roomName) throws NoSuchRoomException {
        registeredUsers.add(userName);
        /*try{
            ChatRoom room=getRoom(roomName);
            room.addUser(userName);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

         */
        if(!rooms.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        rooms.get(roomName).addUser(userName);
    }
    public void joinRoom(String userName, String roomName) throws NoSuchUserException, NoSuchRoomException {
        if(!isRegistered(userName))
            throw new NoSuchUserException(userName);
        if(!rooms.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        rooms.get(roomName).addUser(userName);
    }
    public void leaveRoom(String username, String roomName) throws NoSuchUserException, NoSuchRoomException {
        if(!isRegistered(username))
            throw new NoSuchUserException(username);
        if(!rooms.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        rooms.get(roomName).removeUser(username);
    }
    public void followFriend(String username, String friend_username) throws NoSuchUserException {
        if(!isRegistered(username))
            throw new NoSuchUserException(username);
        if(!isRegistered(friend_username))
            throw new NoSuchUserException(friend_username);

        for(ChatRoom room : rooms.values()){
            if(room.hasUser(friend_username)){
                room.addUser(username);
            }
        }
    }
}
