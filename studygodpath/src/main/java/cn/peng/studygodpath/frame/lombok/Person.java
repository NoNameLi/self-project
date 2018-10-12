package cn.peng.studygodpath.frame.lombok;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by remote on 2018/1/9.
 */
@Data
@Builder
public class Person {

    private Long id;
    private String identityNum;
    private String surname;
    private String name;
    private Sex sex;
    private Date bornDate;
    private double height;
    private double weigth;

    public enum Sex{
        MALE,
        FEMALE;
    }
}
