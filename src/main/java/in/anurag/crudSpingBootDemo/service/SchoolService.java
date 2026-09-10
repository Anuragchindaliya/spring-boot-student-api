package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.annotation.TrackExecutionTime;
import in.anurag.crudSpingBootDemo.dto.CreateSchoolDTO;
import in.anurag.crudSpingBootDemo.dto.CreateSchoolResponseDTO;
import in.anurag.crudSpingBootDemo.dto.CreateStudentRequestDTO;
import in.anurag.crudSpingBootDemo.dto.UpdateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.entity.School;
import in.anurag.crudSpingBootDemo.entity.Student;
import in.anurag.crudSpingBootDemo.repository.SchoolRepository;
import in.anurag.crudSpingBootDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository){
         this.schoolRepository = schoolRepository;
    }

    @Transactional
    public CreateSchoolDTO createSchool(CreateSchoolDTO schoolDTO){
        School school = mapToEntity(schoolDTO);
        LocalDateTime date = LocalDateTime.now();
        school.setCreatedAt(date);
        school.setUpdatedAt(date);
        schoolRepository.save(school);
        String m = "School is created";
        System.out.println(m);
//        throw new RuntimeException("Some error occurred");
        return schoolDTO;
    }

    public CreateSchoolResponseDTO getSchoolById (Long id){
        School school = schoolRepository.findById(id);
        CreateSchoolResponseDTO schoolResponseDTO = mapToUpdateDto(school);
        return schoolResponseDTO;
    }
//    @Timestamp
    @TrackExecutionTime(warnAfter = 1000, operation = "Run Dummy method")
    public String dummyMethod(String s){
        String m = "dummyMethod is called from service";
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(m);
//        throw new RuntimeException("Some error occurred");
        return s;
    }

    private School mapToEntity(CreateSchoolDTO schoolReq){
        School school = new School();
        school.setName(schoolReq.getName());
        school.setAge(schoolReq.getAge());
        school.setEmail(schoolReq.getEmail());
        school.setRollNo(schoolReq.getRollNo());
        school.setSubject(schoolReq.getSubject());
        return school;
    }
    private CreateSchoolResponseDTO mapToUpdateDto(School schoolReq){
        CreateSchoolResponseDTO studentRes = new CreateSchoolResponseDTO();
        studentRes.setId(schoolReq.getId());
        studentRes.setName(schoolReq.getName());
        studentRes.setAge(schoolReq.getAge());
        studentRes.setEmail(schoolReq.getEmail());
        studentRes.setRollNo(schoolReq.getRollNo());
        studentRes.setSubject(schoolReq.getSubject());
        studentRes.setMessage("School details updated successfully");
        studentRes.setUpdatedAt(schoolReq.getUpdatedAt());
        return studentRes;
    }
}
