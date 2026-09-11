package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.dto.CreateSchoolDTO;
import in.anurag.crudSpingBootDemo.dto.CreateSchoolResponseDTO;
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
    @GetMapping("/{id}")
    public ResponseEntity<CreateSchoolResponseDTO> getSchoolById(@PathVariable Long id){
        System.out.println("controller started");
        CreateSchoolResponseDTO schoolResponseDTO = schoolService.getSchoolById(id);
        System.out.println("controoler"+schoolResponseDTO);
        return ResponseEntity.ok(schoolResponseDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchoolById(@RequestBody CreateSchoolDTO school, @PathVariable Long id){
        schoolService.updateSchoolById(school, id);
        return ResponseEntity.ok("School updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchoolById(@PathVariable Long id){
        schoolService.deleteSchool(id);
        return ResponseEntity.ok("School deleted successfully");
    }

}
