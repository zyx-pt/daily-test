package reflect;

/**
 * 创建一个我们要使用反射操作的类
 * @Author zhengyongxian
 * @Date 2020/5/7
 */
@MyAnnotation(name = "xxx", value = "xxx")
public class TargetObject extends SuperClass implements IService{
    private String value;
    public String publicValue;

    public TargetObject() {
        value = "JavaGuide";
    }

    public TargetObject(String value){
        this.value = value;
    }

    private TargetObject(String value, String publicValue){
        this.value = value;
        this.publicValue = publicValue;
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("I is " + value);
    }

    @MyMethodAnno
    public void testMethodAnnotation(){

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPublicValue() {
        return publicValue;
    }

    public void setPublicValue(String publicValue) {
        this.publicValue = publicValue;
    }
}
