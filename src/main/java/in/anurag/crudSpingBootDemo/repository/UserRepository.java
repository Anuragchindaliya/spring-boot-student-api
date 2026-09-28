package in.anurag.crudSpingBootDemo.repository;

import in.anurag.crudSpingBootDemo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public void createUser(User user){
//        entityManager.persist(user);
//    }
//
//    public List getAllUser(){
//        return entityManager.createQuery("select ul from User ul").getResultList();
//    }

}
