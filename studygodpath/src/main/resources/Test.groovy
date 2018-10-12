import cn.peng.studygodpath.groovy.AbstractClass

//
//def testC(int numA, int numB, int numC) {
//    "传入参数：" + numA + numB + numC + "计算之和为：" + (numA + numB + numC)
//
//}
class Calculae extends AbstractClass{
    def printlnStr = "exec...."

    def testC(int numA, int numB, int numC) {
        "传入参数：" + numA + numB + numC + "计算之和为：" + (numA + numB + numC)
    }

    @Override
    void exect() {
        println "${printlnStr}"
    }
}