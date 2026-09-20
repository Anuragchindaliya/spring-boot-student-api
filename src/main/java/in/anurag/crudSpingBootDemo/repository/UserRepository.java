package in.anurag.crudSpingBootDemo.repository;

import in.anurag.crudSpingBootDemo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createUser(User user){
        entityManager.persist(user);
    }

}
