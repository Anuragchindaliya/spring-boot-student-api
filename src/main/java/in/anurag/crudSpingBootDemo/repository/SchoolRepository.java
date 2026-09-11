package in.anurag.crudSpingBootDemo.repository;

import in.anurag.crudSpingBootDemo.entity.School;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(School school){
        entityManager.persist(school);
    }

    public void update(School school){
        entityManager.persist(school);
    }

    public School findById(Long id){
        return entityManager.find(School.class,id);
    }

    public void remove(School school){
         entityManager.remove(school);
    }
}
