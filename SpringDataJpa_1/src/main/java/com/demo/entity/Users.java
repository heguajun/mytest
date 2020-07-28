package com.demo.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity //声明实体类
@Table(name = "users")//建立实体类和表的映射关系
public class Users {
    @Id //声明当前私有属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键生成策略
    @Column(name = "id")//指定表中id字段的映射关系
    private  Integer id;
    @Column(name = "email")
    private  String email;
    @Column(name = "username")
    private  String username;
    @Column(name = "password")
    private  String password;
    @Column(name = "phoneNum")
    private  String phoneNum;
    @Column(name = "sataus")
    private  Integer sataus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getSataus() {
        return sataus;
    }

    public void setSataus(Integer sataus) {
        this.sataus = sataus;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", sataus=" + sataus +
                ", roles=" + roles +
                '}';
    }

    //用户与角色之间得关系
    @ManyToMany(mappedBy = "users",cascade=CascadeType.ALL)
    private Set<Role> roles =new HashSet<Role>();

}
