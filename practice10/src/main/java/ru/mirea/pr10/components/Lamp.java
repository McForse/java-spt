package ru.mirea.pr10.components;

import org.springframework.stereotype.Component;
import ru.mirea.pr10.interfaces.Lighter;

@Component
public class Lamp implements Lighter {
    @Override
    public void doLight() {
        System.out.println("Lamp gives light");
    }
}
