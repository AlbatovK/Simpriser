package com.albatros.simspriser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SessionController {

    public static final List<Session> sessions = new ArrayList<>();

    public static final List<Data> data = new ArrayList<>();

    static {
        data.add(new Data("add login!"));
    }

    static class Data {
        private String login;

        public Data(String login) {
            this.login = login;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }

    @GetMapping(value = "/send")
    public void addLogin(@RequestParam(value="login") String login) {
        SessionController.data.add(new Data((login)));
    }

    @GetMapping(value = "/get")
    public List<Data> getLogins() {
        return data;
    }


    @GetMapping(value = "/session/register")
    public ResponseEntity<Integer> registerSession() {
        Session current = new Session();
        sessions.add(current);
        Integer id = current.getId();
        return ResponseEntity.ok(id);
    }

    @GetMapping(value = "/session/find")
    public ResponseEntity<Integer> findSession(@RequestParam(value="id") int id) {
        for (Session s: sessions)
            if (s.getId() == id)
                return ResponseEntity.ok(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
