package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.entity.Order;
import in.anurag.crudSpingBootDemo.entity.PaymentAudit;
import in.anurag.crudSpingBootDemo.repository.PaymentAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentAuditService {

    private final PaymentAuditRepository paymentAuditRepository;

    public PaymentAuditService(PaymentAuditRepository paymentAuditRepository) {
        this.paymentAuditRepository = paymentAuditRepository;
    }
//    Propagation
//    SUPPORT
//    if txn exist--> join it
//    if not --> implement without txn

//    MANDATORY
//    if txn exist --> join it
//    ft not --> throw an exception

//    NOT_SUPPORTED
//    always execute without a txn
//    txn exist --> suspend

//    NEVER
//    i want to execute without txn
//    txn exist --> throw exception

//    NESTED
//     save points


    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ
    )
    public void audit(Order order) {
        PaymentAudit paymentAudit =
                new PaymentAudit(order.getAmount(), order.getId(), true);

        paymentAuditRepository.save(paymentAudit);
    }
}
