package com.zerp.bookmanagement.Model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "user") 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id; 

    @Column(name = "user_name")
    private String userName;
    private String email;
    private String role;
    private String password;
     @Column(name = "is_active")
    private boolean is_active;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private Date modifiedDate;

    
    public Long getUser_id() {
        return user_id;
    }


    public String getUser_name() {
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


    public boolean isIs_active() {
        return is_active;
    }


    public Date getCreatedDate() {
        return createdDate;
    }


    public Date getModifiedDate() {
        return modifiedDate;
    }


    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }


    public void setUser_name(String user_name) {
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


    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public User() {
      
    }


}

