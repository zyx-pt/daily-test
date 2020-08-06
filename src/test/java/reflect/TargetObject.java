package reflect;

/**
 * 创建一个我们要使用反射操作的类
 * @Author zhengyongxian
 * @Date 2020/5/7
 */

public class TargetObject {
    private String value;
    public String publicValue;

    public TargetObject() {
        value = "JavaGuide";
    }

    private TargetObject(String value){
        this.value = value;
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("I is " + value);
    }
}
