package mk.finki.ukim.lab.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class SelectBalloonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        String color = (String)req.getSession().getAttribute("color");
        String path = req.getServletPath();


        if (!path.contains("/balloons") && !path.equals("/selectBalloon") && (color == null || color.isEmpty())){
            resp.sendRedirect("/balloons");
        }else{
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
