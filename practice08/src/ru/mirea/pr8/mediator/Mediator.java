package ru.mirea.pr8.mediator;

public interface Mediator {
    void setColleague1(Colleague colleague);
    void setColleague2(Colleague colleague);
    void notify(Colleague sender, String message);
}
