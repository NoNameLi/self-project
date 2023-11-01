package condition;

import constans.ModuleContstans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class ModuleCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        boolean result = context.getEnvironment().getProperty(ModuleContstans.MODULE_PROPERTIE_ENABLE, Boolean.class, Boolean.FALSE);
        log.info("[Project] |- Condition [moudle Enabled] value is [{}]", result);
        return result;
    }
}
