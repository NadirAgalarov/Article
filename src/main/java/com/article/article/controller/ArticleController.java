package com.article.article.controller;

import com.article.article.dao.ArticleDaoImpl;
import com.article.article.model.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TreeSet;

@WebServlet(name = "ArticleController", value = "/user/article/actions")
public class ArticleController extends HttpServlet {
    private ArticleDaoImpl articleDao;

    @Override
    public void init() {
        articleDao = new ArticleDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            request.getRequestDispatcher("/addArticle.jsp").forward(request, response);
        } else {
            try {
                switch (action) {
                    case "new":
                        request.getRequestDispatcher("/addArticle.jsp").forward(request, response);
                        break;

                    case "insert":
                        addArticle(request, response);
                        break;
                    case "update":
                        updateArticle(request, response);
                        break;
                    case "edit":
                        showEditArticle(request, response);
                        break;
                    case "delete":
                        deleteArticle(request, response);
                        break;
                    case "my-article":
                        selectMyArticle(request,response);
                        break;
                    default:
                        response.sendRedirect("../../login.jsp");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }
    private void selectMyArticle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = (String) request.getSession().getAttribute("userName");
       TreeSet<Article> articles=articleDao.selectMyArtticles(userName);
        request.setAttribute("articles", articles);
        for (Article article : articles) {
            System.out.println(article);
        }

        System.out.println("in articlecontroller select my article");
        request.getRequestDispatcher("../../article.jsp").forward(request, response);
    }

    private void addArticle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = (String) request.getSession().getAttribute("userName");
        String title = request.getParameter("title");
        String article = request.getParameter("article");


        Article article1=new Article();
        article1.setTitle(title);
        article1.setArticle(article);
        article1.setUserName(userName);

        articleDao.addArticle(article1);
        response.sendRedirect(request.getContextPath() + "/user/article");
    }

    private void showEditArticle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Article article1 =articleDao.getArticle(id);
        request.setAttribute("article1", article1);
        request.getRequestDispatcher("/addArticle.jsp").forward(request, response);
    }

    private void updateArticle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String article = request.getParameter("article");




        Article article1=new Article();
        article1.setId(id);
        article1.setTitle(title);
        article1.setArticle(article);


        articleDao.updateArticle(article1);
        response.sendRedirect(request.getContextPath() + "/user/article");
    }

    private void deleteArticle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        articleDao.deleteArticle(id);
        response.sendRedirect(request.getContextPath() + "/user/article");
    }





}
