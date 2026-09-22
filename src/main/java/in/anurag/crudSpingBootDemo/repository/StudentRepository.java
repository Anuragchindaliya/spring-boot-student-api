package in.anurag.crudSpingBootDemo.repository;

import in.anurag.crudSpingBootDemo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    Page<Student> findByDeletedIsFalse(Pageable pageable);

    Boolean existsByEmail(String email);
}
