package com.demo.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")//建立实体类和表的映射关系
public class Role {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键生成策略
     @Column(name = "id")//指定表中id字段的映射关系
    private  Integer id;
    @Column(name = "roleName")
    private  String roleName;
    @Column(name = "roleDesc")
    private  String roleDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    //多对多得关系映射
    @ManyToMany
    // 中间的表名
    @JoinTable(name = "users_role",
            //中间表user_role字段关联sys_role表的主键字段role_id
            joinColumns = {@JoinColumn(name = "roleId",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="userId",referencedColumnName = "id")})

    //@ManyToMany(mappedBy="roles")
    private Set<Users> users  =  new HashSet<Users>();

}
