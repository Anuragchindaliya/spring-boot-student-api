package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.entity.Role;
import in.anurag.crudSpingBootDemo.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role){
        roleRepository.save(role);
    }
}
