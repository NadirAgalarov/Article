package com.article.article.dao;

import com.article.article.model.Article;

import java.util.List;
import java.util.TreeSet;

public interface ArticleDao {
    void addArticle(Article article);

    TreeSet<Article> selectAllArticles();
    TreeSet<Article> selectMyArtticles(String userName);
    Article getArticle(int id);
    void  updateArticle(Article article);
    void deleteArticle(int id);

}
