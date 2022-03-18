package com.albatros.simspriser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SessionController {

    public static final List<Session> sessions = new ArrayList<>();

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
