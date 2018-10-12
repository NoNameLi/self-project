package cn.peng.studygodpath.java8.copy;

public class ObjRef {
    Obj aObj = new Obj();
    int aInt = 11;

    public void changeObj(Obj inObj) {
        inObj.str = "changed value";
    }

    public void changePri(int inInt) {
        inInt = 22;
    }   

}
