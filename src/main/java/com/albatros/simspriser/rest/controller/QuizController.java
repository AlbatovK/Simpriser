package com.albatros.simspriser.rest.controller;

import com.albatros.simspriser.domain.Quiz;
import com.albatros.simspriser.rest.dto.QuizDto;
import com.albatros.simspriser.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequestMapping("/quiz")
@RestController
@RequiredArgsConstructor
public class QuizController {

    @Autowired
    private final QuizService service;
    public static final List<QuizDto> availableQuizzes = new ArrayList<>();

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void createQuiz(@RequestBody QuizDto dto) throws ExecutionException, InterruptedException {
        Quiz obj = QuizDto.toDomainObject(dto);
        service.saveQuiz(obj);
    }

    @GetMapping("/delete")
    public void deleteQuiz(@RequestParam("quiz_id") long quiz_id) throws ExecutionException, InterruptedException {
        List<Quiz> objects
                = availableQuizzes.stream().map(QuizDto::toDomainObject).collect(Collectors.toList());
        for (Quiz quiz : objects)
            if (quiz.getId() == quiz_id)
                service.deleteQuiz(quiz);
    }

    @GetMapping("/get/all")
    public List<QuizDto> getAll() throws ExecutionException, InterruptedException {
        List<QuizDto> quizzes
                = service.getQuizzes().stream().map(QuizDto::toDto).collect(Collectors.toList());
        availableQuizzes.addAll(quizzes);
        return quizzes;
    }
}
