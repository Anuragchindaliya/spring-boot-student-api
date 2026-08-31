package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.NotificationResponseDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class NotificationServiceImpl implements NotificationService {
    @Override
    public NotificationResponseDTO send(){
//        try{
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        NotificationResponseDTO responseDTO = new NotificationResponseDTO();
        responseDTO.setName("Prod");
        responseDTO.setMessage("Prod Notification sent successfully");
        return responseDTO;
    }
}
