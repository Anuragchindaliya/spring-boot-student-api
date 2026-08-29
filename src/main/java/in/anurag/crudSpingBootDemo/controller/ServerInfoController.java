package in.anurag.crudSpingBootDemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server-info")
public class ServerInfoController {
    @Value("${spring.application.name}")
    private String message;
    @GetMapping
    public ResponseEntity<String> getServerInfo(){
        return ResponseEntity.ok(message);
    }
}
