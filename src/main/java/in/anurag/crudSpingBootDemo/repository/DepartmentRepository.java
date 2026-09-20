package in.anurag.crudSpingBootDemo.repository;

import in.anurag.crudSpingBootDemo.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public DepartmentRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(Department department){
        entityManager.persist(department);
    }

    public Department getDepartmentById(Long id){
        return entityManager.find(Department.class,id);
    }

}
