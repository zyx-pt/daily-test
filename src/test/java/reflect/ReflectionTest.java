package reflect;

import org.junit.Test;

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
     * 方法                                           用途
     *forName(String className)	                根据类名返回类的对象
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
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        // 获取Class对象
        Class<?> targetClass = Class.forName("reflect.TargetObject");
        Class targetClass2 = TargetObject.class;

        // 获取类名
        System.out.println(targetClass2.getName()); // 获取的类名包含包信息
        System.out.println(targetClass2.getSimpleName());   // 只是获取类名，不包含包信息。

        // 获取类修饰符
        System.out.println(targetClass2.getModifiers());
        System.out.println("isPublic:"+Modifier.isPublic(targetClass2.getModifiers())
                + "---isPrivate:"+Modifier.isPrivate(targetClass2.getModifiers()));

        // 获取类实例
        TargetObject targetObject = (TargetObject) targetClass.newInstance();
        TargetObject targetObject2 = (TargetObject) targetClass.getConstructor(null).newInstance();

        // 获取所有构造方法
        Constructor<?>[] declaredConstructors = targetClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        // 获取类中所有定义的方法getDeclaredMethods()
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        // 获取指定方法并调用
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "hehe");

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
        // 获取泛型信息

    }
}
