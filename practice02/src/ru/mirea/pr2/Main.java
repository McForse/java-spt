package ru.mirea.pr2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        List<Human> humans = new ArrayList<Human>() {{
            add(new Human(21, "Ivan", "Ivanov", LocalDate.of(2000, 12, 12), 70));
            add(new Human(22, "Peter", "Petrov", LocalDate.of(1999, 10, 23), 75));
            add(new Human(19, "Timur", "Timurovich", LocalDate.of(2001, 7, 10), 80));
            add(new Human(21, "Danil", "Danilovich", LocalDate.of(2000, 1, 1), 67));
            add(new Human(25, "Kiril", "Kirillovich", LocalDate.of(1996, 2, 4), 70));
        }};

        Function<String, Character> getSecondLetter = x -> x.charAt(1);
        Function<Human, Character> getHSecondLetter = h -> getSecondLetter.apply(h.getFirstName());
        Function<Human, Integer> multiplyWeightAndAge = h -> h.getWeight() * h.getAge();

        System.out.println("Сортировка по второй букве имени:");
        humans.stream().sorted(Comparator.comparing(getHSecondLetter)).forEach(System.out::println);

        System.out.println("\nФильтрация по весу кратно 10:");
        humans.stream().filter(h -> h.getWeight() % 10 == 0).forEach(System.out::println);

        System.out.println("\nСортировка по произведению веса на возраст:");
        humans.stream().sorted(Comparator.comparing(multiplyWeightAndAge)).forEach(System.out::println);

        System.out.println("\nПроизведение всех весов:");
        humans.stream()
                .mapToInt(Human::getWeight).reduce((h1, h2) -> h1 * h2)
                .ifPresent(System.out::println);
    }
}
