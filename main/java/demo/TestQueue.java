package demo;

import java.util.ArrayDeque;
import java.util.Queue;

public class TestQueue {

    public static void main(String[] args) {
        josephus(10,1,3);
    }

    /**
     * 使用队列解决约瑟夫环
     * @param total
     * @param begin
     * @param count
     */
    public static void josephus(int total,int begin,int count){
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = begin; i <= total; i++) {
            queue.add(i);
        }
        for (int i = 1; i < begin; i++) {
            queue.add(i);
        }
        int flag = 1;
        while (queue.size() > 0){
            if (flag < count){
                Integer ele = queue.poll();
                queue.add(ele);
                flag++;
            }else {
                flag = 1;
                System.out.println(queue.poll());
            }
        }
    }
}
