package ru.mirea.pr5;

public class Main {

    public static void main(String[] args) {
        System.out.println("EagerInitializedSingleton");
        System.out.println(EagerInitializedSingleton.getInstance());
        System.out.println(EagerInitializedSingleton.getInstance());

        System.out.println("\nStaticBlockSingleton");
        System.out.println(StaticBlockSingleton.getInstance());
        System.out.println(StaticBlockSingleton.getInstance());

        System.out.println("\nLazyInitializedSingleton");
        System.out.println(LazyInitializedSingleton.getInstance());
        System.out.println(LazyInitializedSingleton.getInstance());

        System.out.println("\nThreadSafeSingleton");
        System.out.println(ThreadSafeSingleton.getInstance());
        System.out.println(ThreadSafeSingleton.getInstanceUsingDoubleLocking());

        System.out.println("\nEnumSingleton");
        System.out.println(EnumSingleton.getInstance());
        System.out.println(EnumSingleton.getInstance());
    }
}
