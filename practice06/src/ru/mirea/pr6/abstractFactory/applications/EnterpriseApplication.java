package ru.mirea.pr6.abstractFactory.applications;

import ru.mirea.pr6.abstractFactory.ApplicationFactory;
import ru.mirea.pr6.abstractFactory.Database;
import ru.mirea.pr6.abstractFactory.Framework;
import ru.mirea.pr6.abstractFactory.databases.Postgres;
import ru.mirea.pr6.abstractFactory.frameworks.Spring;

public class EnterpriseApplication implements ApplicationFactory {
    @Override
    public Database getDatabase() {
        return new Postgres();
    }

    @Override
    public Framework getFramework() {
        return new Spring();
    }
}
