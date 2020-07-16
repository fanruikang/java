package 并发;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {

//        useThreadPoolExecutor();

        useHavedThreadPoolExecutor();

        //定时任务，周期执行和周期延迟执行，Delay和任务执行的时间有关
//        useScheduledThreadPool();
//        useScheduledThreadPool1();
//        System.out.println("jfsdkajfoia");

        }


    //定时线程，任务，开始时间，时间间隔，时间单位
    private static void useScheduledThreadPool() {
        ScheduledExecutorService cachedThreadPoolExecutor = Executors.newScheduledThreadPool(10);
        cachedThreadPoolExecutor.scheduleWithFixedDelay(new MyThreadNum(1),0,10, TimeUnit.SECONDS);
//        cachedThreadPoolExecutor.scheduleAtFixedRate(new MyThreadNum(2),0,1, TimeUnit.NANOSECONDS);
    }    //定时线程，任务，开始时间，时间间隔，时间单位
    private static void useScheduledThreadPool1() {
        ScheduledExecutorService cachedThreadPoolExecutor = Executors.newScheduledThreadPool(10);
//        cachedThreadPoolExecutor.scheduleWithFixedDelay(new MyThreadNum(1),0,1, TimeUnit.NANOSECONDS);
        cachedThreadPoolExecutor.scheduleAtFixedRate(new MyThreadNum(2),0,10, TimeUnit.SECONDS);
    }
    private static void useHavedThreadPoolExecutor() {
//        //容量固定，队列无限制
        ExecutorService cachedThreadPoolExecutor = Executors.newFixedThreadPool(4);
//        //没有核心线程，非核心线程很大
//        ExecutorService cachedThreadPoolExecutor = Executors.newCachedThreadPool();

        //核心线程数和线程总数为1，创建一个单线程的线程池，他所有的任务都是用这个线程来执行的，保证所有任务按照指定顺序执行；他还能保证当一个线程发生异常时，他会继续往下执行：
//        ExecutorService cachedThreadPoolExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            cachedThreadPoolExecutor.execute(new MyThreadNum(i));
//            主线程睡眠等待线程执行完，则cached线程池则会复用空闲线程
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        scheduledThreadPool.shutdown();
//        while(!scheduledThreadPool.isTerminated()){
//        }
//        System.out.println("Finished all threads");
    }

    /**
     *@Title:
     *@Description: 一、缓存队列 LinkedBlockingQueue 没有设置固定容量大小
     * 1.1、Executors.newFixedThreadPool()
     * 1.2、Executors.newSingleThreadExecutor()
     * 总结：
     * 二 、最大线程数量是 Integer.MAX_VALUE
     * 2.1、Executors.newCachedThreadPool()
     * 2.2、Executors.newScheduledThreadPool()
     * 总结：
     * 三、拒绝策略不能自定义（这个不是重点）
     * 四、创建线程 或 线程池时请指定有意义的线程名称，方便出错时回溯（这个不是重点）
     *@Copyright: 2019-10-16
     *@Author: FanRuikang(樊瑞康)
     *@Version:1.0
     */
    private static void useThreadPoolExecutor() {
        ExecutorService pool = new ThreadPoolExecutor(5, 5, 60000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
        for (int i = 0; i < 7; i++) {
            pool.execute(new LiftOff());
        }
    }
}

class LiftOff implements Runnable {
    private static int taskCount = 0;
    private final int id = taskCount++;
    private int countDown = 10;

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(Thread.currentThread().getName()+"#" + id + ":" + (countDown > 0 ? countDown : "Liftoff!"));
            Thread.yield();
        }
    }
}


class MyThreadNum implements Runnable{
    int i=0;
    public MyThreadNum(int i){
        this.i=i;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName()+"====="+i);
        int k = 100/i;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}