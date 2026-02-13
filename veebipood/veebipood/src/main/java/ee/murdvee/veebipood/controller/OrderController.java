package ee.murdvee.veebipood.controller;

import ee.murdvee.veebipood.entity.Order;
import ee.murdvee.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("orders")
    public List<Order> getOrder(){
        return orderRepository.findAll();
    }

    @DeleteMapping("orders/{id}")
    public List<Order> deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id); // kustutan
        return orderRepository.findAll(); // uuenenud seis
    }

    @PostMapping("orders")
    public List<Order> addOrder(@RequestBody Order order){
        orderRepository.save(order); // siin salvestab
        return orderRepository.findAll(); // siin on uuenenud seis
    }

}
