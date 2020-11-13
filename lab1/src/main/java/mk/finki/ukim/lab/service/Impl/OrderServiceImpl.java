package mk.finki.ukim.lab.service.Impl;

import mk.finki.ukim.lab.model.Order;
import mk.finki.ukim.lab.repository.OrderRepository;
import mk.finki.ukim.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String balloonColor, String balloonSize, String clientName, String address) {
        return this.orderRepository.save(new Order(balloonColor, balloonSize, clientName, address));
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findByClientNameAndClientAddress(String clientName, String clientAddress) {
        return this.orderRepository.findByClientNameAndClientAddress(clientName, clientAddress);
    }

    @Override
    public void deleteByClientNameAndClientAddress(String clientName, String clientAddress) {
        this.orderRepository.deleteByClientNameAndClientAddress(clientName, clientAddress);
    }
}
