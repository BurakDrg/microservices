package com.burak.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("from Order where customerId = :customerId")
    List<Order> findOrderByCustomerId(@Param("customerId") Integer customerId);

    @Query("from Order order by startDate, endDate")
    List<Order> getOrderByDateInterval();
}
