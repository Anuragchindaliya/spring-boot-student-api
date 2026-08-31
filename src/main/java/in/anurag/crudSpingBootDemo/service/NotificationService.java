package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.NotificationResponseDTO;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    public NotificationResponseDTO send();
}
