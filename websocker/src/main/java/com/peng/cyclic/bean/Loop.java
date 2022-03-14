package com.peng.cyclic.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Loop {
//    @Autowired
//    private Cyclic cyclic;

    public Loop() {
        System.out.println("创建loop");
    }
}
