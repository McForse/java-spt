package ru.mirea.pr6;

import ru.mirea.pr6.abstractFactory.ApplicationFactory;
import ru.mirea.pr6.abstractFactory.Database;
import ru.mirea.pr6.abstractFactory.Framework;
import ru.mirea.pr6.abstractFactory.applications.EnterpriseApplication;
import ru.mirea.pr6.abstractFactory.applications.ModernApplication;
import ru.mirea.pr6.builder.BloggerBuilder;
import ru.mirea.pr6.builder.Director;
import ru.mirea.pr6.builder.User;
import ru.mirea.pr6.builder.UserBuilder;
import ru.mirea.pr6.factory.Employee;
import ru.mirea.pr6.factory.Department;
import ru.mirea.pr6.factory.departments.ITDepartment;
import ru.mirea.pr6.factory.departments.AccountingDepartment;
import ru.mirea.pr6.prototype.ProgrammingLanguage;

public class Main {

    public static void main(String[] args) {
        System.out.println("Фабрика:");
        Department department = new ITDepartment();
        Employee developer = department.hire();
        department = new AccountingDepartment();
        Employee accountant = department.hire();
        developer.work();
        accountant.work();

        System.out.println("\nАбстрактная фабрика:");
        ApplicationFactory application = new EnterpriseApplication();
        Database database1 = application.getDatabase();
        Framework framework1 = application.getFramework();
        database1.info();
        framework1.start();
        application = new ModernApplication();
        Database database2 = application.getDatabase();
        Framework framework2 = application.getFramework();
        database2.info();
        framework2.start();

        System.out.println("\nСтроитель:");
        UserBuilder builder = new BloggerBuilder();
        Director director = new Director(builder);
        User randomUser = director.makeRandomUser();
        System.out.println(randomUser);

        System.out.println("\nПрототип:");
        ProgrammingLanguage java = new ProgrammingLanguage("Java", "Oracle");
        System.out.println("Исходный - " + java);
        ProgrammingLanguage clone = (ProgrammingLanguage) java.clone();
        System.out.println("Клонированный - " + clone);
    }
}
