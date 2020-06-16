package cn.peng.studygodpath.frame.shiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class SysPermission implements Serializable {
    private static final long serialVersionUID = -8510925741758860237L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "role_permission_t", joinColumns = {@JoinColumn(name = "pid")}, inverseJoinColumns = {@JoinColumn(name = "rid")})
    private List<SysRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
