package test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LinkedHashMap实现一个LRU缓存
 */

public class 基于LinkedHashMap实现一个LRU缓存 {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put("key_"+i,"value_"+i);
        }
        // accessOrder为true的话，每次获取到节点后会调用afterNodeAccess(Node<K,V> e)把回去的节点变成tail节点
        Map<String, String> cache = new LinkedHashMap<>(16,0.75f,true);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.get("2");
        cache.forEach((k,v)->{System.out.println(k + "------>" + v);});
        System.out.println("===============================================");
        cache.get("4");
        cache.forEach((k,v)->{System.out.println(k + "------>" + v);});
        System.out.println("===============================================");
        cache.get("1");
        cache.forEach((k,v)->{System.out.println(k + "------>" + v);});
        System.out.println("===============================================");
        cache.get("3");
        cache.forEach((k,v)->{System.out.println(k + "------>" + v);});
    }
}
