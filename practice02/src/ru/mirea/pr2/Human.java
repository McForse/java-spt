package ru.mirea.pr2;

import java.time.LocalDate;

public class Human {
    private final int age;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDay;
    private final int weight;

    public Human(int age, String firstName, String lastName, LocalDate birthDay, int weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", weight=" + weight +
                '}';
    }
}
