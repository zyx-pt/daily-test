package utiltest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 测试StringUtils的相关使用
 * @Author zhengyongxian
 * @Date 2020/7/21 11:39
 */
public class StringUtilsTest {


    @Test
    public void test1(){
        // 判空
        // isNotBlank -> 非空;且非空字符串;且非全部空格的字符串
        boolean notBlank = StringUtils.isNotBlank("  ");// false
        // isNotEmpty -> 非空；非空字符串
        boolean notEmpty = StringUtils.isNotEmpty("  ");// true

        // 拼接字符串 可以用于拼接数组、集合
        String[] strings = {"a", "b", "c"};
        String join1 = StringUtils.join(new String[]{"a", "b", "c"}, ",");// a,b,c
        List<String> iterable = Arrays.asList(strings);
        String join2 = StringUtils.join(iterable, ",");// a,b,c
        List<String> collect = Arrays.stream(strings).collect(Collectors.toList());
        String join3 = StringUtils.join(collect, ",");// a,b,c

        // 拆分字符串
        String[] split = StringUtils.split("a.b.c", '.');
        System.out.println("split:" + Arrays.toString(split));// ["a", "b", "c"]

        // 截取字符串 -> 左包含，右不包含
        String substring = StringUtils.substring("abcba", 1);// bcba
        String substring2 = StringUtils.substring("abcba", 1, 3);// bc
        String substringBeforeLast = StringUtils.substringBeforeLast("abcba", "b");// abc
        String substringAfterLast = StringUtils.substringAfterLast("abcba", "b");// a

        // 判断包含字符串
        StringUtils.contains("abc", "A");// false
        StringUtils.containsIgnoreCase("abc", "A");// true

        // 前后去空格但可以规避 NPE
        String trimToEmpty1 = StringUtils.trimToEmpty(null);// ""
        String trimToEmpty2 = StringUtils.trimToEmpty("    abc    ");// "abc"
        String trimToEmpty3 = StringUtils.trimToEmpty("    a  b  c    ");// "a  b  c"

        // 加前缀
        String preUsername = StringUtils.prependIfMissing("username", "user.");// user.username
        //补齐，可用于生成格式化单号
        String leftPad = StringUtils.leftPad("15", 5, "0");// 00015 左补齐
        System.out.println(leftPad);
    }

    @Test
    public void testSubstring(){


    }

    @Test
    public void test3(){

    }
}
