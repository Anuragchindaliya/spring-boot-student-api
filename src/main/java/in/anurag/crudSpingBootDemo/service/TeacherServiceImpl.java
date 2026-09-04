package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.CreateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.dto.CreateTeacherRequestDTO;
import in.anurag.crudSpingBootDemo.dto.CreateTeacherResponseDTO;
import in.anurag.crudSpingBootDemo.entity.Teacher;
import in.anurag.crudSpingBootDemo.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    public TeacherServiceImpl(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    @Override
    public CreateTeacherResponseDTO createTeacher(CreateTeacherRequestDTO teacherReq) {
        System.out.println("reached to implementation");
        Teacher teacher = mapToEntity(teacherReq);

        LocalDateTime datetime = LocalDateTime.now();
        teacher.setCreatedAt(datetime);
        teacher.setUpdatedAt(datetime);
        teacher.setDeleted(false);

        teacherRepository.save(teacher);

        CreateTeacherResponseDTO teacherResponse = new CreateTeacherResponseDTO();
        teacherResponse.setName(teacher.getName());
        teacherResponse.setAge(teacher.getAge());
        teacherResponse.setEmail(teacher.getEmail());
        teacherResponse.setRollNo(teacher.getRollNo());
        teacherResponse.setMessage("Teacher Created Successfully.");
        return teacherResponse;
    }

    private Teacher mapToEntity(CreateTeacherRequestDTO teacherReq){
        Teacher teacher = new Teacher();
        teacher.setName(teacherReq.getName());
        teacher.setAge(teacherReq.getAge());
        teacher.setEmail(teacherReq.getEmail());
        teacher.setRollNo(teacherReq.getRollNo());
        teacher.setSubject(teacherReq.getSubject());
        return teacher;
    }
    private CreateTeacherResponseDTO mapToDto(Teacher studentReq){
        CreateTeacherResponseDTO studentRes = new CreateTeacherResponseDTO();
        studentRes.setId(studentReq.getId());
        studentRes.setName(studentReq.getName());
        studentRes.setAge(studentReq.getAge());
        studentRes.setEmail(studentReq.getEmail());
        studentRes.setRollNo(studentReq.getRollNo());
        studentRes.setSubject(studentReq.getSubject());
        studentRes.setMessage("Teacher saved successfully");
        studentRes.setCreatedAt(studentReq.getCreatedAt());
        studentRes.setUpdatedAt(studentReq.getUpdatedAt());
        return studentRes;
    }

}
