package ru.mirea.pr5;

/**
 * Thread Safe Singleton
 */
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    synchronized public static ThreadSafeSingleton getInstance() {
        if (instance == null){
            instance = new ThreadSafeSingleton();
        }

        return instance;
    }

    /**
     * Thread Safe Singleton with double locking
     * @return ThreadSafeSingleton
     */
    public static ThreadSafeSingleton getInstanceUsingDoubleLocking() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null){
                    instance = new ThreadSafeSingleton();
                }
            }
        }

        return instance;
    }
}
