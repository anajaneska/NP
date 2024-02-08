package lab5.zad1;

import java.util.Arrays;

public class ResizableArray<T> {
    private T[] niza;
    int size;
    @SuppressWarnings("unchecked")
    public ResizableArray(){
        niza=(T[]) new Object[1];
        size=0;
    }
    public void addElement(T element){
        if(size==niza.length){
            niza= Arrays.copyOf(niza,size<<1);
        }
        niza[size++]=element;
    }
    public boolean removeElement(T element){
        int idx=find(element);
        if(idx==-1)return false;
        niza[idx]=niza[--size];
        if(size<<2 <=niza.length){
            niza=Arrays.copyOf(niza,size<<1>0?size<<1:1);
        }
        return true;
    }
    private int find(T element){
        for(int i=0;i<size;i++){
            if(element.equals(niza[i]))return i;
        }
        return -1;
    }
    public boolean contains(T element){
        return find(element) != -1;
    }
    public Object[] toArray(){
        return Arrays.copyOf(niza,size);
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int count(){
        return size;
    }
    public T elementAt(int idx){
        return niza[idx];
    }
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(niza, size))+" "+niza.length+" "+size;
    }
    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src){
        int count=src.count();
        for(int k=0;k<count;k++){
            dest.addElement(src.elementAt(k));
        }
    }

}
