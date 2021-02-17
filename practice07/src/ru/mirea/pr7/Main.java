package ru.mirea.pr7;

import ru.mirea.pr7.decorator.BaseNotifier;
import ru.mirea.pr7.decorator.EmailNotifier;
import ru.mirea.pr7.decorator.Notifier;
import ru.mirea.pr7.facade.Computer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Decorator:");
        Notifier baseNotifier = new BaseNotifier();
        baseNotifier.send("Some message");
        Notifier emailNotifier = new EmailNotifier(baseNotifier);
        emailNotifier.send("Another message");

        System.out.println("\nFacade:");
        Computer computer = new Computer();
        computer.run();
    }
}
