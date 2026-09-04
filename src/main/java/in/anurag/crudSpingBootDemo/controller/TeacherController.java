package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.dto.CreateTeacherRequestDTO;
import in.anurag.crudSpingBootDemo.dto.CreateTeacherResponseDTO;
import in.anurag.crudSpingBootDemo.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<CreateTeacherResponseDTO> createTeacher(@RequestBody CreateTeacherRequestDTO teacherRequestDTO){
        System.out.println("payload from controller: "+teacherRequestDTO);
        teacherService.createTeacher(teacherRequestDTO);
        CreateTeacherResponseDTO teacherResponse = new CreateTeacherResponseDTO();

        return ResponseEntity.ok(teacherResponse);
    }
}
