package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.CreateStudentRequestDTO;
import in.anurag.crudSpingBootDemo.dto.CreateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.dto.UpdateStudentRequestDTO;
import in.anurag.crudSpingBootDemo.dto.UpdateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.entity.Student;
import in.anurag.crudSpingBootDemo.exception.DuplicateResourceException;
import in.anurag.crudSpingBootDemo.exception.ResourceNotFoundException;
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
        String email = student.getEmail();
        if(emailExists(email)){
            throw new DuplicateResourceException("Student with email "+email+" already exist");
        }

        LocalDateTime datetime = LocalDateTime.now();
        student.setCreatedAt(datetime);
        student.setUpdatedAt(datetime);
        student.setDeleted(false);

        Student savedStudent = studentRepository.save(student);
        return mapToDto(savedStudent);
    }
    public CreateStudentResponseDTO getStudent(Long id){
        Student currentStudent = studentRepository.findByIdAndDeletedIsFalse(id).orElseThrow(()->new ResourceNotFoundException("Student with id "+id+" not found"));
//        if(currentStudent.isPresent()){
//            return currentStudent.get();
//        }

        return mapToDto(currentStudent);
    }
    public List<CreateStudentResponseDTO> getAllStudent(){
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream().map(this::mapToDto).toList();
    }

    public void deleteStudent(Long id){
        studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student with id "+id+" not found"));
        studentRepository.deleteById(id);
    }
    public void deleteStudentSoftly(Long id){
        Student foundStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Student with id "+id+" not found"));

        foundStudent.setDeleted(true);
        studentRepository.save(foundStudent);
    }

    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO studentReq){
        Student studentRes = studentRepository.findByIdAndDeletedIsFalse(id).orElseThrow(()->new ResourceNotFoundException("Student with id "+id+" not found"));
        studentRes.setName(studentReq.getName());
        studentRes.setAge(studentReq.getAge());
        studentRes.setSubject(studentReq.getSubject());
        studentRes.setRollNo(studentReq.getRollNo());
        Student updateStudentRes = studentRepository.save(studentRes);
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
    private Boolean emailExists(String email){

        return studentRepository.existsByEmail(email);
    }
}
