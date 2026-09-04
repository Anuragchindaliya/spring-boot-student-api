package in.anurag.crudSpingBootDemo.repository;

import in.anurag.crudSpingBootDemo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Optional<Teacher> findByIdAndDeletedIsFalse(Long id);
    List<Teacher> findByDeletedIsFalse();

    Boolean existsByEmail(String email);

}
