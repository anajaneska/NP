package lab8.zad1;

public class Song {
    String title;
    String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }
    @Override
    public String toString() {
        return String.format("Song{title=%s, artist=%s}",title,artist);
    }
}
