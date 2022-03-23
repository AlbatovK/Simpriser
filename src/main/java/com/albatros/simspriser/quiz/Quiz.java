package com.albatros.simspriser.quiz;

import java.util.List;
import java.util.UUID;

public class Quiz {

    private long id;
    private String name;
    private List<Question> questions;
    private List<String> topics;

    public Quiz(long id, String name, List<Question> questions, List<String> topics) {
        this.id = id;
        this.name = name;
        this.questions = questions;
        this.topics = topics;
    }

    public Quiz(String name, List<Question> questions, List<String> topics) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.name = name;
        this.questions = questions;
        this.topics = topics;
    }

    public Quiz() {}

    static class Question {

        private String description;
        private List<String> variants;
        private String answer;

        public Question(String description, List<String> variants, String answer) {
            this.description = description;
            this.variants = variants;
            this.answer = answer;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public List<String> getVariants() {
            return variants;
        }

        public void setVariants(List<String> variants) {
            this.variants = variants;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}