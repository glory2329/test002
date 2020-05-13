package com.glory.demo.framework.web.exception.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * author : glory
 * date : 2019/12/14 21:33
 * description : 自定义异常 注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValueRangeValidator.class)
public @interface ValueRange {

    String[] values();

    String message() default "值不正确";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
