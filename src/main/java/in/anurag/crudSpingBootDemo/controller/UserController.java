package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.dto.UserRegisterRequestDto;
import in.anurag.crudSpingBootDemo.dto.UserRegisterResponseDto;
import in.anurag.crudSpingBootDemo.entity.User;
import in.anurag.crudSpingBootDemo.service.AuthService;
import in.anurag.crudSpingBootDemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    public UserController(UserService userService,AuthService authService){
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user, @RequestParam Long id){

        userService.createUser(user,id);
        return ResponseEntity.ok("User created successfully");
    }
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody UserRegisterRequestDto user){

        UserRegisterResponseDto userRegisterResponseDto = authService.register(user);
        return ResponseEntity.ok(userRegisterResponseDto);
    }

    @PostMapping("/with")
    public ResponseEntity<String> createUserWithDepartment(@RequestBody User user, @RequestParam String departmentName){

        userService.createUserWithDepartment(user,departmentName);
        return ResponseEntity.ok("User and Department created successfully");
    }

    @GetMapping
    public ResponseEntity<List> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }


}
