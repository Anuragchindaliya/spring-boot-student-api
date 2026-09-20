package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.entity.Department;
import in.anurag.crudSpingBootDemo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
     private final DepartmentService departmentService;

     public DepartmentController(DepartmentService departmentService){
         this.departmentService = departmentService;
     }

     @PostMapping
    public String createDepartment(@RequestBody Department department){
         return departmentService.createDepartment(department);
     }

     @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id){
         return departmentService.getDepartmentById(id);
     }
}
