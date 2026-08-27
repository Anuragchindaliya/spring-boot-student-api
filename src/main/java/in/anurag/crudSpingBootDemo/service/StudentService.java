package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.CreateStudentRequestDTO;
import in.anurag.crudSpingBootDemo.dto.CreateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.dto.UpdateStudentRequestDTO;
import in.anurag.crudSpingBootDemo.dto.UpdateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.entity.Student;
import in.anurag.crudSpingBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentReq) {
        Student student = mapToEntity(studentReq);
        LocalDateTime datetime = LocalDateTime.now();
        student.setCreatedAt(datetime);
        student.setUpdatedAt(datetime);
        student.setDeleted(false);

        Student savedStudent = studentRepository.save(student);
        return mapToDto(savedStudent);
    }
    public Student getStudent(Long id){
        Optional<Student> currentStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(currentStudent.isPresent()){
            return currentStudent.get();
        }
        return null;
    }
    public List<CreateStudentResponseDTO> getAllStudent(){
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream().map(this::mapToDto).toList();
    }

    public Boolean deleteStudent(Long id){
        boolean isExistStudent = studentRepository.existsById(id);
        if(!isExistStudent){
            return false;
        }
        studentRepository.deleteById(id);

        return true;

    }
    public Boolean deleteStudentSoftly(Long id){
        Optional<Student> isExistStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(isExistStudent.isEmpty()){
            return false;
        }
        Student foundStudent = isExistStudent.get();
        foundStudent.setDeleted(true);
        studentRepository.save(foundStudent);

        return true;

    }

    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO studentReq){
        Optional<Student> studentRes = studentRepository.findById(id);
        if(studentRes.isEmpty()){
            return null;
        }
        Student existingStudent = studentRes.get();
        existingStudent.setName(studentReq.getName());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setRollNo(studentReq.getRollNo());
        Student updateStudentRes = studentRepository.save(existingStudent);
        return mapToUpdateDto(updateStudentRes);
    }
    private Student mapToEntity(CreateStudentRequestDTO studentReq){
        Student student = new Student();
        student.setName(studentReq.getName());
        student.setAge(studentReq.getAge());
        student.setEmail(studentReq.getEmail());
        student.setRollNo(studentReq.getRollNo());
        student.setSubject(studentReq.getSubject());
        return student;
    }
    private CreateStudentResponseDTO mapToDto(Student studentReq){
        CreateStudentResponseDTO studentRes = new CreateStudentResponseDTO();
        studentRes.setId(studentReq.getId());
        studentRes.setName(studentReq.getName());
        studentRes.setAge(studentReq.getAge());
        studentRes.setEmail(studentReq.getEmail());
        studentRes.setRollNo(studentReq.getRollNo());
        studentRes.setSubject(studentReq.getSubject());
        studentRes.setMessage("Student saved successfully");
        studentRes.setCreatedAt(studentReq.getCreatedAt());
        studentRes.setUpdatedAt(studentReq.getUpdatedAt());
        return studentRes;
    }
    private UpdateStudentResponseDTO mapToUpdateDto(Student studentReq){
        UpdateStudentResponseDTO studentRes = new UpdateStudentResponseDTO();
        studentRes.setId(studentReq.getId());
        studentRes.setName(studentReq.getName());
        studentRes.setAge(studentReq.getAge());
        studentRes.setEmail(studentReq.getEmail());
        studentRes.setRollNo(studentReq.getRollNo());
        studentRes.setSubject(studentReq.getSubject());
        studentRes.setMessage("Student details updated successfully");
        studentRes.setUpdatedAt(studentReq.getUpdatedAt());
        return studentRes;
    }
}
