package com.albatros.simspriser.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ClientInfo {

    private long id;
    private String name;
    private boolean started;
    private int score;
    private HashMap<Integer, QuestionInfo> questionMap = new HashMap<>();

    public ClientInfo(String name) {
        this.score = 0;
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.name = name;
        this.started = false;
    }

    public ClientInfo(long id, String name, boolean started, int score) {
        this.score = score;
        this.id = id;
        this.name = name;
        this.started = started;
    }
}