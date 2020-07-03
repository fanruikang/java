package 集合;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *@Title: HashMap的几种遍历方式
 *@Copyright: 2019-10-12
 *@Author: FanRuikang(樊瑞康)
 *@Version:1.0
 */
public class HashmapDemo {
    public static void main(String[] args) throws InterruptedException {

        HashmapDemo hashmapDemo = new HashmapDemo();
//        hashmapDemo.circleItem();
        hashmapDemo.confilct();
    }

    /**
     * @Description HashMap的四种循环遍历方式
     * @Date 2019/10/14
     * @author FanRuikang
     **/
    private  void circleItem() {


        HashMap<Integer, String> hashmap = new HashMap<>(5);
        hashmap.put(1,"frk");
        hashmap.put(2,"handsome");
        hashmap.put(3,"man");
        hashmap.put(4,"!!!");
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
    
    /**
     * @Description 验证并发下丢失值的问题
     * @Date 2019/10/14
     * @author FanRuikang
     **/
    private void confilct() throws InterruptedException {
        //解决方法：用ConcurrentHashMap代替HashMap
//        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(2048);
        final Map<String, String> map = new HashMap<>(2048);
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                final int x = i;
                new Thread(() -> put(map, x),i+" _subThread").start();
                if(i!=0 && i%10000==0){
                    System.out.println("thread1中显示的map.size:"+map.size());
                }

            }
        },"thread");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                final int x = i;
                new Thread(() -> put(map, x),i+" _subThread2").start();
                if(i!=0 && i%10000==0){
                    System.out.println("thread2中显示的map.size:"+map.size());
                }

            }
        },"thread2");
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                final int x = i;
                new Thread(() -> put(map, x),i+" _subThread3").start();
                if(i!=0 && i%10000==0){
                    System.out.println("thread3中显示的map.size:"+map.size());
                }

            }
        },"thread3");

        t.start();
        t2.start();
        t3.start();
        t.join();
        t2.join();
        t3.join();
//        Thread.sleep(10000);
        TimeUnit.MINUTES.sleep(1);
        System.out.println(map.size());
    }

    /**
     * @Description 获取32位随机值
     * @Date 2019/10/14
     * @author FanRuikang
     **/
     String getKey() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.ENGLISH);
    }

    /**
     * @Description 放入map
     * @Date 2019/10/14
     * @author FanRuikang
     **/
     void put(Map<String, String> map, int index){
        String key = getKey();
        map.put(key, key);
        if(map.get(key)==null){
            System.out.println(key+",元素缺失，index:"+index);
        }
    }
}
