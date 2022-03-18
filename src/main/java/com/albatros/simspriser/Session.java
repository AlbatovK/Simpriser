package com.albatros.simspriser;

public class Session {

    public static int static_id = 0;

    public Session() {
        id = static_id;
        static_id++;
    }

    public void setMetaInfo(MetaInfo info) {

    }

    private final int id;

    public int getId() {
        return id;
    }
}
