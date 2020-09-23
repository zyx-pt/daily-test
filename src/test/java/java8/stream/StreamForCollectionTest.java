package java8.stream;

import entity.Account;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: stream在集合中的应用
 * @Author zhengyongxian
 * @Date 2020/8/28 15:01
 */
public class StreamForCollectionTest {

    @Test
    public void test() {
        /** 查询集合中符合条件的总条数 */
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account("qwe", 12, "beijing"));
        accountList.add(new Account("asd", 15, "shanghai"));
        accountList.add(new Account("zxc", 18, "xiamen"));
        accountList.add(new Account("hehe", 21, "hangzhou"));
        accountList.add(new Account("zyx", 24, "putian"));
        accountList.add(new Account("zyx", 24, "putian"));

        /** 获取集合中某个属性不重复的集合 */
        List<String> distinctAccountByName = accountList.stream()
                .map(Account::getName).distinct().collect(Collectors.toList());
        /** 筛选集合中符合某些条件 */
        List<Account> filterAccountByAge = accountList.stream()
                .filter(item -> item.getAge() > 18).collect(Collectors.toList());
        /** 根据某一属性筛选 (去重) */
        List<Account> distinctByName = accountList.stream()
                .filter(distinctByName(Account::getName)).collect(Collectors.toList());
        /** sorted 排序 */
        List<Account> collect = accountList.stream()
                .sorted(Comparator.comparing(Account::getAge)).collect(Collectors.toList());

        /** groupingBy 统计的使用 */
        System.out.println("----------------groupingBy----------------");
        /** 统计集合中某一属性的个数，用Map返回 */
        Map<String, Long> nameCountMap = accountList.stream()
                .filter(p -> StringUtils.isNotEmpty(p.getName()))
                .collect(Collectors.groupingBy(Account::getName, Collectors.counting()));
        System.out.println("name -> count"+nameCountMap);
        /** 把集合存储到Map中，key为某个属性，value为集合 */
        Map<Integer, List<Account>> ageToListMap = accountList.stream()
//                .collect(Collectors.groupingBy(Account::getAge, Collectors.toList()));
                .collect(Collectors.groupingBy(Account::getAge));
        System.out.println("age -> list："+ageToListMap);
        /** 统计后按key进行排序 */
        // TreeMap默认为按照key升序
        TreeMap<String, List<Account>> orderByNameToListMap = accountList.stream()
                .filter(item -> StringUtils.isNotEmpty(item.getName()))
                .collect(Collectors.groupingBy(Account::getName, TreeMap::new, Collectors.toList()));

        // 降序
        NavigableMap<String, List<Account>> stringListNavigableMap = orderByNameToListMap.descendingMap();
        /** 累加求和 */
        Map<String, Integer> nameToCountAgeMap = accountList.stream()
                .collect(Collectors.groupingBy(Account::getName, Collectors.summingInt(Account::getAge)));
        System.out.println("name -> ageCount："+nameToCountAgeMap);
        /** 统计后拼接某一属性 */
        Map<String, String> nameToJoinAddressMap = accountList.stream()
                .collect(Collectors.groupingBy(Account::getName, Collectors.mapping(Account::getAddress, Collectors.joining(","))));
        System.out.println("name -> join address："+nameToJoinAddressMap);

        /** 把集合存储到Map中，key为某个属性，value为另一个属性 */
        System.out.println("----------------toMap----------------");
        Map<String, String> nameToAddressMap1 = accountList.stream()
                .collect(Collectors.toMap(Account::getName, Account::getAddress, (oldValue, newValue)->newValue));
        Map<String, String> nameToAddressMap2 = accountList.stream()
                .collect(Collectors.toMap(Account::getName, Account::getAddress, (oldValue, newValue)->oldValue+","+newValue));
        System.out.println(nameToAddressMap1);
        System.out.println(nameToAddressMap2);
        /** 把集合存储到Map中，key为某个属性，value为对象 */
//        Map<String, Account> nameToAccountMap1 = Maps.uniqueIndex(accountList, Account::getName);
//        Map<String, Account> nameToAccountMap2 = accountList.stream()
//                .collect(Collectors.toMap(Account::getName, Function.identity()));
        /** 注意点：转换成Map时，集合中作为key的属性存在重复，则会报错duplicate key，需要另行处理 */
        // duplicate key的处理方案
        Map<String, Account> nameToAccountMap3 = accountList.stream()
                .collect(Collectors.toMap(Account::getName, Function.identity(), (v1, v2) -> v2));
        System.out.println(nameToAccountMap3);
        // Map的value可以储存一个list，把重复key的值放入list，再存到value中
        Map<String, List<String>> nameToList = accountList.stream()
                .collect(Collectors.toMap(Account::getName,
                        item -> {
                            List<String> getAddressList = new ArrayList<>();
                            getAddressList.add(item.getAddress());
                            return getAddressList;
                        },
                        (List<String> oldList, List<String> newList) -> {
                            oldList.addAll(newList);
                            return oldList;
                        }));
        System.out.println(nameToList);

        /** String-List-Map互转 */
        String str1 = "a,b,c";
        String str2 = "1,1,2,3,3";
        // String（，隔开）==> List<String> 注意：str1为null会报错，空字符串不会，建议进行判空
        List<String> list1= Arrays.asList(StringUtils.split(str1, ","));
        // String（，隔开）==> List<Long>:
        List<Long> list2 = Arrays.asList(str2.split(","))
                .stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        // List<String> ==> String (，隔开)
        String lsitToStr = StringUtils.join(list1, ",");

        // 统计数量
        Map<Long, Long> countListToMap = countListToMap(list2);
        System.out.println(countListToMap);

        // sorted排序
        Random random = new Random();
//        random.ints().limit(10).sorted().forEach(System.out::println);

        // parallel并行
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        Long count = strings.parallelStream().filter(StringUtils::isEmpty).count();

        // Collectors归约操作toList toMap counting joining
        System.out.println("-----------筛选------------");
        List<String> filtered = strings.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(StringUtils::isNotEmpty).collect(Collectors.joining(","));
        System.out.println("合并字符串: " + mergedString);

        // 统计 使用统计结果的收集器
        System.out.println("------------统计收集器的使用-------------");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());


    }

    /**
     * @Description: 统计数量
     *
     * @Author: zhengyongxian
     * @Date: 2020/9/3 17:51
     * @param list
     * @return: java.util.Map<java.lang.Long,java.lang.Long>
     */
    public static Map<Long,Long> countListToMap(List<Long> list){
        Map<Long, Long> map = new HashMap<>();
        Set<Long> set = new HashSet<>(list);
        for (Long productIdOnly : set) {
            for (Long productId : list) {
                if (productId.equals(productIdOnly)) {
                    if (map.containsKey(productIdOnly)) {
                        Long count = map.get(productIdOnly);
                        count++;
                        map.put(productIdOnly, count);
                    } else {
                        map.put(productIdOnly, 1L);
                    }
                }
            }
        }
        return map;
    }

    /**
     * @Description: 根据某一属性去重
     *
     * @Author: zhengyongxian
     * @Date: 2020/9/14 14:07
     * @param keyExtractor
     * @return: java.util.function.Predicate<T>
     */
    private static <T> Predicate<T> distinctByName(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;

        // putIfAbsent
        // key不存在或者key已存在但是值为null --> put进去，返回结果null
        // 如果结果等于null，说明该对象的不重复,返回true --> filter恰好会留下表达式为true的数据
    }
}
