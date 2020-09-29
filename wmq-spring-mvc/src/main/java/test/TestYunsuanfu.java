package test;

public class TestYunsuanfu {
    public static void main(String[] args) {
       /* System.out.println(78>>32);
        System.out.println(78>>>1);
        System.out.println(2f-1.9f);
        System.out.println(1921680127l&2552552550l);

        System.out.println(126 | 2);

        System.out.println((62 & 7) > 0);*/

       /* Flags |= 1<<1; //将第bit位置1
        Flags &= ~(1<<bit);//将第bit位清零
        if (Flags & (1<<bit)) {//第bit位为1
        } else {//第bit位为0
        }*/
        int flag = 0;
        flag |= 1<<0;
        System.out.println(flag);
        flag |= 1<<1;
        System.out.println(flag);
        flag |= 1<<2;
        System.out.println(flag);
        flag |= 1<<3;
        System.out.println(flag);
        flag |= 1<<4;
        System.out.println(flag);
        flag |= 1<<5;
        System.out.println(flag);
        flag |= 1<<6;
        System.out.println(flag);
        flag |= 1<<7;
        System.out.println(flag);
        flag &= ~(1<<7);
        System.out.println(flag);
    }
}
