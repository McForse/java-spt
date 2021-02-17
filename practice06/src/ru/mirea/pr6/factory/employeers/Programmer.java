package ru.mirea.pr6.factory.employeers;

import ru.mirea.pr6.factory.Employee;

public class Programmer implements Employee {
    @Override
    public void work() {
        System.out.println("Programmer writes code");
    }
}
