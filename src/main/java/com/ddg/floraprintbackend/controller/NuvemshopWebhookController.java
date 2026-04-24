package com.ddg.floraprintbackend.controller;

import com.ddg.floraprintbackend.model.Order;
import com.ddg.floraprintbackend.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhooks/nuvemshop")
@CrossOrigin(origins = "*")
public class NuvemshopWebhookController {

    private final OrderRepository repository;

    public NuvemshopWebhookController(OrderRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/order-paid")
    public Order receiveOrderPaid(@RequestBody Map<String, Object> payload) {
        System.out.println("WEBHOOK NUVEMSHOP RECEBIDO:");
        System.out.println(payload);

        Order order = new Order();
        order.setCustomerName("Cliente Nuvemshop");
        order.setProduct("Pedido recebido via webhook");
        order.setMessage("Verificar detalhes no painel da Nuvemshop");
        order.setPrintStatus("PENDING");

        return repository.save(order);
    }
}