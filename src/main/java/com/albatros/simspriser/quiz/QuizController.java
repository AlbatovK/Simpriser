package com.albatros.simspriser.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequestMapping("/quiz")
@RestController
public class QuizController {

    public static final List<Quiz> availableQuizzes = new ArrayList<>();

    @Autowired
    QuizService service;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void createQuiz(@RequestBody Quiz quiz) throws ExecutionException, InterruptedException {
        service.saveQuiz(quiz);
    }

    @GetMapping("/delete")
    public void deleteQuiz(@RequestParam("quiz_id") long quiz_id) throws ExecutionException, InterruptedException {
        for (Quiz quiz : availableQuizzes)
            if (quiz.getId() == quiz_id)
                service.deleteQuiz(quiz);
    }

    @GetMapping("/get/all")
    public List<Quiz> getAll() throws ExecutionException, InterruptedException {
        List<Quiz> quizzes = service.getQuizzes();
        availableQuizzes.addAll(quizzes);
        return quizzes;
    }
}
