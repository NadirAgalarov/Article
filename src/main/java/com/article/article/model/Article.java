package com.article.article.model;
import  java.lang.Comparable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Article implements Comparable<Article> {
    private int id;
    private String title;
    private String article;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt1) {
        Article article1=new Article();
        this.createdAt=article1.localDateFromTimestamp(createdAt1);
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updted1) {
        Article article1=new Article();
        this.updatedAt =article1.localDateFromTimestamp(updted1);
    }
    public  LocalDateTime localDateFromTimestamp(Timestamp timestamp) {
        return LocalDateTime
                .ofInstant(timestamp.toInstant(), ZoneOffset.ofHours(0));
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", userName='" + userName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int compareTo(Article article) {

        int after=this.createdAt.compareTo(article.createdAt);

        if(after<0){
            return 1;}
        else if(after>0)
            return -1;
        return 0;
    }


}