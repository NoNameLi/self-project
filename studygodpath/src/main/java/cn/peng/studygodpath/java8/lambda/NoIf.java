package cn.peng.studygodpath.java8.lambda;

/**
 * if 消除术
 */
public class NoIf {

    @FunctionalInterface
    interface ThrowExceptionFunction {

        void throwException(String message);
    }

    @FunctionalInterface
    interface BranchHandler {

        void exec(Runnable trueRunnable, Runnable falseRunnable);
    }

    public static ThrowExceptionFunction isTrue(boolean b) {
        return (message) -> {
            throw new RuntimeException(message);
        };
    }

    public static BranchHandler branchHandler(boolean b) {

        return (trueRunnable, falseRunnable) -> {
            if (b) {
                trueRunnable.run();
            } else {
                falseRunnable.run();
            }
        };
    }

    public static void main(String[] args) {
        isTrue(false).throwException("0 > 1 is error");

        branchHandler(true).exec(() -> System.out.println("true"), () -> System.out.println("false"));

    }

}
