package org.example.design_pattern.singleton;

@SuppressWarnings("all")
public final class EagerInitialization {

    private static final EagerInitialization INSTANCE = initInstance();

    private EagerInitialization() {
    }

    private static EagerInitialization initInstance() {
        return new EagerInitialization();
    }

    public static EagerInitialization getInstance() {
        return INSTANCE;
    }

}
