package ru.mirea.pr5;

/**
 * Enum Singleton
 * С исполльзованием перечесления
 */
public enum EnumSingleton {
    INSTANCE;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
