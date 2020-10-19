package demo;

import java.util.ArrayList;
import java.util.List;

public class Test {

    /**
     *  百钱买百鸡的问题算是一套非常经典的不定方程的问题，题目很简单：
     *  公鸡5文钱一只，母鸡3文钱一只，小鸡3只一文钱，用100文钱买一百
     *  只鸡,其中公鸡，母鸡，小鸡都必须要有，问公鸡，母鸡，小鸡要买多
     *  少只刚好凑足100文钱。
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> sortList = new ArrayList<>();
        addAtIndex(sortList,-1,"11111111111111");
        addAtIndex(sortList,-2,"333333333333333");
        addAtIndex(sortList,3,"44444444444444");
        addAtIndex(sortList,4,"55555555555555555");
        sortList.forEach(t-> System.out.println(t));
        System.out.println("=====================");
        addAtIndex(sortList,2,"222222222222222");
        addAtIndex(sortList,20,"666666666666666");
        sortList.forEach(t-> System.out.println(t));
    }
    private static void addAtIndex(List<String> sortList,int index,String element){
        if (sortList.size() == 0 || index < 0){
            sortList.add(0,element);
            return;
        }
        if (index > sortList.size()){
            sortList.add(sortList.size() ,element);
        }else {
            sortList.add(index-1,element);
        }
    }
}
