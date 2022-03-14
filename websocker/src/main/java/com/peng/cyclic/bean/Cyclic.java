package com.peng.cyclic.bean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Cyclic {

//    @Autowired
//    private Loop loop;

    public Cyclic() {
        System.out.println("创建cyclic");
    }

    public void test() {
        System.out.println("......test");
    }
}
