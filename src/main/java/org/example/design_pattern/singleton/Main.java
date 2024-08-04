package org.example.design_pattern.singleton;

public class Main {

    public static void main(String[] args) {
        EagerInitialization eagerInitialization = EagerInitialization.getInstance();
        System.err.println(eagerInitialization.hashCode());

        LazyInitialization lazyInitialization = LazyInitialization.getInstance();
        System.err.println(lazyInitialization.hashCode());
    }

}
