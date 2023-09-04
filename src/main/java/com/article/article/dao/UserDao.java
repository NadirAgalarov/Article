package com.article.article.dao;

import com.article.article.model.User;

public interface UserDao {
    public  void register(User user);
    User getUser(String username, String password);
}
