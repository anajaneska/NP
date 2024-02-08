package lab8.zad1;

import java.util.List;

public class MP3Player {
    List<Song> songs;
    int currentSong;
    int playing;
    public MP3Player(List<Song> songs) {
        this.songs=songs;
        this.currentSong=0;
        this.playing=0;
    }

    public void pressPlay() {
        if(playing==1){
            System.out.println("Song is already playing");
        }else{
            System.out.printf("Song %d is playing\n",currentSong);
            playing=1;
        }
    }

    public void printCurrentSong() {
        System.out.println(songs.get(currentSong).toString());
    }

    public void pressStop() {
        if(playing==1){
            System.out.printf("Song %d is paused\n",currentSong);
            playing=0;
        }else if(playing==0){
            System.out.println("Songs are stopped");
            playing=-1;
            currentSong=0;
        }else{
            System.out.println("Songs are already stopped");
        }
    }

    public void pressFWD() {
        if(currentSong+1==songs.size()){
            currentSong=0;
        }else{
            currentSong++;
        }
        if(playing!=-1){
            playing=0;
        }
        System.out.println("Forward...");
    }

    public void pressREW() {
        if(currentSong-1==-1){
            currentSong=songs.size()-1;
        }else{
            currentSong--;
        }
        if(playing!=-1){
            playing=0;
        }
        System.out.println("Reward...");
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("MP3Player{");
        sb.append("currentSong = ").append(currentSong);
        sb.append(", songList = ").append(songs);
        sb.append('}');
        return sb.toString();
    }
}
