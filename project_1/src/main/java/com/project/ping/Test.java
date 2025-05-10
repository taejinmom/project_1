package com.project.ping;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class Test {

    @GetMapping("/ping")
    public ResponseEntity ping() {
        return ResponseEntity.ok("pong4");
    }
}
