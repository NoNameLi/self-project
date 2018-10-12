package cn.peng.studygodpath.frame.lombok;

import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by remote on 2018/1/9.
 */
public class LombolTest {


    @Test
    public void DataTest(){
        Person li = Person.builder().surname("李").name("超鹏").sex(Person.Sex.MALE).bornDate(new Date()).build();
        li.setBornDate(new Date());
    }
}
