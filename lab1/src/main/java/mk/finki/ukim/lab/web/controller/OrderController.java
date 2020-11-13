package mk.finki.ukim.lab.web.controller;

import mk.finki.ukim.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrdersPage(HttpServletRequest request, Model model){
        String clientName = (String)request.getSession().getAttribute("clientName");
        String clientAddress = (String)request.getSession().getAttribute("clientAddress");
        model.addAttribute("orders", this.orderService.findByClientNameAndClientAddress(clientName, clientAddress));
        return "userOrders";
    }
}
