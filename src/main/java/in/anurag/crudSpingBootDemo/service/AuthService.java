package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.UserRegisterRequestDto;
import in.anurag.crudSpingBootDemo.dto.UserRegisterResponseDto;
import in.anurag.crudSpingBootDemo.entity.User;
import in.anurag.crudSpingBootDemo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDto register(UserRegisterRequestDto registerRequestDto){

        User user = new User();
        String userName = registerRequestDto.getUsername();
        user.setUsername(userName);
        user.setName(registerRequestDto.getName());

        String encodedPassword = passwordEncoder.encode(registerRequestDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        userRepository.save(user);


        UserRegisterResponseDto userRegisterResponseDto = new UserRegisterResponseDto();
        userRegisterResponseDto.setUsername(userName);
        userRegisterResponseDto.setMessage("User saved successfully");
        return userRegisterResponseDto;
    }
}
