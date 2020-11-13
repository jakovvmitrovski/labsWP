package mk.finki.ukim.lab.web.servlets;

import mk.finki.ukim.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "confirmation-servlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = (String)req.getSession().getAttribute("clientName");
        String clientAddress = (String)req.getSession().getAttribute("clientAddress");
        this.orderService.deleteByClientNameAndClientAddress(clientName, clientAddress);
        req.getSession().invalidate();
        resp.sendRedirect("/balloons");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String color = (String)req.getSession().getAttribute("color");
        String size = (String)req.getSession().getAttribute("size");
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        req.getSession().setAttribute("clientName", clientName);
        req.getSession().setAttribute("clientAddress", clientAddress);
        String ipAddress = req.getRemoteAddr();
        String agent = req.getHeader("User-agent");
        this.orderService.placeOrder(color, size, clientName, clientAddress);
        context.setVariable("color", color);
        context.setVariable("size", size);
        context.setVariable("clientName", clientName);
        context.setVariable("clientAddress", clientAddress);
        context.setVariable("ipAddress", ipAddress);
        context.setVariable("agent", agent);
        this.springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }
}
