package main.study.datastructure.sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * 快速排序测试
 * @author Zhuqiuping on 2019/4/3
 */
public class QuickSort {

    //private Double[] a = new Double[]{11.43, 12.33, 2.43, 3.43, 5.54, 7.89, 10.32, 12.32, 2.32, 11.38,
    //        11.98, 12.54, 2.18};
    /**
     * 从上次的固定数组，变成随机数值，并且优化了部分代码
     */
    @Test
    public void testQuickSort() {
        int size = 13;
        Double[] a = new Double[size];
        System.out.println("排序前: ");
        //将数组填充成随机数
        for(int i = 0; i < size; i++) {
            //a[i] = Double.parseDouble(String.format("%.2f", Math.random() * 13D));
            System.out.print(a[i] + "\t");
        }
        System.out.println();
        System.out.println("排序后：");
        quick(a, 0, a.length);
        for(int i = 0; i < size; i++) {
            System.out.print(a[i] + "\t");
        }
    }

    /**
     * 快排
     * 原理：拿第一个元素先找出这个元素该存放的索引（在找出的过程中，将大于第一个元素都会放到该索引的右侧，小于该数值都会放到该索引左侧），
     * 然后递归的二分数组，进行第一个元素排序。
     *
     * @param a 待排序数组
     * @return 排完序的数组
     */
    public void quick(Double[] a, int low, int high) {
        if((a.length - 1) < low || low >= high) {
            return ;
        }
        int middleIndex = partition(a, low, high);

        quick(a, low, middleIndex);
        quick(a, middleIndex + 1, high);
    }

    /**
     * 找出元素第一个位置
     *
     * @param a        待排序的数组
     * @param low      低位元素索引
     * @param high     高位元素索引
     */
    private int partition(Double[] a, int low, int high) {
        double lowData = a[low];
        int numberIndex = low;
        //永真循环
        while(true) {
            //2019年4月8日，在运行的时候，这块一直都包数组越界异常，就是索引为13了。调试了一遍发现 最后两个元素的时候，low没有做边界判断。
            if((low + 1) < a.length && a[++low] < lowData) {
                continue;
            } else if(a[--high] > lowData) {
                if(a[low] > lowData) {
                    low--;
                }
                continue;
            }

            if(low >= high) {
                break;
            }

            if(a[low] > lowData && a[high] < lowData) {
                exchange(a, low, high);
            }

        }
        if(numberIndex != high) {
            exchange(a, numberIndex, high);
        }

        return high;
    }

    /**
     * 书本上的快排关键方法
     */
    public static int partition(Comparable[] a, int low, int high) {
        int i = low, j = high + 1;
        Comparable v = a[low];
        while(true) {
            while(less(a[++i], v)) {
                if(i == high) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if(j == low) {
                    break;
                }
            }

            if(i >= j) {
                break;
            }

            exch(a, i, j);
        }

        exch(a, low, j);
        return j;
    }

    public static boolean less(Comparable left, Comparable right) {
        return left.compareTo(right) < 0;
    }

    public static void exch(Comparable[] a, int left, int right) {
        Comparable temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    @Data
    @AllArgsConstructor
    class Person {
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Person(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }
    @Test
    public void test1() {
        Person person1 = new Person("2323", "34");
        Person person2 = new Person("2323", "34");
        Person person3 = new Person("2323", "34");
        Person person4 = new Person("2323", "34");
        List<Person> xx = Lists.newArrayList(person1, person2,person3,person4, null);
        Set<Person> xxSet = Sets.newHashSet(xx);
        xxSet.remove(null);
        System.out.println(xxSet.size());
    }

    /**
     * 交换元素
     *
     * @param a         待交换元素的数组
     * @param left      左边元素
     * @param right     右边元素
     */
    private void exchange(Double[] a, int left, int right) {
        double temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }
}
