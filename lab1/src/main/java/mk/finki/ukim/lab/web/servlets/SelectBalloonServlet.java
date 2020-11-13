package mk.finki.ukim.lab.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "select-balloon-servlet", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String color = (String)req.getSession().getAttribute("color");
//        if (color == null || color.isEmpty()){
//            resp.sendRedirect("/balloon");
//        }else {
//            super.doGet(req, resp);
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        if (color == null || color.isEmpty()){
            resp.sendRedirect("/balloon");
        }
        req.getSession().setAttribute("color", color);
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("color", color);
        springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());
    }
}
