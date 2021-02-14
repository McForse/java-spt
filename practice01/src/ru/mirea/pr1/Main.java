package ru.mirea.pr1;

import java.util.function.Function;

public class Main {

    public static void main(String[] arg) {
        Function<Integer[], Integer> nod = list -> {
            int a = list[0];
            int b = list[1];

            while (b != 0) {
                int tmp = a % b;
                a = b;
                b = tmp;
            }

            return a;
        };

        System.out.println(nod.apply(new Integer[] {18, 6}));
    }
}
