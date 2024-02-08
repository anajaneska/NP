package lab7.zad3;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Scheduler<T> {
    TreeMap<Date, T> schedules;

    public Scheduler() {
        schedules=new TreeMap<>();
    }
    public void add(Date d, T t){
        schedules.putIfAbsent(d,t);
    }
    public boolean remove(Date d){
        /*if(schedules.containsKey(d)){
            schedules.remove(d);
            return true;
        }
        return false;

         */
        return (boolean) schedules.remove(d);
    }
    public T next(){
        return schedules.ceilingEntry(new Date()).getValue();
    }
    public T last(){
        return schedules.floorEntry(new Date()).getValue();
    }
    public ArrayList<T> getAll(Date begin,Date end){
        ArrayList<T> list=new ArrayList<>();
        schedules.keySet().stream()
                .filter(x->x.after(begin) && x.before(end))
                .forEach(x->list.add(schedules.get(x)));
        return list;
    }
    public T getFirst(){
        return schedules.firstEntry().getValue();
    }
    public T getLast(){
        return schedules.lastEntry().getValue();
    }
}
