package lab5.zad2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class Scheduler<T> {
    List<Timestamp<T>> elements;

    public Scheduler() {
        elements=new ArrayList<>();
    }
    public void add(Timestamp<T> t){
        elements.add(t);
    }
    public boolean remove(Timestamp<T> t){
        return elements.remove(t);
    }
    public Timestamp<T> next(){
        return elements.stream()
                .filter(Timestamp::isAfterNow)
                .sorted()
                .findFirst()
                .orElse(null);
    }
    public Timestamp<T> last(){
        return elements.stream()
                .filter(Timestamp::isBeforeNow)
                .sorted(Comparator.<Timestamp,LocalDateTime>comparing(Timestamp::getTime).reversed())
                .findFirst()
                .orElse(null);
    }
    public List<Timestamp<T>> getAll(LocalDateTime begin, LocalDateTime end){
        return elements.stream()
                .filter(each->each.getTime().isBefore(end)&&each.getTime().isAfter(begin))
                .collect(Collectors.toList());
    }


}
