package com.albatros.simspriser.quiz;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        vars2.add("5");
        vars2.add("4");
        vars2.add("3");
        vars2.add("2");

        Quiz.Question q4 = new Quiz.Question("What is 3 + 3?", vars2, "4");

        Quiz.Question q5 = new Quiz.Question("What is 3 * 3?", vars2, "4");

        List<Quiz.Question> qs = new ArrayList<>();
        qs.add(q4);
        qs.add(q5);

        List<String> ts = new ArrayList<>();
        ts.add("Math");
        ts.add("Educational");

        Quiz qz = new Quiz("NextQuiz", qs, ts);
        availableQuizzes.add(qz);

        List<String> a = Arrays.asList("110", "100", "Колодин", "69");
        List<String> b = Arrays.asList("Да", "Нет", "Колодин", "Тупой вопрос");
        List<String> c = Arrays.asList("Колодин?", "Колодин!", "Колодин...", "Колодин.");
        List<String> d = Arrays.asList("Да", "Да", "Да", "Да");

        Quiz.Question qa = new Quiz.Question("Сколько будет 77 + 33?", a, "100");
        Quiz.Question qb = new Quiz.Question("Сваливаешь с проекта?", b, "Да");
        Quiz.Question qc = new Quiz.Question("Колодин", c, "Колодин?");
        Quiz.Question qd = new Quiz.Question("Хозяйственная деятельность общества, а также совокупность отношений, складывающихся в системе производства, распределения, обмена и потребления.", d, "Нет");

        List<String> ta = Arrays.asList("Колодин");

        Quiz res = new Quiz("KolodinQuiz", Arrays.asList(qa, qb, qc, qd), ta);
        availableQuizzes.add(res);

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
