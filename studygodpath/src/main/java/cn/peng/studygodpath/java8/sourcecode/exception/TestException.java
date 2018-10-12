package cn.peng.studygodpath.java8.sourcecode.exception;

import org.testng.annotations.Test;

/**
 * Created by remote on 2018/1/22.
 */
public class TestException {

    public void throwBusException()throws  BusException{
        throw  new BusException("业务异常");
    }

    public void throwParentException() throws ParentException{
        this.throwBusException();
    }


    @Test
    public void test(){

        try {
            this.throwParentException();
//        }catch (BusException e){
//            System.out.println("捕获业务异常" + e.getMessage());
        }catch (ParentException e){
            System.out.println("捕获父类异常" + e.getMessage());
        }
    }


}
