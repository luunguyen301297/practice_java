package org.example.design_pattern.singleton;

@SuppressWarnings("all")
public final class LazyInitialization {

    private LazyInitialization() {
    }

    public static LazyInitialization getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final LazyInitialization INSTANCE = new LazyInitialization();
    }

}
