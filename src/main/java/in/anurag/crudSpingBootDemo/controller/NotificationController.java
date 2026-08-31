package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.dto.NotificationResponseDTO;
import in.anurag.crudSpingBootDemo.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationResponseDTO> sendNotification(){
        System.out.println("notification sent");
        return ResponseEntity.ok(notificationService.send());
    }
}
