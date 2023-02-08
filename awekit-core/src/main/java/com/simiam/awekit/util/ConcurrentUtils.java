package com.simiam.awekit.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.*;

/**
 * <p>Title: ConcurrentUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2022</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2022/1/20 18:40</p>
 */
public class ConcurrentUtils {
    /**
     * 默认构造方法
     */
    private ConcurrentUtils() {
    }

    /**
     * @param threadPoolName 线程池名
     * @return ExecutorService
     */
    public static ExecutorService newSingleThreadExecutor(String threadPoolName) {
        return Executors.newSingleThreadExecutor(newThreadFactory(threadPoolName));
    }

    /**
     * @param corePoolSize    初始线程数
     * @param maximumPoolSize 最大线程数
     * @param queue           task缓存队列
     * @param threadFactory   线程创建工厂
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                                              BlockingQueue<Runnable> queue,
                                                              ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0, TimeUnit.MILLISECONDS, queue, threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 创建一个线程池，该线程池中用来缓存Runnable实例的队列容量理论上为无上限的。
     *
     * @param corePoolSize    初始线程数
     * @param maximumPoolSize 最大线程数
     * @param threadFactory   线程创建工厂
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPoolWithoutCapacity(int corePoolSize, int maximumPoolSize,
                                                                     ThreadFactory threadFactory) {
        return createThreadPoolExecutor(corePoolSize, maximumPoolSize, new LinkedBlockingDeque<>(),
                threadFactory);
    }

    /**
     * 创建一个线程池，该线程池中用来缓存Runnable实例的队列容量为maximumPoolSize。
     *
     * @param corePoolSize    初始线程数
     * @param maximumPoolSize 最大线程数
     * @param threadFactory   线程创建工厂
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                                              ThreadFactory threadFactory) {
        return createThreadPoolExecutor(corePoolSize, maximumPoolSize, new ArrayBlockingQueue<>
                (maximumPoolSize), threadFactory);
    }

    /**
     * @param corePoolSize    初始线程数
     * @param maximumPoolSize 最大线程数
     * @param threadFactory   线程创建工厂
     * @param handler         线程池满情况下的执行策略
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                                              ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(maximumPoolSize), threadFactory, handler);
    }

    /**
     * 创建一个线程池，该线程池中用来缓存Runnable实例的队列容量为maximumPoolSize。
     *
     * @param corePoolSize    初始线程数
     * @param maximumPoolSize 最大线程数
     * @param threadPoolName  线程池名
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                                              String threadPoolName) {
        return createThreadPoolExecutor(corePoolSize, maximumPoolSize, new ArrayBlockingQueue<>
                (maximumPoolSize), threadPoolName);
    }

    /**
     * 创建一个线程池，该线程池中用来缓存Runnable实例的队列容量理论上为无上限的。
     *
     * @param corePoolSize    初始线程数
     * @param maximumPoolSize 最大线程数
     * @param threadPoolName  线程池名
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPoolWithoutCapacity(int corePoolSize, int maximumPoolSize,
                                                                     String threadPoolName) {
        return createThreadPoolExecutor(corePoolSize, maximumPoolSize, new LinkedBlockingDeque<>(),
                threadPoolName);
    }

    /**
     * @param corePoolSize    初始线程数
     * @param maximumPoolSize 最大线程数
     * @param queue           task缓存队列
     * @param threadPoolName  线程池名
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                                              BlockingQueue<Runnable> queue,
                                                              String threadPoolName) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0, TimeUnit.MILLISECONDS, queue,
                newThreadFactory(threadPoolName), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * @param corePoolSize    初始线程数
     * @param maximumPoolSize 最大线程数
     * @param threadPoolName  线程池名
     * @param handler         线程池满情况下的执行策略
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                                              String threadPoolName, RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(maximumPoolSize), newThreadFactory(threadPoolName), handler);
    }

    /**
     * 线程池缓存
     * Map<String,ThreadPoolExecutor> poolCache
     */
    private static Map<String, ThreadPoolExecutor> POOLCACHE = new ConcurrentHashMap<>();

    /**
     * 创建线程池
     *
     * @param poolName 线程池名
     * @param coreSize 最小线程数
     * @param maxSize  最大线程数
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPool(String poolName, int coreSize, int maxSize) {
        ThreadPoolExecutor existPoolExecutor = getThreadPool(poolName);
        if (existPoolExecutor == null) {
            synchronized (POOLCACHE) {
                existPoolExecutor = getThreadPool(poolName);
                if (existPoolExecutor == null) {
                    existPoolExecutor = createThreadPoolExecutor(coreSize, maxSize, poolName);
                    POOLCACHE.put(poolName, existPoolExecutor);
                    existPoolExecutor.setCorePoolSize(coreSize);
                    existPoolExecutor.setMaximumPoolSize(maxSize);
                }
            }
        }

        return existPoolExecutor;
    }

    /**
     * 创建线程池
     *
     * @param poolName     线程池名
     * @param coreSize     最小线程数
     * @param maxSize      最大线程数
     * @param withCapacity 是否使用无上限的task缓存队列
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor createThreadPool(String poolName, int coreSize, int maxSize,
                                                      boolean withCapacity) {
        ThreadPoolExecutor existPoolExecutor = getThreadPool(poolName);
        if (existPoolExecutor == null) {
            synchronized (POOLCACHE) {
                existPoolExecutor = getThreadPool(poolName);
                if (existPoolExecutor == null) {
                    if (withCapacity) {
                        existPoolExecutor = createThreadPoolExecutor(coreSize, maxSize, poolName);
                    } else {
                        existPoolExecutor = createThreadPoolWithoutCapacity(coreSize, maxSize, poolName);
                    }
                    POOLCACHE.put(poolName, existPoolExecutor);
                    existPoolExecutor.setCorePoolSize(coreSize);
                    existPoolExecutor.setMaximumPoolSize(maxSize);
                }
            }
        }

        return existPoolExecutor;
    }

    /**
     * 从缓存中获取已创建线程池
     *
     * @param poolName 线程池名
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor getThreadPool(String poolName) {
        return POOLCACHE.get(poolName);
    }

    /**
     * 从缓存中移除已创建线程池
     *
     * @param poolName 线程池名
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor removeThreadPool(String poolName) {
        return POOLCACHE.remove(poolName);
    }

    public static ThreadFactory newThreadFactory(String nameFormat) {
        return new ThreadFactoryBuilder().setNameFormat(nameFormat + "-%d").build();
    }
}
