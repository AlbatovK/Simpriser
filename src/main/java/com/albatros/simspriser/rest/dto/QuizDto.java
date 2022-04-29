package com.albatros.simspriser.rest.dto;

import com.albatros.simspriser.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizDto {

    private long id;
    private String name;
    private List<QuestionDto> questions;
    private List<String> topics;

    public QuizDto(String name, List<QuestionDto> questions, List<String> topics) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.name = name;
        this.questions = questions;
        this.topics = topics;
    }

    public static Quiz toDomainObject(QuizDto dto) {
        List<Quiz.Question> questions
                = dto.questions.stream().map(QuestionDto::toDomainObject).collect(Collectors.toList());
        return Quiz.builder()
                .id(dto.id)
                .name(dto.name)
                .questions(questions)
                .topics(dto.topics)
                .build();
    }

    public static QuizDto toDto(Quiz obj) {
        List<QuestionDto> questionsData
                = obj.getQuestions().stream().map(QuestionDto::toDto).collect(Collectors.toList());
        return builder()
                .id(obj.getId())
                .name(obj.getName())
                .questions(questionsData)
                .topics(obj.getTopics())
                .build();
    }
}
