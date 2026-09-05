package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.dto.CreateSchoolDTO;
import in.anurag.crudSpingBootDemo.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
    private final SchoolService schoolService;
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @PostMapping
    public ResponseEntity<CreateSchoolDTO> createSchool (@RequestBody CreateSchoolDTO schoolDTO){
        CreateSchoolDTO message = schoolService.createSchool(schoolDTO);
        return ResponseEntity.ok(message);
    }
    @GetMapping
    public ResponseEntity<String> createDummy(@RequestParam String s){
        String message = schoolService.dummyMethod(s);
        return ResponseEntity.ok(message);
    }
}
