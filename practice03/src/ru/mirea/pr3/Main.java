package ru.mirea.pr3;

import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Set<Integer> set = new SetSynchronized<>();
        System.out.println("Set with synchronized:");
        testSet(set);

        Map<Integer, Integer> map = new MapLock<>();
        System.out.println("Map with lock:");
        testMap(map);
    }

    public static void testSet(Set<Integer> set) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) set.add(i);
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 51; i < 100; i++) set.add(i);
        });

        thread1.start();
        thread2.start();
        Thread.sleep(2000);

        set.stream().forEach(System.out::println);
    }

    public static void testMap(Map<Integer, Integer> map) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) map.put(i, i);
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 51; i < 100; i++) map.put(i, i);
        });

        thread1.start();
        thread2.start();
        Thread.sleep(2000);

        map.entrySet().stream().forEach(System.out::println);
    }
}
