package ru.mirea.pr5;

/**
 * Lazy Initialization
 * Создание экземпляра класса в глобальном методе
 */
public class LazyInitializedSingleton {
    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton() {}

    public static LazyInitializedSingleton getInstance() {
        if (instance == null){
            instance = new LazyInitializedSingleton();
        }

        return instance;
    }
}
