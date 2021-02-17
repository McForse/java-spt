package ru.mirea.pr6.abstractFactory.applications;

import ru.mirea.pr6.abstractFactory.ApplicationFactory;
import ru.mirea.pr6.abstractFactory.Database;
import ru.mirea.pr6.abstractFactory.Framework;
import ru.mirea.pr6.abstractFactory.databases.Mongo;
import ru.mirea.pr6.abstractFactory.frameworks.Django;

public class ModernApplication implements ApplicationFactory {
    @Override
    public Database getDatabase() {
        return new Mongo();
    }

    @Override
    public Framework getFramework() {
        return new Django();
    }
}
