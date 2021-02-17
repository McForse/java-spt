package ru.mirea.pr7.decorator;

public class NotifierDecorator implements Notifier {
    private final Notifier wrapper;

    public NotifierDecorator(Notifier wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void send(String data) {
        wrapper.send(data);
    }
}
