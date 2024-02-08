package lab7.zad4;

import javax.security.sasl.SaslClient;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class TermFrequency {
    Map<String,Integer> words;
    public TermFrequency(InputStream inputStream, String[] stopWords){
        words=new TreeMap<>();

        Scanner sc=new Scanner(inputStream);
        List<String> stop= Arrays.asList(stopWords);

        while(sc.hasNext()){
            String w=sc.next();
            w=w.toLowerCase().replace(",","\0").replace(".","\0").trim();
            if(w.isEmpty() || stop.contains(w)) continue;
            int v=words.computeIfAbsent(w,x->0);
            words.put(w,++v);

        }
    }
    public int countTotal(){
        return words.values().stream()
                .mapToInt(i->i)
                .sum();
    }
    public int countDistinct(){
        return words.size();
    }
    public List<String> mostOften(int k){
        return words.keySet().stream()
                .sorted(Comparator.comparing(x->words.get(x)).reversed())
                .limit(k)
                .collect(Collectors.toList());
    }
}
