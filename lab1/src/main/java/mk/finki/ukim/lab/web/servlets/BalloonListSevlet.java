package mk.finki.ukim.lab.web.servlets;

import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="balloon-list-servlet", urlPatterns = "/servlet")
public class BalloonListSevlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;


    public BalloonListSevlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloons", this.balloonService.listAll());
        String text = (String)req.getSession().getAttribute("fballoons");
        if (text != null)
            context.setVariable("fballoons", this.balloonService.searchByNameOrDescription(text));
        else{
            context.setVariable("fballoons", new ArrayList<Balloon>());
        }
        this.springTemplateEngine.process("listBalloons.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("fballoons", req.getParameter("text"));
        resp.sendRedirect("/*");
    }
}
