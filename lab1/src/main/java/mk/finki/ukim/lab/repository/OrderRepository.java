package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.bootstrap.DataHolder;
import mk.finki.ukim.lab.model.Order;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    public Order save(Order order){
        DataHolder.orders.removeIf(o -> o.getOrderId().equals(order.getOrderId()));
        DataHolder.orders.add(order);
        return order;
    }

    public List<Order> findAll(){
        return DataHolder.orders;
    }

    public List<Order> findByClientNameAndClientAddress(String clientName, String clientAddress){
        return DataHolder.orders
                .stream()
                .filter(x -> x.getClientName().equals(clientName) && x.getClientAddress().equals(clientAddress))
                .collect(Collectors.toList());
    }
    public void deleteByClientNameAndClientAddress(String clientName, String clientAddress){
        DataHolder.orders.removeIf(x -> x.getClientAddress().equals(clientAddress) && x.getClientName().equals(clientName));
    }
}
