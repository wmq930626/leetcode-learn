package framework;

public class Test {
    public int aMethod(){
        int i = 0;
        i++;
        return i;
    }
public static void main(String args[]){

        String s ="00000";
        s += "000000000";
    Test test = new Test();
    test.aMethod();
    int j = test.aMethod();
    System.out.println(j);
    }
}