package com.article.article.controller;

import com.article.article.dao.UserDaoImpl;
import com.article.article.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    private UserDaoImpl userDao;
    public void init(){ userDao= new UserDaoImpl();}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkUser(request,response);
    }
    public void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        User user=userDao.getUser(userName,password);
        if(user != null){
            request.getSession().setAttribute("userName",user.getUserName());
            response.sendRedirect("user/article");
        }
        else{
            request.setAttribute("loginError", "Username or Password id incorrect!");
            response.sendRedirect("login.jsp");
        }



    }
}
