package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.NotificationResponseDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev","default","staging"})
public class DummyNotificationServiceImpl implements NotificationService {

    @Override
    public NotificationResponseDTO send() {
        NotificationResponseDTO responseDTO = new NotificationResponseDTO();
        responseDTO.setName("Default/Dev/Staging");
        responseDTO.setMessage("Dummy Notification sent successfully");
        return responseDTO;
    }
}
