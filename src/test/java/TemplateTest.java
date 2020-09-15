import com.google.common.collect.Lists;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        System.out.println(objectObjectHashMap.get(null));
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
        List<String> list1 = Lists.newArrayList();

        list1.add("xx");

        List<String> collect = list1.stream().filter(item -> StringUtils.isEmpty(item)).collect(Collectors.toList());
        List<String> collect1 = collect.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());

        Map<String, List<String>> map = new HashMap();
        map.put("1111", list1);
        System.out.println(map.get("xxx"));
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("输出的结果是：" + jsonObject);
    }
}
