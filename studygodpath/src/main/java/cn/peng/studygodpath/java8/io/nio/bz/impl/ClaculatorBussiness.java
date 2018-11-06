package cn.peng.studygodpath.java8.io.nio.bz.impl;

import cn.peng.studygodpath.java8.io.nio.bz.BusinessProcess;
import cn.peng.studygodpath.java8.io.util.Calculator;

public class ClaculatorBussiness implements BusinessProcess {

    private Calculator calculator = new Calculator();

    @Override
    public byte[] dealwith(String data) {
        float result = calculator.cal(data);
        return new StringBuilder().append(result).toString().getBytes();
    }
}
