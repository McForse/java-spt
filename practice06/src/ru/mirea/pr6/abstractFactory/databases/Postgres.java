package ru.mirea.pr6.abstractFactory.databases;

import ru.mirea.pr6.abstractFactory.Database;

public class Postgres implements Database {
    @Override
    public void info() {
        System.out.println("You are running PostgreSQL");
    }
}
