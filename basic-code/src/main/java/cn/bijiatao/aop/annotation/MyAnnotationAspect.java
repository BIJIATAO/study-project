package cn.bijiatao.aop.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.PrioritizedParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 自定义注解切面
 *
 * @author bijiatao
 */
@Slf4j
@Aspect
@Component
public class MyAnnotationAspect {

    /**
     * 切点：所有带 MyMethodAnnotation 的方法
     */
    @Pointcut("@annotation(cn.bijiatao.aop.annotation.MyMethodAnnotation)")
    public void myMethodPointcut() {
    }

    @Around("myMethodPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object target = joinPoint.getTarget();

        // 解析类注解
        MyClassAnnotation classAnnotation = target.getClass().getAnnotation(MyClassAnnotation.class);
        if (classAnnotation != null) {
            log.info("【类注解】模块名称: {}", classAnnotation.value());
        }

        // 解析方法注解
        MyMethodAnnotation methodAnnotation = method.getAnnotation(MyMethodAnnotation.class);
        log.info("【方法注解】方法描述: {}, 版本: {}", methodAnnotation.desc(), methodAnnotation.version());

        // 参数注解校验
        validateParams(method, joinPoint.getArgs());

        Object result;
        try {
            log.info("{}.{}方法开始执行。", target.getClass().getSimpleName(), method.getName());
            result = joinPoint.proceed();
            log.info("方法结束，返回结果: {}", result);
        } catch (Exception e) {
            log.error("方法异常：", e);
            throw e;
        }
        return result;
    }

    /**
     * 参数必填校验
     */
    private void validateParams(Method method, Object[] args) {
        // 二维数组是因为：第一层是参数索引，第二层是特定参数注解的索引
        /**
         * 比如，public void createUser(
         *            // 参数 0：1 个注解
         *            @NotNull(message = "用户名称不能为空") String username,
         *            // 参数 1：1 个注解
         *            @Length(min = 2, max = 20) @NotNull(message = "用户密码不能为空") String password
         *   );
         *
         *    parameterAnnotations[0] 代表username，parameterAnnotations[1] 代表password
         *    而parameterAnnotations[0][0]代表@NotNull，parameterAnnotations[1][0]代表@Length，parameterAnnotations[1][1]代表@NotNull
         */

        // 获取方法参数名
        ParameterNameDiscoverer paramNameDiscoverer = new PrioritizedParameterNameDiscoverer();
        String[] paramNames = paramNameDiscoverer.getParameterNames(method);

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof MyParamAnnotation myParam) {
                    if (myParam.required() && args[i] == null) {
                        String paramName =
                                (paramNames != null && paramNames.length > i) ? paramNames[i] : "第" + (i + 1) + "个参数";
                        throw new IllegalArgumentException("参数校验失败：" + paramName + "不能为空");
                    }
                }
            }
        }
    }
}