package test;

public class Test {
    public static void main(String[] args) {
        int flag = 1;
        if((flag=2)==2){
            System.out.println("true");
        }else{

            System.out.println("false");
        }
        System.out.println("return value of getValue(): " +
        getValue());
    }
     public static int getValue() {
         try {
             return 0;
         } finally {
             return 1;
         }
     }
 }