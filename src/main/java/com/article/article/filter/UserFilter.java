package com.article.article.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter" , urlPatterns = "/user/*")
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        if (session != null) {
            String userName = (String) session.getAttribute("userName");

            if (userName != null)
                chain.doFilter(request, response);
            else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            }
        } else
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
    }
}

