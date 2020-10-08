package demo;

public class Test {

    /**
     *  百钱买百鸡的问题算是一套非常经典的不定方程的问题，题目很简单：
     *  公鸡5文钱一只，母鸡3文钱一只，小鸡3只一文钱，用100文钱买一百
     *  只鸡,其中公鸡，母鸡，小鸡都必须要有，问公鸡，母鸡，小鸡要买多
     *  少只刚好凑足100文钱。
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 33; j++) {
                if (i*5 + j*3 + (100-i - j)/3 == 100 && (100-i - j)%3 == 0){
                    System.out.print(i + "只公鸡");
                    System.out.print(j + "只母鸡");
                    System.out.print((100-i - j) + "只小鸡");
                    System.out.println();
                }
            }
        }
    }
}
