package com.albatros.simspriser.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Session {

    private static final long modValue = 1_000_000;

    private long id;
    private Quiz quiz;
    private boolean started;
    private List<ClientInfo> info = new ArrayList<>();

    public Session(long id, Quiz quiz, boolean started) {
        this.id = id;
        this.quiz = quiz;
        this.started = started;
    }

    public Session(Quiz quiz) {
        long raw = UUID.randomUUID().getMostSignificantBits();
        this.id = Math.abs(raw) % modValue;
        this.quiz = quiz;
        this.started = false;
    }

    public void addClientInfo(ClientInfo info) {
        this.info.add(info);
    }
}
