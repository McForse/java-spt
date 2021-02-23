package ru.mirea.pr8.mediator;

public abstract class Colleague {
    protected Mediator mediator;

    protected void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void send(String message) {
        mediator.notify(this, message);
    }

    public abstract void notify(String message);
}
