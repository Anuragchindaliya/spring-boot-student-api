package in.anurag.crudSpingBootDemo.repository;

import in.anurag.crudSpingBootDemo.entity.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferRecord,Long> {


}
