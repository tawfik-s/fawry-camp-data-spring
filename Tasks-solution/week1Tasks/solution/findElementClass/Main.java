package tasks.findElementClass;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String [] args){
        List<String> strings= Arrays.asList("first","second","Ali","tawfeek");

        ElementFinder ef=new ElementFinder();
        Comparator<String> comp=(s1, s2)->s1.compareTo(s2);

        System.out.println(ef.findFirst(strings));
        System.out.println(ef.findFirst(strings,comp));


        System.out.println(ef.findLast(strings));
        System.out.println(ef.findLast(strings,comp));

        Comparator<BigDecimal> bigDecimalComparator=(s1,s2)->s1.compareTo(s2);

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
