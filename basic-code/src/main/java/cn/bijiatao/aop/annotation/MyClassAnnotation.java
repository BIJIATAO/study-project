package cn.bijiatao.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类级别注解
 *
 * @author bijiatao
 */
// 运行时注解
@Retention(RetentionPolicy.RUNTIME)
// 类级别注解
@Target(ElementType.TYPE)
public @interface MyClassAnnotation {
    // 注解里的字段必须以 【类型 字段名()】这样的形式定义
    String value() default "默认类注解";
}
