package com.albatros.simspriser.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    private long id;
    private String name;
    private List<Question> questions;
    private List<String> topics;

    public Quiz(String name, List<Question> questions, List<String> topics) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.name = name;
        this.questions = questions;
        this.topics = topics;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Question {
        private String description;
        private List<String> variants;
        private String answer;
    }
}