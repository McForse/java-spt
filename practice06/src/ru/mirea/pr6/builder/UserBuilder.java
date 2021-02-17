package ru.mirea.pr6.builder;

public interface UserBuilder {
    UserBuilder setName(String name);
    UserBuilder setEmail(String email);
    User getResult();
}
