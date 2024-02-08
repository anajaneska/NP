package lab5.zad1;

import java.util.Arrays;

public class IntegerArray extends ResizableArray<Integer>{
    public IntegerArray() {
        super();
    }
    public double sum(){
        int sum=0;
        Object a[]=toArray();
        for(int i=0;i<a.length;i++){
            sum+=(Integer)a[i];
        }
        return sum;
    }
    public double mean(){
        return sum()/count();
    }
    public int countNonZero(){
        int count=0;
        Object a[]=toArray();
        for(int i=0;i<a.length;i++){
            count+=(Integer)a[i]!=0?1:0;
        }
        return count;
    }
    public IntegerArray distinct(){
        IntegerArray res=new IntegerArray();
        Object a[]=toArray();
        Arrays.sort(a);
        for(int k=0;k<a.length;k++){
            while(k<a.length-1 && a[k]==a[k+1]){
                ++k;
            }
            res.addElement((Integer)a[k]);
        }
        return res;
    }
    public IntegerArray increment(int offset){
        IntegerArray res=new IntegerArray();
        Object a[]=toArray();
        for(int k=0;k<a.length;k++){
            res.addElement((Integer)a[k]+offset);
        }
        return res;
    }
}
