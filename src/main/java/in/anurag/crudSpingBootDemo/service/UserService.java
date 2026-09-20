package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.entity.Department;
import in.anurag.crudSpingBootDemo.entity.User;
import in.anurag.crudSpingBootDemo.exception.ResourceNotFoundException;
import in.anurag.crudSpingBootDemo.repository.DepartmentRepository;
import in.anurag.crudSpingBootDemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public UserService(UserRepository userRepository,DepartmentRepository departmentRepository){
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }
    @Transactional
    public void createUser(User user,Long id){
        Department department = departmentRepository.getDepartmentById(id);
        if(department==null){
            throw new ResourceNotFoundException("No Department found");
        }
        user.setDepartment(department);
        department.getUserList().add(user);
//        Department department = departmentRepository.getDepartmentById()
        userRepository.createUser(user);

    }

    @Transactional
    public void createUserWithDepartment(User user, String departmentName){
        Department department = new Department();
        department.setName(departmentName);

        user.setDepartment(department);
//        department.getUserList().add(user);
        departmentRepository.save(department);
        userRepository.createUser(user);

    }
}
