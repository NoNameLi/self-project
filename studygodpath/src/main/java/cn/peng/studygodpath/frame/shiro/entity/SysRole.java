package cn.peng.studygodpath.frame.shiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role_t")
public class SysRole implements Serializable {
    private static final long serialVersionUID = -7217484187759149093L;

    @Id
    @GeneratedValue
    private Integer id;

    private String role;

    @ManyToMany
    @JoinTable(name = "role_permission_t", joinColumns = {@JoinColumn(name = "rid")}, inverseJoinColumns = {@JoinColumn(name = "pid")})
    private List<SysPermission> permissions;

    @ManyToMany
    @JoinTable(name = "user_t", joinColumns = {@JoinColumn(name = "rid")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", permissions=" + permissions +
                ", users=" + users +
                '}';
    }
}
