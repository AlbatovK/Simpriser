package com.albatros.simspriser.session;

import com.albatros.simspriser.quiz.Quiz;
import com.albatros.simspriser.quiz.QuizController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/session")
@RestController
public class SessionController {

    public static final List<Session> sessions = new ArrayList<>();

    private static class RegisterResponse {

        private long code;

        public RegisterResponse(long code) {
            this.code = code;
        }

        public RegisterResponse() {}

        public long getCode() {
            return code;
        }

        public void setCode(long code) {
            this.code = code;
        }
    }

    @GetMapping("/register")
    public ResponseEntity registerSession(@RequestParam("quiz_id") long quizId) {
        for (Quiz quiz : QuizController.availableQuizzes) {
            if (quiz.getId() == quizId) {
                Session current = new Session(quiz);
                sessions.add(current);
                long code = current.getId();
                RegisterResponse response = new RegisterResponse(code);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/enter")
    public ResponseEntity enterSession(
            @RequestParam("session_id") long session_id,
            @RequestParam("name") String name) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                Session.ClientInfo info = new Session.ClientInfo(name);
                current.addClientInfo(info);
                long clientId = info.getId();
                RegisterResponse response = new RegisterResponse(clientId);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}/start")
    public void userStart(
            @PathVariable("userId") long userId,
            @RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                for (Session.ClientInfo info : current.getInfo()) {
                    if (info.getId() == userId) {
                        info.setStarted(true);
                    }
                }
            }
        }
    }

    @GetMapping("/start")
    public void startSession(@RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                current.setStarted(true);
            }
        }
    }

    @GetMapping("/info/started")
    public ResponseEntity hasStarted(@RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                boolean started = current.getStarted();
                return ResponseEntity.ok(started);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/info/get/all")
    public ResponseEntity getAllInfo(@RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                List<Session.ClientInfo> info = current.getInfo();
                return ResponseEntity.ok(info);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/quiz/get")
    public ResponseEntity getQuiz(@RequestParam("session_id") long session_id) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                Quiz quiz = current.getQuiz();
                return ResponseEntity.ok(quiz);
            }
        }
        return ResponseEntity.notFound().build();
    }

    static class SendData {

        private long id;
        private int position;
        private boolean right;
        private int time;

        public SendData(long id, int position, boolean right, int time) {
            this.id = id;
            this.position = position;
            this.right = right;
            this.time = time;
        }

        public SendData() {}

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public boolean getRight() {
            return right;
        }

        public void setRight(boolean right) {
            this.right = right;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    @PostMapping(value = "/info/send", consumes = "application/json", produces = "application/json")
    public SendData sendData(
            @RequestParam("session_id") long session_id,
            @RequestBody SendData data) {
        for (Session current : sessions) {
            if (current.getId() == session_id) {
                for (Session.ClientInfo info : current.getInfo()) {
                    if (info.getId() == data.getId()) {
                        Session.QuestionInfo qInfo = new Session.QuestionInfo(data.getRight(), data.getTime());
                        info.getQuestionMap().put(data.getPosition(), qInfo);
                        break;
                    }
                }
            }
        }
        return data;
    }

    @GetMapping("/delete/all")
    public void deleteSessions() {
        sessions.clear();
    }

    @GetMapping("/info/sessions")
    public List<Session> getSessions() {
        return sessions;
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
}
