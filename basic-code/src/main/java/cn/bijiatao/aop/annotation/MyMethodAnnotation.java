package cn.bijiatao.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法级别注解
 *
 * @author bijiatao
 */
// 运行时注解
@Retention(RetentionPolicy.RUNTIME)
// 方法级别注解
@Target(ElementType.METHOD)
public @interface MyMethodAnnotation {
    String desc() default "默认方法注解";

    int version() default 1;
}
