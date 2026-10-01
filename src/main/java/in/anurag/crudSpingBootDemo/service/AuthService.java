package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.dto.UserRegisterRequestDto;
import in.anurag.crudSpingBootDemo.dto.UserRegisterResponseDto;
import in.anurag.crudSpingBootDemo.entity.Role;
import in.anurag.crudSpingBootDemo.entity.User;
import in.anurag.crudSpingBootDemo.repository.RoleRepository;
import in.anurag.crudSpingBootDemo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AuthService {
    private  UserRepository userRepository;
    private RoleRepository roleRepository;
    private  PasswordEncoder passwordEncoder ;

    public AuthService(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDto register(UserRegisterRequestDto registerRequestDto){

        User user = new User();
        String userName = registerRequestDto.getUsername();
        user.setUsername(userName);
        user.setName(registerRequestDto.getName());

        String encodedPassword = passwordEncoder.encode(registerRequestDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = roleRepository.findByName("ROLE_USER").get();

        user.getRoles().add(role);

        userRepository.save(user);


        UserRegisterResponseDto userRegisterResponseDto = new UserRegisterResponseDto();
        userRegisterResponseDto.setUsername(userName);
        userRegisterResponseDto.setMessage("User saved successfully");
        return userRegisterResponseDto;
    }
    public Boolean login(UserRegisterRequestDto registerRequestDto){

        Optional<User> userOptional = userRepository.findByUsername(registerRequestDto.getUsername());
        User user = userOptional.get();

        return passwordEncoder.matches(registerRequestDto.getPassword(),user.getPassword());



    }
}
