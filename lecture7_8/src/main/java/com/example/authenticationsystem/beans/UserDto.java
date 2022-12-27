package com.example.authenticationsystem.beans;

public class UserDto {

    private String login;
    private String name;

    public UserDto(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public static UserDto from(User user) {
        return new UserDto(user.getLogin(), user.getName());
    }

    public UserDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
