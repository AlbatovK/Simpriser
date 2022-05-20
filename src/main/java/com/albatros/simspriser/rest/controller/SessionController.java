package com.albatros.simspriser.rest.controller;

import com.albatros.simspriser.domain.ClientInfo;
import com.albatros.simspriser.domain.QuestionInfo;
import com.albatros.simspriser.domain.Quiz;
import com.albatros.simspriser.domain.Session;
import com.albatros.simspriser.rest.dto.ClientInfoDto;
import com.albatros.simspriser.rest.dto.QuizDto;
import com.albatros.simspriser.rest.dto.response.RegisterResponse;
import com.albatros.simspriser.rest.dto.response.SendData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/session")
@RestController
public class SessionController {

    public static final List<Session> sessions = new ArrayList<>();

    @GetMapping("/api")
    public String getApiVersion() {
        return "v36 - last stable v37 - current";
    }

    @GetMapping("/register")
    public ResponseEntity registerSession(@RequestParam("quiz_id") long quizId) {
        List<Quiz> objects =
                QuizController.availableQuizzes.stream().map(QuizDto::toDomainObject).collect(Collectors.toList());
        for (Quiz quiz : objects) {
            if (quiz.getId() == quizId) {
                Session current = new Session(quiz);
                sessions.add(current);
                RegisterResponse response = new RegisterResponse(current.getId());
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/enter")
    public ResponseEntity enterSession(@RequestParam("session_id") long session_id, @RequestParam("name") String name) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                ClientInfo info = new ClientInfo(name);
                current.addClientInfo(info);
                long clientId = info.getId();
                RegisterResponse response = new RegisterResponse(clientId);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/start")
    public void startSession(@RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                current.setStarted(true);
            }
        }
    }

    @GetMapping("/{userId}/start")
    public void userStart(@PathVariable("userId") long userId, @RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                for (ClientInfo info : current.getInfo()) {
                    if (info.getId() == userId) {
                        info.setStarted(true);
                    }
                }
            }
        }
    }

    @GetMapping("/info/sessions")
    public List<Session> getSessions() {
        return sessions;
    }

    @GetMapping("/info/get/all")
    public ResponseEntity getAllInfo(@RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                List<ClientInfoDto> info
                        = current.getInfo().stream().map(ClientInfoDto::toDto).collect(Collectors.toList());
                return ResponseEntity.ok(info);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/info/started")
    public ResponseEntity hasStarted(@RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                boolean started = current.isStarted();
                return ResponseEntity.ok(started);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/quiz/get")
    public ResponseEntity getQuiz(@RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                QuizDto quiz = QuizDto.toDto(current.getQuiz());
                return ResponseEntity.ok(quiz);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/info/send", consumes = "application/json", produces = "application/json")
    public SendData sendData(@RequestParam("session_id") long session_id, @RequestBody SendData data) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                for (ClientInfo info : current.getInfo()) {
                    if (info.getId() == data.getId()) {
                        int score = data.isRight() ? 1000 : 0;
                        if (data.isRight()) score += 200 - data.getTime() * 10;
                        info.setScore(info.getScore() + score);
                        QuestionInfo qInfo = new QuestionInfo(data.isRight(), data.getTime());
                        info.getQuestionMap().put(data.getPosition(), qInfo);
                        break;
                    }
                }
            }
        }
        return data;
    }

    @GetMapping("/end")
    public void endIfExists(@RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                current.getInfo().clear();
                sessions.remove(current);
                break;
            }
        }
    }

    @GetMapping("/delete/all")
    public void deleteSessions() {
        sessions.clear();
    }
}
