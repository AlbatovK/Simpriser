package com.albatros.simspriser.quiz;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/quiz")
@RestController
public class QuizController {

    public static final List<Quiz> availableQuizzes = new ArrayList<>();

    static {
        /* Todo: delete after pushing */

        List<String> vars1 = new ArrayList<>();
        vars1.add("5");
        vars1.add("4");

        Quiz.Question<String> question = new Quiz.Question<>("What is 2 + 2?", vars1, "4");
        List<Quiz.Question> questions = new ArrayList<>();
        questions.add(question);

        List<String> topics = new ArrayList<>();
        topics.add("Math");
        topics.add("Education");

        Quiz quiz = new Quiz("TestQuiz", questions, topics);
        availableQuizzes.add(quiz);
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void createQuiz(@RequestBody Quiz quiz) {
        availableQuizzes.add(quiz);
    }

    @GetMapping("/delete")
    public void deleteQuiz(@RequestParam("quiz_id") long quiz_id) {
        availableQuizzes.removeIf(q -> q.getId() == quiz_id);
    }

    @GetMapping("/get/all")
    public List<Quiz> getAll() {
        return availableQuizzes;
    }
}
