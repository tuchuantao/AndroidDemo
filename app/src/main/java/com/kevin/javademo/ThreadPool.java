package com.kevin.javademo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by Kevin-Tu on 2020/6/26.
 */
public class ThreadPool {

    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newCachedThreadPool()
        //ExecutorService threadPool = new ThreadPoolExecutor(5, 100, 60, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
        ExecutorService threadPool = new ThreadPoolExecutor(5, 100, 60, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>()
        , Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        /**
         *
         * RejectedExecutionHandler
         *
         * 1、ThreadPoolExecutor.AbortPolicy()   // 直接抛出异常
         * 2、ThreadPoolExecutor.CallerRunsPolicy()  // 交给当前线程处理
         * 3、ThreadPoolExecutor.DiscardPolicy()     // 直接丢弃
         * 4、ThreadPoolExecutor.DiscardOldestPolicy()   // 丢弃任务队列中最早的任务
         */

        /**
         * BlockingQueue
         *
         * 1、SynchronousQueue        没有容量    不存储元素的阻塞队列
         * 2、LinkedBlockingQueue     无界缓存队列         使用次队列时，maximumPoolSize相当于无效    可以人为指定大小
         * 3、ArrayBlockingQueue      有界缓存队列
         * 4、PriorityBlockingQueue   无界    heap
         */

        /**
         * 1、corePoolSize  核心线程数
         * 2、maximumPoolSize   最大线程数 = 核心线程 + 非核心线程   maximumPoolSize必须大于corePoolSize
         *
         *         if (corePoolSize < 0 ||
         *             maximumPoolSize <= 0 ||
         *             maximumPoolSize < corePoolSize ||
         *             keepAliveTime < 0)
         *             throw new IllegalArgumentException();
         */

        for (int i = 0; i < 1000; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println("Thread index=" + index);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }

        @Override
        protected void terminated() {
            super.terminated();
        }
    }
}
