import org.junit.Test;

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

    }
    
    @Test
    public void test3(){

    }
}
