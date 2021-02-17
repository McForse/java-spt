package ru.mirea.pr7.decorator;

public class BaseNotifier implements Notifier {
    @Override
    public void send(String data) {
        System.out.println(data);
    }
}
