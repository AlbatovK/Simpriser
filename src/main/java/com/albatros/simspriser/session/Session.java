package com.albatros.simspriser.session;

import com.albatros.simspriser.quiz.Quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Session {

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
        this.id = Math.abs(UUID.randomUUID().getMostSignificantBits()) % 1000000;
        this.quiz = quiz;
        this.started = false;
    }

    public Session() {}

    public void addClientInfo(ClientInfo info) {
        this.info.add(info);
    }

    static class QuestionInfo {

        private boolean right;
        private int time;

        public QuestionInfo(boolean right, int time) {
            this.right = right;
            this.time = time;
        }

        public QuestionInfo() {}

        public boolean getRight() {
            return right;
        }

        public void setRight(boolean right) {
            this.right = right;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    static class ClientInfo {

        private long id;
        private String name;
        private boolean started;
        private HashMap<Integer, QuestionInfo> questionMap = new HashMap<>();

        public ClientInfo(String name) {
            this.id = UUID.randomUUID().getMostSignificantBits();
            this.name = name;
            this.started = false;
        }

        public ClientInfo() {}

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean getStarted() {
            return started;
        }

        public void setStarted(boolean started) {
            this.started = started;
        }

        public HashMap<Integer, QuestionInfo> getQuestionMap() {
            return questionMap;
        }

        public void setQuestionMap(HashMap<Integer, QuestionInfo> questionMap) {
            this.questionMap = questionMap;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public boolean getStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public List<ClientInfo> getInfo() {
        return info;
    }

    public void setInfo(List<ClientInfo> info) {
        this.info = info;
    }
}
