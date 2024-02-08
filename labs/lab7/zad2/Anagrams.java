package lab7.zad2;

import java.io.InputStream;
import java.util.*;

public class Anagrams {

    public static void main(String[] args) {
        findAll(System.in);
    }

    public static void findAll(InputStream inputStream) {
        Scanner sc=new Scanner(inputStream);
        Map<String, TreeSet<String>> allWords=new TreeMap<>();

        while(sc.hasNextLine()){
            String word=sc.nextLine();
            char [] temp=word.toCharArray();
            Arrays.sort(temp);
            String sortedWord=new String(temp);

            allWords.computeIfAbsent(sortedWord, x->new TreeSet<>());
            allWords.get(sortedWord).add(word);
        }
        allWords.values().stream()
                .filter(x->x.size()>=5)
                .sorted(Comparator.comparing(TreeSet::first))
                .forEach(x->System.out.println(String.join(" ",x)));
    }
}
