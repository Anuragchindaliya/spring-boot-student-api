package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.entity.Department;
import in.anurag.crudSpingBootDemo.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public String createDepartment(Department department){
        departmentRepository.save(department);
        return "Department is created successfully";
    }
    public Department getDepartmentById(Long id){
       return departmentRepository.getDepartmentById(id);
    }

}
