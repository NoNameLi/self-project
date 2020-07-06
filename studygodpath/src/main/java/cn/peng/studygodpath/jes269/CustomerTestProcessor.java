package cn.peng.studygodpath.jes269;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public class CustomerTestProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("Log in AnnotationProcessor.process");
        for (TypeElement typeElement : annotations) {
            System.out.println(typeElement);
        }
        System.out.println(roundEnv);
        return true;
    }
}
