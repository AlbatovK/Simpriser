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
        vars1.add("3");
        vars1.add("2");

        Quiz.Question q1 = new Quiz.Question("What is 2 + 2?", vars1, "4");

        Quiz.Question q2 = new Quiz.Question("What is 2 * 2?", vars1, "4");

        Quiz.Question q3 = new Quiz.Question("What is 2 ^ 2?", vars1, "4");

        List<Quiz.Question> questions = new ArrayList<>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);

        List<String> topics = new ArrayList<>();
        topics.add("Math");
        topics.add("Education");

        Quiz quiz = new Quiz("TestQuiz", questions, topics);
        availableQuizzes.add(quiz);

        /* ####### */

        List<String> vars2 = new ArrayList<>();
        vars1.add("5");
        vars1.add("4");
        vars1.add("3");
        vars1.add("2");

        Quiz.Question q4 = new Quiz.Question("What is 3 + 3?", vars2, "4");

        Quiz.Question q5 = new Quiz.Question("What is 3 * 3?", vars2, "4");

        Quiz.Question q6 = new Quiz.Question("What is 3 ^ 3?", vars2, "4");

        List<Quiz.Question> qs = new ArrayList<>();
        qs.add(q4);
        qs.add(q5);
        qs.add(q6);

        List<String> ts = new ArrayList<>();
        ts.add("Math");
        ts.add("Educational");

        Quiz qz = new Quiz("AnotherQuiz", qs, ts);
        availableQuizzes.add(qz);
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
