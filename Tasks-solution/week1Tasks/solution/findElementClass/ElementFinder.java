package tasks.findElementClass;

import java.math.BigDecimal;
import java.util.*;

public class ElementFinder<T> {



    public <T extends Comparable<T>> T findFirst(List<T> list){
        if(list.size()==0) return null;
        T result= list.get(0);
        for(T x:list){
            if(result.compareTo(x)>0){
                result=x;
            }
        }
        return result;
    }

    public T findFirst(List<T> list, Comparator comp){
        if(list.size()==0) return null;
        T result= list.get(0);
        for(T x:list){
            if(comp.compare(result,x)>0){
                result=x;
            }
        }
        return result;

    }

    public <T extends Comparable<T>> T findLast(List<T> list){
        if(list.size()==0) return null;
        T result= list.get(0);
        for(T x:list){
            if(result.compareTo(x)<0){
                result=x;
            }
        }
        return result;
    }

    public T findLast(List<T> list, Comparator comp){
        if(list.size()==0) return null;
        T result= list.get(0);
        for(T x:list){
            if(comp.compare(result,x)<0){
                result=x;
            }
        }
        return result;
    }


    public static void main(String [] args){
        List<String> strings= Arrays.asList("first","second","Ali","tawfeek");

        ElementFinder ef=new ElementFinder();
        Comparator<String> comp=(s1, s2)->s1.compareTo(s2);

        System.out.println(ef.findFirst(strings));
        System.out.println(ef.findFirst(strings,comp));


        System.out.println(ef.findLast(strings));
        System.out.println(ef.findLast(strings,comp));

        Comparator<BigDecimal> bigDecimalComparator=(s1, s2)->s1.compareTo(s2);

        BigDecimal num1=new BigDecimal(123);
        BigDecimal num2=new BigDecimal("12345.22222222222");
        BigDecimal num3=new BigDecimal("12346.8989898897970");
        BigDecimal num4=new BigDecimal("12346.89898988979704546");

        List<BigDecimal> BDList=Arrays.asList(num1,num2);

        ElementFinder<BigDecimal> elementFinder=new ElementFinder<>();

        System.out.println(elementFinder.findFirst(BDList));
        System.out.println(elementFinder.findFirst(BDList,bigDecimalComparator));


        System.out.println(elementFinder.findLast(BDList));
        System.out.println(elementFinder.findLast(BDList,bigDecimalComparator));



    }



}
