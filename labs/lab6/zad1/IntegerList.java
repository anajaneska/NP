package lab6.zad1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerList {
    private List<Integer> list;

    public IntegerList() {
        this.list=new ArrayList<>();
    }
    public IntegerList(Integer... numbers){
        this.list=new ArrayList<>();
        IntStream.range(0,numbers.length).forEach(i->list.add(numbers[i]));
    }
    public void add(int el, int idx){
        if(idx>=list.size()){
            IntStream.range(list.size(),idx).forEach(i->list.add(i,0));
        }
        list.add(idx,el);
    }
    public int remove(int idx){
        if(idx<0 || idx>=list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return list.remove(idx);
    }
    public void set(int el, int idx){
        if(idx<0 || idx>=list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        list.set(idx,el);
    }
    public int get(int idx){
        if(idx<0 || idx>=list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return list.get(idx);
    }
    public int size(){
        return list.size();
    }
    public int count(int el){
        return (int)list.stream()
                .filter(i->i==el)
                .count();
    }
    public void removeDuplicates(){
        Collections.reverse(list);
        list=list.stream().distinct().collect(Collectors.toList());
        Collections.reverse(list);
    }
    public int sumFirst(int k){
        return list.stream()
                .limit(k)
                .mapToInt(i->i)
                .sum();
    }
    public int sumLast(int k){
        return list.stream()
                .skip((long) list.size()-k)
                .mapToInt(i->i)
                .sum();
    }
    public void shiftRight(int idx, int k){
        if(idx<0 || idx>=list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        int a=list.get(idx);
        list.remove(idx);
        list.add((idx+k)%(list.size()+1),a);
    }
    public void shiftLeft(int idx , int k){
        if(idx<0 || idx>=list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        int a=list.get(idx);
        list.remove(idx);
        list.add(Math.floorMod(idx - k, list.size() + 1), a);
    }
    public IntegerList addValue(int value){
        IntegerList li=new IntegerList();
        li.list=list.stream().map(i->i+value).collect(Collectors.toList());
        return li;
    }

}
