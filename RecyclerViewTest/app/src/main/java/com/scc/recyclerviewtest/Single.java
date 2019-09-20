package com.scc.recyclerviewtest;

public class Single {

    private static volatile Single singleton;

    private Single() {
    }

    public static Single getInstance() {
        if (singleton == null) {
            synchronized (Single.class) {
                if (singleton == null) {
                    singleton = new Single();
                }
            }
        }
        return singleton;
    }
}