package cn.bijiatao.aop.basic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LogAspect {

    // 3. @Pointcut: 定义一个切点，并用一个方法签名来命名它
    //    这里的表达式含义是：拦截 cn.bijiatao.aop.basic.UserServiceImpl下所有方法
    @Pointcut("execution(* cn.bijiatao.aop.basic.UserServiceImpl.*(..))")
    public void serviceLogPointcut() {
        // 这个方法体通常是空的，它只是一个切点的"标识"
    }

    // 4. @Before: 前置通知，在目标方法执行之前执行
    @Before("serviceLogPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.printf("[%s] 【AOP前置通知】准备执行方法: %s%n", now, methodName);
    }

    // 5. @AfterReturning: 返回通知，在目标方法成功执行并返回结果后执行
    @AfterReturning(pointcut = "serviceLogPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.printf("[%s] 【AOP返回通知】方法 %s 执行成功，返回结果: %s%n", now, methodName, result);
    }

    // 6. @AfterThrowing: 异常通知，在目标方法抛出异常时执行
    @AfterThrowing(pointcut = "serviceLogPointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.printf("[%s] 【AOP异常通知】方法 %s 执行失败，异常信息: %s%n", now, methodName, ex.getMessage());
    }

    // 7. @After: 后置通知，在目标方法执行之后执行（无论成功还是失败）
    @After("serviceLogPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.printf("[%s] 【AOP后置通知】方法 %s 执行结束%n", now, methodName);
    }

    // 8. @Around: 环绕通知，它包裹了目标方法，可以在方法执行前后做任何事情，甚至控制方法是否执行
    @Around("serviceLogPointcut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.printf("[%s] 【AOP环绕通知-开始】方法 %s 计时开始%n", now, methodName);
        long startTime = System.currentTimeMillis();

        // 核心：执行目标方法
        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.printf("[%s] 【AOP环绕通知-结束】方法 %s 计时结束，总耗时: %d ms%n", now, methodName, (endTime - startTime));

        return result;
    }
}