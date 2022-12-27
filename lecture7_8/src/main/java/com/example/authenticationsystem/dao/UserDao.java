package com.example.authenticationsystem.dao;

import com.example.authenticationsystem.beans.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAllUsers();

    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndPassword(String login, String password);

}
