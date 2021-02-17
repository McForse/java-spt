package ru.mirea.pr7.decorator;

public class EmailNotifier extends NotifierDecorator {
    public EmailNotifier(Notifier wrapper) {
        super(wrapper);
    }

    @Override
    public void send(String data) {
        super.send("Email: " + data);
    }
}
