package ru.mirea.pr6.factory.departments;

import ru.mirea.pr6.factory.Department;
import ru.mirea.pr6.factory.Employee;
import ru.mirea.pr6.factory.employeers.Accountant;

public class AccountingDepartment extends Department {
    @Override
    public Employee hire() {
        return new Accountant();
    }
}
