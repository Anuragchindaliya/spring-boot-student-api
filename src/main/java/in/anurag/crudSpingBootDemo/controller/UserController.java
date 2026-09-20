package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.entity.User;
import in.anurag.crudSpingBootDemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user, @RequestParam Long id){

        userService.createUser(user,id);
        return ResponseEntity.ok("User created successfully");
    }
    @PostMapping("/with")
    public ResponseEntity<String> createUserWithDepartment(@RequestBody User user, @RequestParam String departmentName){

        userService.createUserWithDepartment(user,departmentName);
        return ResponseEntity.ok("User and Department created successfully");
    }
}
