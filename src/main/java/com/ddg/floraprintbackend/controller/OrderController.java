package com.ddg.floraprintbackend.controller;

import com.ddg.floraprintbackend.model.Order;
import com.ddg.floraprintbackend.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    // Criar pedido
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order.setPrintStatus("PENDING");
        return repository.save(order);
    }

    // Listar todos
    @GetMapping
    public List<Order> getOrders() {
        return repository.findAll();
    }

    // Listar pendentes
    @GetMapping("/pending")
    public List<Order> getPendingOrders() {
        return repository.findByPrintStatus("PENDING");
    }

    // Marcar como impresso
    @PutMapping("/{id}/printed")
    public Order markAsPrinted(@PathVariable Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        order.setPrintStatus("PRINTED");
        return repository.save(order);
    }
}