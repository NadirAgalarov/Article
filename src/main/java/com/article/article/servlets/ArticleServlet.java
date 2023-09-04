package com.article.article.servlets;

import com.article.article.dao.ArticleDaoImpl;
import com.article.article.model.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

@WebServlet(name = "ArticleServlet", value = "/user/article")
public class ArticleServlet extends HttpServlet {
    private ArticleDaoImpl articleDao;

    @Override
    public void init() {
        articleDao = new ArticleDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = (String) request.getSession().getAttribute("userName");
        TreeSet<Article> articles = articleDao.selectAllArticles();

        request.setAttribute("articles", articles);
        request.getRequestDispatcher("/article.jsp").forward(request, response);
    }
}
