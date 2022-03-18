package com.albatros.simspriser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    public static int x = 0;

    @PostMapping(value = "/game/send", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> receiveData(@RequestParam(value = "session_id") int session_id, @RequestBody MetaInfo info) {
        Session current = null;
        for (Session s: SessionController.sessions)
            if (s.getId() == session_id)
                current = s;
        if (current == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        current.setMetaInfo(info);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/game/x")
    public ResponseEntity<Integer> x() {
        x++;
        return ResponseEntity.ok(x);
    }
}
