package test;

public class Inc {
    public static void main(String[] args) { 
       Inc inc = new Inc(); 
       int i = 0; 
       inc.fermin(i); 
       int j = i ++;
       System.out.println(j);
        System.out.println(i);


    
    } 
    void fermin(int i){ 
       i++; 
    } 
}