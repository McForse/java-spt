package ru.mirea.pr5;

/**
 * Eager Initialization
 * Экземпляр класса создаётся во время загрузки класса
 */
public class EagerInitializedSingleton {
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    private EagerInitializedSingleton() {}

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
}
