package com.zerp.bookmanagement.Model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name")
    private String userName;
    private String email;
    private String role;
    private String password;
    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setCreatedDate(LocalDate localDate) {
        this.createdDate = localDate;
    }

    public void setModifiedDate(LocalDate localDate) {
        this.modifiedDate = localDate;
    }

    User()
    {
        
    }

}
