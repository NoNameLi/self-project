package annotation;


import condition.ModuleCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ModuleCondition.class)
public @interface ConditionOnModuleEnable {
}
