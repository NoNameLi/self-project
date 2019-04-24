package cn.peng.studygodpath.java8.copy;

import org.junit.Test;

public class TestCopy {

    @Test
    public void test() {
        // 测试方法参数是值传递 还是 引用传递
        ObjRef oRef = new ObjRef();
        System.out.println("Before call changeObj() method: " + oRef.aObj);
        oRef.changeObj(oRef.aObj);
        System.out.println("After call changeObj() method: " + oRef.aObj);

        System.out.println("==================Print Primtive=================");
        System.out.println("Before call changePri() method: " + oRef.aInt);
        oRef.changePri(oRef.aInt);
        System.out.println("After call changePri() method: " + oRef.aInt);
    }

    @Test
    public void testObjectCopy() throws CloneNotSupportedException {

        BObj aObj = new BObj("aObj", 1, new Obj(), new ObjClone(), new ObjStream("init value", new Obj()));
        BObj bObj = aObj.clone();

        System.out.println(aObj.toString());
        System.out.println(bObj.toString());

        bObj.name = "bObj";
        bObj.count = 2;
        bObj.obj.str = "change";
        bObj.cloneObj.str = "change";
        bObj.streamObj.str = "change";
        bObj.streamObj.obj.str = "change";
        // 浅度拷贝，费基本数据类型的成员变量没有重写clone 方法
        System.out.println(aObj.toString());
        System.out.println(bObj.toString());
    }
}
