package ru.mirea.pr8.mediator;

public class ConcreteColleague2 extends Colleague {
    @Override
    public void notify(String message) {
        System.out.println("Colleague2 gets message: " + message);
    }
}
