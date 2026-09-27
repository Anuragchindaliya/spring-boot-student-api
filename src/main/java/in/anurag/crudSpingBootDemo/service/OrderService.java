package in.anurag.crudSpingBootDemo.service;

import in.anurag.crudSpingBootDemo.entity.Order;
import in.anurag.crudSpingBootDemo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentAuditService paymentAuditService;

    public OrderService(OrderRepository orderRepository,
                        PaymentAuditService paymentAuditService) {
        this.orderRepository = orderRepository;
        this.paymentAuditService = paymentAuditService;
    }

    @Transactional
    public void placeOrder(Order order) {
        orderRepository.save(order);

        paymentAuditService.audit(order);
    }
}
