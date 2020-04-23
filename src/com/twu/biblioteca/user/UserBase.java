package com.twu.biblioteca.user;

import java.util.Objects;

public class UserBase {
    private String name;
    private String login;
    private final String password;
    private String phone;
    private String email;

    public UserBase(String name, String login, String password, String phone, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User: " + name + "\nPhone: " + phone + "\nE-mail: " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBase userBase = (UserBase) o;
        return Objects.equals(login, userBase.login) &&
                Objects.equals(password, userBase.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    public boolean validateCredentials(String login, String password) {
        return this.login.equals(login) && this.password.equals(password) ;
    }
}
