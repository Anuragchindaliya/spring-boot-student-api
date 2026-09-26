package in.anurag.crudSpingBootDemo.repository;

import in.anurag.crudSpingBootDemo.entity.PaymentAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAuditRepository extends JpaRepository<PaymentAudit, Long> {
}
