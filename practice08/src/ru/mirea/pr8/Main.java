package ru.mirea.pr8;

import ru.mirea.pr8.mediator.Colleague;
import ru.mirea.pr8.mediator.ConcreteColleague1;
import ru.mirea.pr8.mediator.ConcreteMediator;
import ru.mirea.pr8.mediator.Mediator;
import ru.mirea.pr8.memento.Caretaker;
import ru.mirea.pr8.memento.Originator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Mediator:");
        Mediator mediator = new ConcreteMediator();
        mediator.setColleague1(new ConcreteColleague1());
        Colleague colleague2 = new ConcreteColleague1();
        mediator.setColleague2(colleague2);
        colleague2.send("message from a second colleague");

        System.out.println("\nMenento:");
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("on");
        System.out.println("Initial state: " + originator.getState());
        caretaker.setMemento(originator.save());
        originator.setState("off");
        System.out.println("After change: " + originator.getState());

        originator.restore(caretaker.getMemento());
        System.out.println("After restore: " + originator.getState());

    }
}
