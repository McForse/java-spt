package ru.mirea.pr6.abstractFactory;

public interface ApplicationFactory {
    Database getDatabase();
    Framework getFramework();
}
