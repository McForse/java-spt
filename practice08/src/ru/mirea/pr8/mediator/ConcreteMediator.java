package ru.mirea.pr8.mediator;

public class ConcreteMediator implements Mediator {
    private Colleague colleague1;
    private Colleague colleague2;

    public void setColleague1(Colleague colleague) {
        colleague.setMediator(this);
        this.colleague1 = colleague;
    }

    public void setColleague2(Colleague colleague) {
        colleague.setMediator(this);
        this.colleague2 = colleague;
    }

    @Override
    public void notify(Colleague sender, String message) {
        if (sender.equals(colleague1)) {
            colleague2.notify(message);
        } else {
            colleague1.notify(message);
        }
    }
}
