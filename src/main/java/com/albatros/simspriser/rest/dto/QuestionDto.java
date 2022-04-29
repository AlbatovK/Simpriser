package com.albatros.simspriser.rest.dto;

import com.albatros.simspriser.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {
    private String description;
    private List<String> variants;
    private String answer;

    public static Quiz.Question toDomainObject(QuestionDto dto) {
        return Quiz.Question.builder()
                .description(dto.description)
                .variants(dto.variants)
                .answer(dto.answer)
                .build();
    }

    public static QuestionDto toDto(Quiz.Question obj) {
        return builder()
                .description(obj.getDescription())
                .variants(obj.getVariants())
                .answer(obj.getAnswer())
                .build();
    }
}
