import com.google.common.collect.Lists;
import entity.Account;
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
 * @Description: 临时测验
 * @Author zhengyongxian
 * @Date 2020/6/22 22:11
 */
public class TemplateTest {
    
    /**
     * @Description: 测试List Map的为空的循环问题
     * 结论：对List Map的初始化不能赋空值null，故对所传List Map参数要判空
     *      Map 中key可以为null
     * @Author: zhengyongxina
     * @Date: 2020/6/22 22:12
     * @param 
     * @return: void
     */
    @Test
    public void test1(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        // objectObjectHashMap = null; // java.lang.NullPointerException
        objectObjectHashMap.forEach((k, v) -> {
            System.out.println("aaaaa");
        });
        System.out.println(objectObjectHashMap.get(null));
        objectObjectHashMap.put(null, "1111");
        objectObjectHashMap.put(null, "2222");
        System.out.println(objectObjectHashMap.get(null)); // 2222

        ArrayList<Object> objects = new ArrayList<>();
        // objects = null; // java.lang.NullPointerException
        objects.forEach(x -> {
            System.out.println("cccc");
        });

    }
    
    /**
     * @Description: 测试不同Lists初始化
     *
     * @Author: zhengyongxina
     * @Date: 2020/9/28 15:49
     * @return: void
     */
    @Test
    public void test2(){
        String str1 = null;
        String str2 = "sss";
        // com.google.common.collect.Lists可以使用这种方式初始化
        List<String> stringList = Lists.newArrayList(str1, str2);
        List<String> list = Lists.newArrayListWithCapacity(16);
        System.out.println(StringUtils.join(stringList,"/"));
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
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String k = entry.getKey();
            List<String> v = entry.getValue();
        }
        boolean empty = map.isEmpty();

        map.put("1111", list1);
        System.out.println(map.get("xxx"));
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("输出的结果是：" + jsonObject);

        List<Account> accountList = Lists.newArrayList();

        Account account1 = new Account(null, 18);
        Account account2 = new Account("hehe", 18);
        accountList.add(account1);
        accountList.add(account2);
        // java.lang.NullPointerException: element cannot be mapped to a null key
        // Map<String, List<Account>> collect2 = accountList.stream().collect(Collectors.groupingBy(Account::getName));
        // System.out.println(collect2);
        // groupingBy前要进行过滤
         Map<String, List<Account>> collect3= accountList.stream()
                 .filter(item -> StringUtils.isNotEmpty(item.getName()))
                 .collect(Collectors.groupingBy(Account::getName));
         System.out.println(collect3);

    }
}
