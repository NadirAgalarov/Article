package com.article.article.dao;

import com.article.article.model.User;
import com.article.article.utils.Connecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    @Override
    public void register(User user) {
        String InsertSQL="INSERT INTO user(first_name,last_name,user_name,password) values(?,?,?,?);";


        try {
            Connection connection = Connecter.getConnection();
            PreparedStatement statement = connection.prepareStatement(InsertSQL);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getUser(String userName, String password) {
        String getUserSQL="SELECT id,first_name, last_name, user_name, password FROM user WHERE user_name=? AND  password=?;";

        User user=null;

        try {
            Connection connection = Connecter.getConnection();
            PreparedStatement statement = connection.prepareStatement(getUserSQL);
            statement.setString(1, userName);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
}
