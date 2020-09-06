import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Description:
 * @Author zhengyongxian
 * @Date 2020/6/22 22:11
 */
public class TemplateTest {
    
    /**
     * @Description: 
     *
     * @Author: zhengyongxina
     * @Date: 2020/6/22 22:12
     * @param 
     * @return: void
     */
    @Test
    public void test1(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        ArrayList<Object> objects = new ArrayList<>();
        objects.forEach(x -> {
            System.out.println("cccc");
        });
    }

    @Test
    public void test2(){

        BigDecimal num1 = new BigDecimal("1");
        BigDecimal num2 = new BigDecimal(1);
        boolean result = num1.compareTo(num2)==0;
        System.out.println();
    }
    
    @Test
    public void test3(){
        String str1 = new String("111");
        String str2 = new String("111");
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        System.out.println(str1 != str2);

    }
}
