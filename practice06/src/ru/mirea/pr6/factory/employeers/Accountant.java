package ru.mirea.pr6.factory.employeers;

import ru.mirea.pr6.factory.Employee;

public class Accountant implements Employee {
    @Override
    public void work() {
        System.out.println("Accountant keeps or examines the records of money received");
    }
}
