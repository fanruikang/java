package 并发;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *@Title:
 *@Description: 测试并发带来的速度提升
 *@Copyright: 2019-10-23 
 *@Author: FanRuikang(樊瑞康)
 *@Version:1.0
 */
public class ConcurrencyTest {
    private static final long count = 10000;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();

        executor();
    }

    private static void executor() {
        long start = System.currentTimeMillis();
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(() -> {
            long now = System.currentTimeMillis();
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
//                try {
//                    Thread.sleep(0,1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            long time = System.currentTimeMillis() - now;
            System.out.println("concurrency thread end!"+time+"ms" + a);

        });

        System.out.println("executorinit:" + (System.currentTimeMillis()-start) + "ms");
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("executor:" + time + "ms,b = " + b);
//        thread.join();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            long now = System.currentTimeMillis();
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
//                try {
//                    Thread.sleep(0,1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            long time = System.currentTimeMillis() - now;
            System.out.println("concurrency thread end!"+time+"ms" + a);

        });
        System.out.println("threadinit:" + (System.currentTimeMillis()-start) + "ms");
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency:" + time + "ms,b = " + b);
//        thread.join();
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
//            try {
//                Thread.sleep(0,1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }


        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b = " + b + ",a=" + a);
    }

}
