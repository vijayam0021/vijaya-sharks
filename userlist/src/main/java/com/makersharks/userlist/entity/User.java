package com.makersharks.userlist.entity;


import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;

    @Id
    @Column(name="username")
    private String username;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="email")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
