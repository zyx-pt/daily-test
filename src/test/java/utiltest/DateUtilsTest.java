package utiltest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: Date DateUtils DateFormatUtils 测试
 * @Author zhengyongxian
 * @Date 2020/7/21 16:11
 */
public class DateUtilsTest {
    public final static FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Test
    public void testDate2String(){
        Date date = new Date();
        String date2Str1 = DateFormatUtils.format(date, "yyyy-MM-dd");
        String date2Str2 = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        String date2Str3 = DATE_FORMAT.format(date);
    }

    @Test
    public void testString2Date() throws Exception{
        String str = "2020-5-20";
        String str2 = "2020/5/20";
        Date str2Date1 = DATE_FORMAT.parse(str);
        // 如果格式不对应报错
        // Date str2Date11 = DATE_FORMAT.parse(str2);//java.text.ParseException: Unparseable date: 2020/5/20
        // 根据 pattern解析
        Date str2Date2 = DateUtils.parseDate(str, "yyyy-MM-dd");
        // 许宽泛解析时, 可传入多个 pattern, 工具类将逐个尝试解析
        Date str2DateMulti1 = DateUtils.parseDate(str, "yyyy-MM-dd", "yyyy-MM-dd HH:mm");
        Date str2DateMulti2 = DateUtils.parseDate("2020-5-20 13:14", "yyyy-MM-dd", "yyyy-MM-dd HH:mm");
    }

    @Test
    public void testAddDate(){
        // 提供 addDays, addMinutes, addSeconds 等操作方法
        Date date = new Date();
        int amount = 1;
        Date addDays = DateUtils.addDays(date, amount);
        Date addHours = DateUtils.addHours(date, amount);
        System.out.println("now date:"+date);
        System.out.println("add "+amount+" Days:"+addDays);
        System.out.println("add "+amount+" Hours:"+addHours);

    }

    @Test
    public void testParseDateCompareToNewDate()throws Exception{
        String str = "2020-10-20";
        Date nowDate = new Date();
        Date str2Date2 = DateUtils.parseDate(str, "yyyy-MM-dd");
        System.out.println("now:" + nowDate);
        System.out.println("date:" + str2Date2);
        System.out.println("str2Date2 compareTo nowDate:"+str2Date2.compareTo(nowDate));
    }

    @Test
    public void testDate(){
        // 获取今年的天数
        int daysOfThisYear = LocalDate.now().lengthOfYear();
        System.out.println("days of this year:" + daysOfThisYear); // 366
        // 获取指定某年的天数
        int daysOfMonth2020_2 = LocalDate.of(2020, 2, 1).lengthOfMonth();
        System.out.println("days of Month 2020_2:" + daysOfMonth2020_2); //29
        // 使用枚举值来指代月份：0~11
        System.out.println("Calender enum january:"+ Calendar.JANUARY);
        System.out.println("Calender enum february:"+ Calendar.FEBRUARY);

    }
    // LocalDate 和 DateTimeFormatter
    //


}
