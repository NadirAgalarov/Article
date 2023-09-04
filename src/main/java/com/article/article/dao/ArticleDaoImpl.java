package com.article.article.dao;

import com.article.article.model.Article;
import com.article.article.utils.Connecter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.TreeSet;

public class ArticleDaoImpl implements ArticleDao{
    @Override
    public void addArticle(Article article) {
        String INSERT_TODO_SQL = "INSERT INTO article (title, article, user_name) VALUES (?, ?, ?)";

        try {
            Connection connection= Connecter.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_TODO_SQL);
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getArticle());
            statement.setString(3, article.getUserName());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public TreeSet<Article> selectAllArticles() {
        String selectAllArticles = "SELECT id, title, article,user_name, created_at, updated_at  FROM article";

        TreeSet<Article> articles = new TreeSet<>();
        try {
            Connection connection = Connecter.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectAllArticles);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setArticle(resultSet.getString("article"));
                article.setUserName(resultSet.getString("user_name"));
                article.setCreatedAt(resultSet.getTimestamp("created_at"));
                article.setUpdatedAt(resultSet.getTimestamp("updated_at"));


                articles.add(article);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return articles;

    }

    @Override
    public TreeSet<Article> selectMyArtticles(String userName) {
        String selectMyArticles = "SELECT id, title, article,user_name, created_at, updated_at  FROM article WHERE user_name=?";

        TreeSet<Article> articles = new TreeSet<>();
        try {
            Connection connection = Connecter.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectMyArticles);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setArticle(resultSet.getString("article"));
                article.setUserName(resultSet.getString("user_name"));
                article.setCreatedAt(resultSet.getTimestamp("created_at"));
                article.setUpdatedAt(resultSet.getTimestamp("updated_at"));


                articles.add(article);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return articles;
    }

    @Override
    public Article getArticle(int id) {
        String selectAllArticles = "SELECT id, title, article,user_name, created_at, updated_at  FROM article WHERE id=?";

        Article article = null;
        try {
            Connection connection = Connecter.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectAllArticles);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setArticle(resultSet.getString("article"));
                article.setUserName(resultSet.getString("user_name"));
                article.setCreatedAt(resultSet.getTimestamp("created_at"));
                article.setUpdatedAt(resultSet.getTimestamp("updated_at"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return article;
    }


    @Override
    public void updateArticle(Article article) {
        String updateArticle = "UPDATE article SET title = ?, article = ? WHERE id = ?;";

        try {
            Connection connection= Connecter.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateArticle);
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getArticle());
            statement.setInt(3, article.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteArticle(int id) {
        String deleteArticle = "DELETE FROM article WHERE id = ?;";

        try {
            Connection connection = Connecter.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteArticle);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
