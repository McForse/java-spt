package ru.mirea.pr8.mediator;

public class ConcreteColleague1 extends Colleague {
    @Override
    public void notify(String message) {
        System.out.println("Colleague1 gets message: " + message);
    }
}
