package ru.mirea.pr10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mirea.pr10.components.Firefly;
import ru.mirea.pr10.components.Flashlight;
import ru.mirea.pr10.components.Lamp;
import ru.mirea.pr10.interfaces.Lighter;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) throw new IllegalArgumentException("You must pass the name of the bean");

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Lighter lighter = context.getBean(args[0], Lighter.class);
        lighter.doLight();

        Lighter lighter1 = context.getBean(Lamp.class);
        Lighter lighter2 = context.getBean(Flashlight.class);
        Lighter lighter3 = context.getBean(Firefly.class);
        lighter1.doLight();
        lighter2.doLight();
        lighter3.doLight();

        ((AnnotationConfigApplicationContext) context).close();
    }
}
