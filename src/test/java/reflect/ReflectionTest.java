package reflect;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * 演示反射的操作
 * @Author zhengyongxian
 * @Date 2020/5/7
 */
public class ReflectionTest {

    /**
     * 类名	            用途
     * Class类	        代表类的实体，在运行的Java应用程序中表示类和接口
     * Field类	        代表类的成员变量（成员变量也称为类的属性）
     * Method类	        代表类的方法
     * Constructor类	代表类的构造方法
     * 方法                                      用途
     * forName(String className)	            根据类名返回类的对象
     * newInstance()	                        创建类的实例
     * getName()	                            获得类的完整路径名字
     * getConstructor(Class... parameterTypes)  获得某个公有构造方法
     * getConstructors()                        获得所有公有构造方法
     * getDeclaredConstructor(Class... parameterTypes) 获得某个构造方法
     * getDeclaredConstructors()                获取所有的构造方法
     *
     * getMethod(String name, Class...<?> parameterTypes)	获得该类某个公有的方法
     * getMethods()	                            获得该类所有公有的方法
     * getDeclaredMethod(String name, Class...<?> parameterTypes)	获得该类某个方法
     * getDeclaredMethods()	                    获得该类所有方法
     *
     * getField(String name)	                获得某个公有的属性对象
     * getFields()	                            获得所有公有的属性对象
     * getDeclaredField(String name)	        获得某个属性对象
     * getDeclaredFields()	                    获得所有属性对象
     *
     * invoke(Object obj, Object... args)	    传递object对象及参数调用该对象对应的方法
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     */
    @Test
    public void test() throws Exception{

        // 获取Class对象
        Class<?> targetClass1 = Class.forName("reflect.TargetObject");
        Class targetClass = TargetObject.class;

        // 获取类名
        System.out.println("类名:" + targetClass.getName()); // 获取的类名包含包信息
        System.out.println("类名:" + targetClass.getSimpleName());   // 只是获取类名，不包含包信息。

        // 获取类修饰符
        int modifiers = targetClass.getModifiers();
        boolean anAbstract = Modifier.isAbstract(modifiers);
        boolean aFinal = Modifier.isFinal(modifiers);
        boolean anInterface = Modifier.isInterface(modifiers);
        boolean aNative = Modifier.isNative(modifiers);
        boolean aPrivate = Modifier.isPrivate(modifiers);
        boolean aProtected = Modifier.isProtected(modifiers);
        boolean aPublic = Modifier.isPublic(modifiers);// true
        boolean aStatic = Modifier.isStatic(modifiers);
        boolean strict = Modifier.isStrict(modifiers);
        boolean aSynchronized = Modifier.isSynchronized(modifiers);
        boolean aTransient = Modifier.isTransient(modifiers);
        boolean aVolatile = Modifier.isVolatile(modifiers);
        System.out.println(modifiers);// 1
        System.out.println("isPublic:"+Modifier.isPublic(targetClass.getModifiers()));

        // 获取包信息
        System.out.println("包信息：" + targetClass.getPackage());// package reflect

        // 获取父类的Class对象
        Class superclass = targetClass.getSuperclass();
        System.out.println("父类对象：" + superclass.getSimpleName());

        // 获取接口信息 <只返回指定类实现的接口，不会返父类实现的接口>
        Class[] interfaces = targetClass.getInterfaces();
        for (Class aClass : interfaces) {
            System.out.println("实现的接口：" + aClass.getSimpleName());
        }
        // 获取类实例
        TargetObject targetObject = (TargetObject) targetClass.newInstance();
        TargetObject targetObject2 = (TargetObject) targetClass.getConstructor(null).newInstance();

        // 获取所有构造方法
        Constructor<?>[] declaredConstructors = targetClass.getDeclaredConstructors();
        System.out.println("所有构造方法：");
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
        // 所有public修饰的构造函数
        Constructor[] constructors = targetClass.getConstructors();
        // 获取对应参数的构造函数 需要捕获NoSuchMethodException异常
        Constructor constructor = targetClass.getConstructor(String.class);
        TargetObject targetObject3 = (TargetObject) constructor.newInstance("zhengyx");
        System.out.println(targetObject3.getValue());
        // 获取构造函数的参数
        Class[] constructorParameterTypes = constructor.getParameterTypes();

        // 获取类中所有定义的方法getDeclaredMethods()
        Method[] methods = targetClass.getDeclaredMethods();
        System.out.println("所有方法：");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        // 获取指定方法(public)并调用 如果是无参取null
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        System.out.println("调用publicMethod：");
        publicMethod.invoke(targetObject, "hehe");
        // 获取方法的成员方法参数
        Class<?>[] publicMethodParameterTypes = publicMethod.getParameterTypes();
        // 获取方法返回类型
        Class<?> publicMethodReturnType = publicMethod.getReturnType();

        // 获取指定参数并进行修改
        Field field = targetClass.getDeclaredField("value");
        // 为了对类中private成员变量进行修改，取消类的安全检查
        field.setAccessible(true);
        field.set(targetObject, "zyx");
        System.out.println(field.get(targetObject));
        System.out.println(field.getType());    // 获取成员变量类型

        // 调用private方法
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);

        // 访问类注解信息
        Annotation[] classAnnotations = targetClass.getAnnotations();
        for(Annotation annotation : classAnnotations){
            if(annotation instanceof MyClassAnnotation){
                MyClassAnnotation myClassAnnotation = (MyClassAnnotation) annotation;
                System.out.println("name: " + myClassAnnotation.name());
                System.out.println("value: " + myClassAnnotation.value());
            }
        }

        // 访问方法注解信息
        Method testMethodAnnotation = targetClass.getDeclaredMethod("testMethodAnnotation");
        Annotation[] methodAnnotations = testMethodAnnotation.getDeclaredAnnotations();
        for(Annotation annotation : methodAnnotations){
            if(annotation instanceof MyMethodAnno){
                MyMethodAnno myAnnotation = (MyMethodAnno) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }


        // 获取泛型信息

    }

    public static void printGettersSetters(Class aClass){
        Method[] methods = aClass.getMethods();

        for(Method method : methods){
            if(isGetter(method)) System.out.println("getter: " + method);
            if(isSetter(method)) System.out.println("setter: " + method);
        }
    }

    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }
}
