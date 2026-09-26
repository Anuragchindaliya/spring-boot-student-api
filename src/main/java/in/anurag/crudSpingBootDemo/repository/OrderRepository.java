package in.anurag.crudSpingBootDemo.repository;

import in.anurag.crudSpingBootDemo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
