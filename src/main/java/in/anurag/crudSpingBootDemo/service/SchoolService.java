package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.annotation.TrackExecutionTime;
import in.anurag.crudSpingBootDemo.dto.CreateSchoolDTO;
import in.anurag.crudSpingBootDemo.dto.CreateSchoolResponseDTO;
import in.anurag.crudSpingBootDemo.dto.CreateStudentRequestDTO;
import in.anurag.crudSpingBootDemo.dto.UpdateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.entity.School;
import in.anurag.crudSpingBootDemo.entity.Student;
import in.anurag.crudSpingBootDemo.exception.ResourceNotFoundException;
import in.anurag.crudSpingBootDemo.repository.SchoolRepository;
import in.anurag.crudSpingBootDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    public CreateSchoolDTO createSchool(CreateSchoolDTO schoolDTO) {
        School school = mapToEntity(schoolDTO);
        // using CreationTimestamp and UpdateTimestamp annotation I don't need to set Date time manually
//        LocalDateTime date = LocalDateTime.now();
//        school.setCreatedAt(date);
//        school.setUpdatedAt(date);
        school.setDeleted(false);
        schoolRepository.save(school);
        String m = "School is created";
        System.out.println(m);
        // throw new RuntimeException("Some error occurred");
        return schoolDTO;
    }

    public CreateSchoolResponseDTO getSchoolById(Long id) {
        School school = schoolRepository.findById(id);
        System.out.println("school service is started" + school);
        if (school == null) {
            throw new ResourceNotFoundException("School not found");
        }

        CreateSchoolResponseDTO schoolResponseDTO = mapToUpdateDto(school);
        return schoolResponseDTO;
    }

    // when I used Transactional annotation school.setName worked, I don't need to
    // call save method from repository
    @Transactional
    public void updateSchoolById(CreateSchoolDTO schoolDTO, Long id) {
        School school = schoolRepository.findById(id);
        school.setName(schoolDTO.getName());
        school.setAge(schoolDTO.getAge());
        school.setEmail(schoolDTO.getEmail());
        System.out.println(("student updated"));
    }

    @Transactional
    public void deleteSchool(Long id) {
        School school = schoolRepository.findById(id);
        schoolRepository.remove(school);
    }

    private School mapToEntity(CreateSchoolDTO schoolReq) {
        School school = new School();
        school.setName(schoolReq.getName());
        school.setAge(schoolReq.getAge());
        school.setEmail(schoolReq.getEmail());
        school.setRollNo(schoolReq.getRollNo());
        school.setSubject(schoolReq.getSubject());
        school.setDateOfBirth(schoolReq.getDateOfBirth());
        school.setPercentage(schoolReq.getPercentage());
        school.setStudentStatus(schoolReq.getStudentStatus());
        school.setProfileDescription(schoolReq.getProfileDescription());
        school.setMonitor(schoolReq.getMonitor());

        return school;
    }

    private CreateSchoolResponseDTO mapToUpdateDto(School schoolReq) {
        CreateSchoolResponseDTO studentRes = new CreateSchoolResponseDTO();
        studentRes.setId(schoolReq.getId());
        studentRes.setName(schoolReq.getName());
        studentRes.setAge(schoolReq.getAge());
        studentRes.setEmail(schoolReq.getEmail());
        studentRes.setRollNo(schoolReq.getRollNo());
        studentRes.setSubject(schoolReq.getSubject());
        studentRes.setMessage("School details updated successfully");
        studentRes.setUpdatedAt(schoolReq.getUpdatedAt());
        studentRes.setProfileDescription(schoolReq.getProfileDescription());
        studentRes.setDateOfBirth(schoolReq.getDateOfBirth());
        studentRes.setDisplayName(schoolReq.getDisplayName());
        studentRes.setMonitor(schoolReq.getMonitor());
        studentRes.setPercentage(schoolReq.getPercentage());
        studentRes.setStudentStatus(schoolReq.getStudentStatus());
        studentRes.setCreatedAt(schoolReq.getCreatedAt());
        return studentRes;
    }

    // @Timestamp
    @TrackExecutionTime(warnAfter = 1000, operation = "Run Dummy method")
    public String dummyMethod(String s) {
        String m = "dummyMethod is called from service";
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(m);
        // throw new RuntimeException("Some error occurred");
        return s;
    }
}
