package com.ninegame.bird.tool;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Field;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lvrh on 15/11/15.
 */
// an asynchronous task executor(thread pool)
public class TaskExecutor {
    private static ExecutorService sThreadPoolExecutor = null;
    private static ScheduledThreadPoolExecutor sScheduledThreadPoolExecutor = null;
    private static Handler sMainHandler = null;

    public static void executeTask(Runnable task) {
        ensureThreadPoolExecutor();
        sThreadPoolExecutor.execute(task);
    }

    public static <T> Future<T> submitTask(Callable<T> task) {
        ensureThreadPoolExecutor();
        return sThreadPoolExecutor.submit(task);
    }

    public static ScheduledFuture<?> scheduleTask(long delay, Runnable task) {
        ensureScheduledThreadPoolExecutor();
        return sScheduledThreadPoolExecutor.schedule(task, delay, TimeUnit.MILLISECONDS);
    }

//    public static void scheduleTaskAtFixedRateIgnoringTaskRunningTime(long initialDelay, long period, Runnable task) {
//        ensureScheduledThreadPoolExecutor();
//        sScheduledThreadPoolExecutor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
//    }
//
//    public static void scheduleTaskAtFixedRateIncludingTaskRunningTime(long initialDelay, long period, Runnable task) {
//        ensureScheduledThreadPoolExecutor();
//        sScheduledThreadPoolExecutor.scheduleWithFixedDelay(task, initialDelay, period, TimeUnit.MILLISECONDS);
//    }

    public static void scheduleTaskOnUiThread(long delay, Runnable task) {
        ensureMainHandler();
        sMainHandler.postDelayed(task, delay);
    }

    public static void runTaskOnUiThread(Runnable task) {
        ensureMainHandler();
        sMainHandler.post(task);
    }

    private synchronized static void ensureMainHandler() {
        if (sMainHandler == null)
            sMainHandler = new Handler(Looper.getMainLooper());
        /*StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if( stackTraceElements!=null ) {
            if( stackTraceElements.length > 5) {
                Thread.currentThread().setName( stackTraceElements[4].getClassName() + "|" + stackTraceElements[4].getMethodName() + "|" + stackTraceElements[4].getLineNumber());
            } else if ( stackTraceElements.length > 0 ) {
                int index = stackTraceElements.length-1;
                Thread.currentThread().setName( stackTraceElements[index].getClassName() + "|" + stackTraceElements[index].getMethodName() + "|" + stackTraceElements[index].getLineNumber());
            } else {
                Thread.currentThread().setName("unkonwn class");
            }

        }*/
    }

    private synchronized static void ensureThreadPoolExecutor() {
        if (sThreadPoolExecutor == null) {
            sThreadPoolExecutor = Executors.newFixedThreadPool(10);
//            sThreadPoolExecutor = new ThreadPoolExecutor(5, 5,
//                    60L, TimeUnit.SECONDS,
//                    new LinkedBlockingQueue<Runnable>(),
//                    Executors.defaultThreadFactory());
        }
    }

    private synchronized static void ensureScheduledThreadPoolExecutor() {
        if (sScheduledThreadPoolExecutor == null) {
            sScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        }
    }

    public static void shutdown() {
        if (sThreadPoolExecutor != null) {
            sThreadPoolExecutor.shutdown();
            sThreadPoolExecutor = null;
        }

        if (sScheduledThreadPoolExecutor != null) {
            sScheduledThreadPoolExecutor.shutdown();
            sScheduledThreadPoolExecutor = null;
        }
    }

    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
    private static final int DEFAULT_KEEP_ALIVE = 1;
    private static volatile Executor executor;

    private static final Object LOCK = new Object();

    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE =
            new LinkedBlockingQueue<Runnable>(10);

    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "TaskExecutor #" + counter.incrementAndGet());
        }
    };

    public static Executor getExecutor() {
        synchronized (LOCK) {
            if (TaskExecutor.executor == null) {
                Executor executor = getAsyncTaskExecutor();
                if (executor == null) {
                    executor = new ThreadPoolExecutor(
                            DEFAULT_CORE_POOL_SIZE, DEFAULT_MAXIMUM_POOL_SIZE, DEFAULT_KEEP_ALIVE,
                            TimeUnit.SECONDS, DEFAULT_WORK_QUEUE, DEFAULT_THREAD_FACTORY);
                }
                TaskExecutor.executor = executor;
            }
        }
        return TaskExecutor.executor;
    }

    private static Executor getAsyncTaskExecutor() {
        Field executorField = null;
        try {
            executorField = AsyncTask.class.getField("THREAD_POOL_EXECUTOR");
        } catch (NoSuchFieldException e) {
            return null;
        }

        Object executorObject = null;
        try {
            executorObject = executorField.get(null);
        } catch (IllegalAccessException e) {
            return null;
        }

        if (executorObject == null) {
            return null;
        }

        if (!(executorObject instanceof Executor)) {
            return null;
        }

        return (Executor) executorObject;
    }
}
