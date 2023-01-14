package com.example.authenticationsystem.dao;

import com.example.authenticationsystem.beans.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InMemoryUserDao implements UserDao {
    
    private static final List<User> USERS =  new ArrayList<>();
    
    static {
        USERS.add(new User("dasha", "Dasha", "123"));
        USERS.add(new User("yura", "Yura", "1234"));
        USERS.add(new User("ira", "Ira", "12345"));
    }
 
    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(USERS);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return USERS.stream()
                .filter(s -> s.getLogin().equals(login))
                .findAny();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return USERS.stream()
                .filter(s -> s.getPassword().equals(password) && s.getLogin().equals(login))
                .findAny();
    }

}
