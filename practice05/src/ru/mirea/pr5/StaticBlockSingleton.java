package ru.mirea.pr5;

/**
 * Static block initialization
 * Экземпляр класса создается в статическом блоке
 */
public class StaticBlockSingleton {
    private static final StaticBlockSingleton instance;

    private StaticBlockSingleton() {}

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch(Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
