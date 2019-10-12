import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *@Title: HashMap的几种遍历方式
 *@Copyright: 2019-10-12
 *@Author: FanRuikang(樊瑞康)
 *@Version:1.0
 */
public class HashmapDemo {
    public static void main(String[] args) {
        HashMap<Integer, String> hashmap = new HashMap<>(5);
        hashmap.put(1,"gogo");
        hashmap.put(2,"wade");
        hashmap.put(3,"james");
        hashmap.put(4,"curry");
        // 1. 通过Map.keySet遍历key和value：
        for (int key : hashmap.keySet()){
            System.out.println("key: "+ key + "; value: " + hashmap.get(key));
        }

        //2. 通过Map.entrySet使用iterator遍历key和value：
        Iterator<Map.Entry<Integer, String>> it = hashmap.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<Integer, String> entry = it.next();
            System.out.println("key: "+ entry.getKey() + "; value: " + entry.getValue());
        }

        //3. 通过Map.entrySet遍历key和value
        for(Map.Entry<Integer, String> entry : hashmap.entrySet()){
            System.out.println("key: "+ entry.getKey() + "; value: " + entry.getValue());
        }

        //4. 通过Map.values()遍历所有的value，但不能遍历key
        for (String value : hashmap.values()) {
            System.out.println("value: "+value);
        }
    }
}
