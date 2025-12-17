package cn.bijiatao.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数级别注解
 *
 * @author bijiatao
 */
@Retention(RetentionPolicy.RUNTIME)
// 参数级别注解
@Target(ElementType.PARAMETER)
public @interface MyParamAnnotation {
    /**
     * 参数是否必填
     */
    boolean required() default true;
}
