package cn.bijiatao.aop.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解测试类
 *
 * @author bijiatao
 */
public class AnnotationDemo {
    public static void main(String[] args) throws Exception {
        // 获取UserService的Class对象
        Class<UserService> userServiceClass = UserService.class;

        // 1. 测试getMethods()：获取所有public方法（包括继承的，这里过滤Object类方法）
        System.out.println("=== 1. getMethods()：所有public方法 ===");
        Method[] publicMethods = userServiceClass.getMethods();
        for (Method method : publicMethods) {
            // 过滤Object类的方法（如toString、hashCode等）
            if (method.getDeclaringClass() == UserService.class) {
                System.out.println("public方法：" + method.getName());
            }
        }

        // 2. 测试getDeclaredMethods()：获取所有声明的方法（包括private）
        System.out.println("\n=== 2. getDeclaredMethods()：所有声明的方法（含private） ===");
        Method[] declaredMethods = userServiceClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("方法名：" + method.getName() + "，修饰符：" + java.lang.reflect.Modifier.toString(method.getModifiers()));
        }

        // 3. 测试类上的isAnnotationPresent()和getAnnotation()
        System.out.println("\n=== 3. 类注解相关方法 ===");
        boolean hasClassAnno = userServiceClass.isAnnotationPresent(MyClassAnnotation.class);
        System.out.println("类上是否有@MyClassAnnotation：" + hasClassAnno);
        if (hasClassAnno) {
            MyClassAnnotation classAnno = userServiceClass.getAnnotation(MyClassAnnotation.class);
            System.out.println("类注解value：" + classAnno.value());
            // 测试annotationType()：获取注解类型
            Class<? extends Annotation> annoType = classAnno.annotationType();
            System.out.println("类注解的类型：" + annoType.getName());
        }

        // 4. 测试方法上的getAnnotations()（获取所有注解）
        System.out.println("\n=== 4. 方法注解相关方法 ===");
        try {
            // 获取login方法
            Method loginMethod = userServiceClass.getMethod("login", String.class, String.class);

            // 测试isAnnotationPresent()：方法是否有@MyMethodAnnotation
            boolean hasMethodAnno = loginMethod.isAnnotationPresent(MyMethodAnnotation.class);
            System.out.println("login方法是否有@MyMethodAnnotation：" + hasMethodAnno);

            // 测试getAnnotation()：获取指定注解
            MyMethodAnnotation methodAnno = loginMethod.getAnnotation(MyMethodAnnotation.class);
            if (methodAnno != null) {
                System.out.println("方法注解desc：" + methodAnno.desc() + "，version：" + methodAnno.version());
            }

            // 测试getAnnotations()：获取方法上的所有注解
            Annotation[] methodAnnotations = loginMethod.getAnnotations();
            System.out.println("login方法上的所有注解数量：" + methodAnnotations.length);
            for (Annotation anno : methodAnnotations) {
                System.out.println("方法注解类型：" + anno.annotationType().getSimpleName());
            }

            // 5. 测试getParameterAnnotations()：获取参数注解
            System.out.println("\n=== 5. getParameterAnnotations()：参数注解 ===");
            Annotation[][] paramAnnotations = loginMethod.getParameterAnnotations();
            for (int i = 0; i < paramAnnotations.length; i++) {
                System.out.println("第" + (i + 1) + "个参数的注解：");
                Annotation[] annotations = paramAnnotations[i];
                for (Annotation anno : annotations) {
                    if (anno instanceof MyParamAnnotation) {
                        MyParamAnnotation paramAnno = (MyParamAnnotation) anno;
                        System.out.println("  @MyParamAnnotation(required=" + paramAnno.required() + ")");
                    }
                    // 测试annotationType()：参数注解的类型
                    System.out.println("  注解类型：" + anno.annotationType().getSimpleName());
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}