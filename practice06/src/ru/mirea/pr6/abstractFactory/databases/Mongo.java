package ru.mirea.pr6.abstractFactory.databases;

import ru.mirea.pr6.abstractFactory.Database;

public class Mongo implements Database {
    @Override
    public void info() {
        System.out.println("You are running MongoDB");
    }
}
