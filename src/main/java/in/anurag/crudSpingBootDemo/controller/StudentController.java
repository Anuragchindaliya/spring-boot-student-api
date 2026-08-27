package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.dto.CreateStudentRequestDTO;
import in.anurag.crudSpingBootDemo.dto.CreateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.dto.UpdateStudentRequestDTO;
import in.anurag.crudSpingBootDemo.dto.UpdateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.entity.Student;
import in.anurag.crudSpingBootDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    //create student
    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent (@RequestBody CreateStudentRequestDTO student){

        CreateStudentResponseDTO createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }
     //read ALL student
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student studentResp = studentService.getStudent(id);
        if(studentResp==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent(){
        List<CreateStudentResponseDTO> studentResp = studentService.getAllStudent();
        if(studentResp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    //  update student
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDTO studentReq){

        UpdateStudentResponseDTO studentResp = studentService.updateStudent(id,studentReq);
        if(studentResp==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        Boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted");

    }
    @DeleteMapping("/{id}/softly")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
        Boolean isDeleted = studentService.deleteStudentSoftly(id);
        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted Softly");

    }
}
