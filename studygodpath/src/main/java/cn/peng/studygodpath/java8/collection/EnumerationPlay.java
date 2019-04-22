package cn.peng.studygodpath.java8.collection;

import org.testng.annotations.Test;

import java.util.*;

public class EnumerationPlay {

    @Test
    public void enumeration() {
        String[] data = {"a", "b", "c"};
        List<String> list = new ArrayList<>(Arrays.asList(data));
        list.add("d");

        Enumeration<String> stringEnumeration = new Enumeration<String>() {
            private int position = 0;

            @Override
            public boolean hasMoreElements() {
                return position < list.size();
            }

            @Override
            public String nextElement() {
                return list.get(position++);
            }
        };
        while (stringEnumeration.hasMoreElements()) {
            System.out.println(stringEnumeration.nextElement());
        }
    }

    @Test
    public void listIterator(){
        String[] data = {"a", "b", "c"};
        List<String> list = new ArrayList<>(Arrays.asList(data));
        list.add("d");
        ListIterator<String> befor = list.listIterator();
        ListIterator<String> after = list.listIterator(list.size());
        for(;befor.hasNext();){
            System.out.print(befor.next());
        }
        System.out.println();
        for(;after.hasPrevious();){
            System.out.print(after.previous());
        }
    }


}
