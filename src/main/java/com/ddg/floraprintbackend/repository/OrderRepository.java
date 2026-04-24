package com.ddg.floraprintbackend.repository;

import com.ddg.floraprintbackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPrintStatus(String printStatus);
}