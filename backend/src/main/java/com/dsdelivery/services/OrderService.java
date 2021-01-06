package com.dsdelivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.dsdelivery.dto.OrderDTO;
import com.dsdelivery.dto.ProductDTO;
import com.dsdelivery.entities.Order;
import com.dsdelivery.entities.Product;
import com.dsdelivery.entities.enums.OrderStatus;
import com.dsdelivery.repositories.OrderRepository;
import com.dsdelivery.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = repository.findOrdersWithProducts();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insertOrder(OrderDTO dto) {
        Order order = new Order(null, dto.getLatitude(), dto.getLongitude(), dto.getAddress(), 
        Instant.now(), OrderStatus.PENDING);
        for (ProductDTO p : dto.getProducts()) {
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }

        order = repository.save(order);
        return new OrderDTO(order);
    }
}
