package ru.mirea.pr6.abstractFactory.frameworks;

import ru.mirea.pr6.abstractFactory.Framework;

public class Django implements Framework {
    @Override
    public void start() {
        System.out.println("Django framework started");
    }
}
