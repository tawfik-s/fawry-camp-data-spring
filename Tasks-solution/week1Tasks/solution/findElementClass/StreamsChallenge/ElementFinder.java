package tasks.findElementClass.StreamsChallenge;

import java.util.Comparator;
import java.util.List;

public class ElementFinder<T> {



    public <T extends Comparable<T>> T findFirst(List<T> list){
        return list.stream().sorted().findFirst().get();
    }

    public T findFirst(List<T> list, Comparator comp){
        return (T) list.stream().sorted(comp).findFirst().get();

    }

    public <T extends Comparable<T>> T findLast(List<T> list){
        return list.stream().
                sorted(Comparator.reverseOrder())
                .findFirst().get();
    }

    public T findLast(List<T> list, Comparator comp){

        return (T)list.stream().sorted(comp.reversed()).findFirst().get();
    }




}
