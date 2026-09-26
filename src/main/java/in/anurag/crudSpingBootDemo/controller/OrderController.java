package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.entity.Order;
import in.anurag.crudSpingBootDemo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(
            @RequestBody Order order) {
        orderService.placeOrder(order);
        return ResponseEntity.ok("DONE");
    }
}
