package 算法.排序;

/**
 * 我们通常所说的排序算法往往指的是内部排序算法，即数据记录在内存中进行排序。
 * 　　排序算法大体可分为两种：
 * 　　　　一种是比较排序，时间复杂度O(nlogn) ~ O(n^2)，主要有：冒泡排序，选择排序，插入排序，归并排序，堆排序，快速排序等。
 * 　　　　另一种是非比较排序，时间复杂度可以达到O(n)，主要有：计数排序，基数排序，桶排序等。
 * 　　这里我们来探讨一下常用的比较排序算法，非比较排序算法将在下一篇文章中介绍。下表给出了常见比较排序算法的性能：
 * 有一点我们很容易忽略的是排序算法的稳定性(腾讯校招2016笔试题曾考过)。
 * 　　排序算法稳定性的简单形式化定义为：如果Ai = Aj，排序前Ai在Aj之前，排序后Ai还在Aj之前，则称这种排序算法是稳定的。通俗地讲就是保证排序前后两个相等的数的相对顺序不变。
 * 　　对于不稳定的排序算法，只要举出一个实例，即可说明它的不稳定性；而对于稳定的排序算法，必须对算法进行分析从而得到稳定的特性。需要注意的是，排序算法是否为稳定的是由具体算法决定的，不稳定的算法在某种条件下可以变为稳定的算法，而稳定的算法在某种条件下也可以变为不稳定的算法。
 * 　　例如，对于冒泡排序，原本是稳定的排序算法，如果将记录交换的条件改成A[i] >= A[i + 1]，则两个相等的记录就会交换位置，从而变成不稳定的排序算法。
 * 　　其次，说一下排序算法稳定性的好处。排序算法如果是稳定的，那么从一个键上排序，然后再从另一个键上排序，前一个键排序的结果可以为后一个键排序所用。基数排序就是这样，先按低位排序，逐次按高位排序，低位排序后元素的顺序在高位也相同时是不会改变的。
 */
public class CompaireSort {
    public static void main(String[] args) {
        int[] a = {2, 1, 3, 4, 7};
        BubbleSort(a);
    }

    /**
     * 冒泡排序(Bubble Sort)　　冒泡排序是一种极其简单的排序算法，也是我所学的第一个排序算法。它重复地走访过要排序的元素，依次比较相邻两个元素，如果他们的顺序错误就把他们调换过来，直到没有元素再需要交换，排序完成。这个算法的名字由来是因为越小(或越大)的元素会经由交换慢慢“浮”到数列的顶端。
     * 　　冒泡排序算法的运作如下：
     * 	1.比较相邻的元素，如果前一个比后一个大，就把它们两个调换位置。
     * 	2. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 	3. 针对所有的元素重复以上的步骤，除了最后一个。
     * 	4. 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     *
     * 　　由于它的简洁，冒泡排序通常被用来对于程序设计入门的学生介绍算法的概念。冒泡排序的代码如下：
     *
     * // 分类 -------------- 内部比较排序
     * // 数据结构 ---------- 数组
     * // 最差时间复杂度 ---- O(n^2)
     * // 最优时间复杂度 ---- 如果能在内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n)
     * // 平均时间复杂度 ---- O(n^2)
     * // 所需辅助空间 ------ O(1)
     * // 稳定性 ------------ 稳定
     * 尽管冒泡排序是最容易了解和实现的排序算法之一，但它对于少数元素之外的数列排序是很没有效率的。
     * @param a
     */
    static void BubbleSort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            for (int j = 0; j < a.length - i-1; j++) {
                if (a[j] < a[j + 1]) {
                    a[j + 1] = a[j] + a[j + 1];
                    a[j] = a[j+1] - a[j];
                    a[j + 1] = a[j+1] - a[j];
                }
                System.out.print("冒泡排序第"+(i+1)+"趟第"+(j+1)+"次结果:");
                for (int k = 0; k <a.length ; k++) {
                    System.out.print(a[k]+"    ");
                }
                System.out.println("");
            }
        }
    }


}
