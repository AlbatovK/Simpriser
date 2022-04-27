package com.albatros.simspriser.service;

import com.albatros.simspriser.dao.QuizDao;
import com.albatros.simspriser.domain.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class QuizService {

    @Autowired
    private QuizDao dao;

    public void saveQuiz(Quiz quiz) throws ExecutionException, InterruptedException {
        dao.save(quiz);
    }

    public void deleteQuiz(Quiz quiz) throws ExecutionException, InterruptedException {
        dao.delete(quiz);
    }

    public List<Quiz> getQuizzes() throws InterruptedException, ExecutionException {
        return dao.getAll();
    }
}
