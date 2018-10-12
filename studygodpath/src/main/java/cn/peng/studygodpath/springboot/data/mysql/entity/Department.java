package cn.peng.studygodpath.springboot.data.mysql.entity;


import javax.persistence.*;

/**
 * Created by remote on 2018/3/3.
 */

@Entity
@Table(name = "deparment")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
