package lab5.zad2;

import java.sql.Time;
import java.time.LocalDateTime;

public class Timestamp<T> implements Comparable<Timestamp<? extends T>>{
    private final LocalDateTime time;
    private final T element;

    public Timestamp(LocalDateTime time, T element) {
        this.time = time;
        this.element = element;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public T getElement() {
        return element;
    }

    @Override
    public int compareTo(Timestamp<? extends T> o) {
        return time.compareTo(o.time);
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        Timestamp<T> t=(Timestamp<T>) obj;
        return this.time.equals(t.time);
    }

    @Override
    public String toString() {
        return String.format("%s %s",time,element);
    }
    public boolean isAfterNow(){
        return this.time.isAfter(LocalDateTime.now());
    }
    public boolean isBeforeNow(){
        return this.time.isBefore(LocalDateTime.now());
    }
}
