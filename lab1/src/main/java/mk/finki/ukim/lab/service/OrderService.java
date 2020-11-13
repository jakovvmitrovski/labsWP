package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(String balloonColor, String balloonSize, String clientName, String address);
    List<Order> findAll();
    List<Order> findByClientNameAndClientAddress(String clientName, String clientAddress);
    void deleteByClientNameAndClientAddress(String clientName, String clientAddress);
}
