package 算法.排序;

import java.util.Arrays;

import org.jetbrains.annotations.NotNull;

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

    /**
     * @Description
     * 插入排序(Insertion Sort)　　插入排序是一种简单直观的排序算法。它的工作原理非常类似于我们抓扑克牌
     * 对于未排序数据(右手抓到的牌)，在已排序序列(左手已经排好序的手牌)中从后向前扫描，找到相应位置并插入。
     * 　　插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     * 　　具体算法描述如下：
     * 	1. 从第一个元素开始，该元素可以认为已经被排序
     * 	2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 	3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 	4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 	5. 将新元素插入到该位置后
     * 	6. 重复步骤2~5
     *分类 -------------内部比较排序
     * // 数据结构 ---------- 数组
     * // 最差时间复杂度 ---- 最坏情况为输入序列是降序排列的,此时时间复杂度O(n^2)
     * // 最优时间复杂度 ---- 最好情况为输入序列是升序排列的,此时时间复杂度O(n)
     * // 平均时间复杂度 ---- O(n^2)
     * // 所需辅助空间 ------ O(1)
     * // 稳定性 ------------ 稳定
     *
     * @Param [a]
     * @return
     * @Date 2020/7/21
     * @author FanRuikang
     **/
    static void InsertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {// 类似抓扑克牌排序
            int get = a[i];// 右手抓到一张扑克牌
            int j = i - 1;// 拿在左手上的牌总是排序好的
            while (j >= 0 && a[j] > get) {// 将抓到的牌与手牌从右向左进行比较
                a[j + 1] = a[j];// 如果该手牌比抓到的牌大，就将其右移
                j--;// 直到该手牌比抓到的牌小(或二者相等)，将抓到的牌插入到该手牌右边(相等元素的相对次序未变，所以插入排序是稳定的)
            }
            a[j + 1] = get;

            System.out.println("插入第i 个元素： " + i);
            printArray(a);
        }
    }

    private static void printArray(int[] a) {
        for (int k = 0; k < a.length; k++) {
            System.out.print(a[k] + "    ");
        }
        System.out.println("");
    }

    /**
     * @Description
     * 鸡尾酒排序，也叫定向冒泡排序，是冒泡排序的一种改进。此算法与冒泡排序的不同处在于从低到高然后从高到低，而冒泡排序则仅从低到高去比较序列里的每个元素。他可以得到比冒泡排序稍微好一点的效能。
     * // 分类 -------------- 内部比较排序
     * // 数据结构 ---------- 数组
     * // 最差时间复杂度 ---- O(n^2)
     * // 最优时间复杂度 ---- 如果序列在一开始已经大部分排序过的话,会接近O(n);加一个比较次数的标志，等于0就终止循环
     * // 平均时间复杂度 ---- O(n^2)
     * // 所需辅助空间 ------ O(1)
     * // 稳定性 ------------ 稳定
     * @Param [a]
     * @return
     * @Date 2020/7/21
     * @author FanRuikang
     **/
    static void CocktailSort(int[] a) {
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            for (int i = left; i < right; i++) {// 前半轮,将最大元素放到后面
                if (a[i] > a[i + 1]) {
                    a[i + 1] = a[i] + a[i + 1];//不用额外空间的置换两个数；缺点：易溢出
                    a[i] = a[i + 1] - a[i];
                    a[i + 1] = a[i + 1] - a[i];
                }
            }
            right--;

            for (int i = right; i > left; i--) {// 后半轮,将最小元素放到前面
                if (a[i - 1] > a[1]) {
                    a[i - 1] = a[i] + a[i - 1];//不用额外空间的置换两个数；缺点：易溢出
                    a[i] = a[i - 1] - a[i];
                    a[i - 1] = a[i - 1] - a[i];
                }
            }
            left++;

            System.out.println("鸡尾酒排序来回：");
            System.out.println("right = " + right);
            System.out.println("left = " + left);
            printArray(a);
        }
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
     * // 最优时间复杂度 ---- 如果能在内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n);加一个比较次数的标志，等于0就终止循环
     * // 平均时间复杂度 ---- O(n^2)
     * // 所需辅助空间 ------ O(1)
     * // 稳定性 ------------ 稳定
     * 尽管冒泡排序是最容易了解和实现的排序算法之一，但它对于少数元素之外的数列排序是很没有效率的。
     * @param a
     */
    static void BubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    a[j + 1] = a[j] + a[j + 1];//不用额外空间的置换两个数；缺点：易溢出
                    a[j] = a[j + 1] - a[j];
                    a[j + 1] = a[j + 1] - a[j];
                }

                System.out.print("冒泡排序第" + (i + 1) + "趟第" + (j + 1) + "次结果:");
                printArray(a);
            }
        }
    }

    /**
     * @Description
     * 选择排序(Selection Sort)　　选择排序也是一种简单直观的排序算法。它的工作原理很容易理解：初始时在序列中找到最小（大）元素，放到序列的起始位置作为已排序序列；然后，再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     * 　　注意选择排序与冒泡排序的区别：冒泡排序通过依次交换相邻两个顺序不合法的元素位置，从而将当前最小（大）元素放到合适的位置；而选择排序每遍历一次都记住了当前最小（大）元素的位置，最后仅需一次交换操作即可将其放到合适的位置。
     * 　　选择排序的代码如下：
     *     分类 -------------- 内部比较排序
     *      数据结构 ---------- 数组
     *      最差时间复杂度 ---- O(n^2)
     *      最优时间复杂度 ---- O(n^2)
     *      平均时间复杂度 ---- O(n^2)
     *      所需辅助空间 ------ O(1)
     *      稳定性 ------------ 不稳定
     *      选择排序是不稳定的排序算法，不稳定发生在最小元素与A[i]交换的时刻。
     * 　　比如序列：{ 5, 8, 5, 2, 9 }，一次选择的最小元素是2，然后把2和第一个5进行交换，从而改变了两个元素5的相对次序。
     * @Param [a]
     * @return
     * @Date 2020/7/21
     * @author FanRuikang
     **/
    static void SelectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {// 找出未排序序列中的最小值
                    min = j;
                }
            }

            if (min != i) {// 放到已排序序列的末尾，该操作很有可能把稳定性打乱，所以选择排序是不稳定的排序算法
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }

            System.out.printf("选择排序第" + (i + 1) + "趟");
            printArray(a);
        }
    }

    public static void main(String[] args) {
        int[] a = {8, 9, 1, 3, 4, 7};
//        BubbleSort(a);
//        CocktailSort(a);
//        SelectionSort(a);
//        InsertionSort(a);
//        InsertionSortDichotomy(a);
//        ShellSort(a);
//        MergeSortRecursion(a, 0, a.length - 1);
//        MergeSortIteration(a);
//        HeapSort(a,a.length);
        QuickSort(a, 0, a.length - 1);
        printArray(a);
    }

    static void MergeSortIteration(int[] a) // 非递归(迭代)实现的归并排序(自底向上)
    {
        int left, mid, right;// 子数组索引,前一个为A[left...mid]，后一个子数组为A[mid+1...right]
        for (int i = 1; i < a.length; i *= 2) // 子数组的大小i初始为1，每轮翻倍
        {
            left = 0;
            while (left + i < a.length - 1) // 后一个子数组存在(需要归并)
            {
                mid = left + i - 1;
                right = mid + i < a.length ? mid + i : a.length - 1;// 后一个子数组大小可能不够
                Merge(a, left, mid, right);
                left = right + 1; // 前一个子数组索引向后移动
            }
        }
    }

    private static void Merge(int[] a, int left, int mid, int right) {
        int[] temp = Arrays.copyOfRange(a, mid + 1, right + 1);//需要的额外的空间
        int index1 = mid, index2 = temp.length - 1;
        int indexMerge = right;
        while (index1 >= left || index2 >= 0) {//从尾部向头部方向添加元素，不需要额外空间
            if (index1 < left) {//如果左边弄完了，添加右边剩余元素
                a[indexMerge--] = temp[index2--];
            } else if (index2 < 0) { //右边添加完，添加左边剩余元素
                a[indexMerge--] = a[index1--];
            } else if (a[index1] > temp[index2]) { //从小到大排序
                a[indexMerge--] = a[index1--];
            } else {
                a[indexMerge--] = temp[index2--];
            }
        }
    }

    /**
     * @Description
     * 插入排序的改进：二分插入排序　　对于插入排序，如果比较操作的代价比交换操作大的话，可以采用二分查找法来减少比较操作的次数，我们称为二分插入排序，代码如下：
     * // 分类 -------------- 内部比较排序
     * // 数据结构 ---------- 数组
     * // 最差时间复杂度 ---- O(n^2)
     * // 最优时间复杂度 ---- O(nlogn)
     * // 平均时间复杂度 ---- O(n^2)
     * // 所需辅助空间 ------ O(1)
     * // 稳定性 ------------ 稳定
     * 当n较大时，二分插入排序的比较次数比直接插入排序的最差情况好得多，但比直接插入排序的最好情况要差，所当以元素初始序列已经接近升序时，直接插入排序比二分插入排序比较次数少。二分插入排序元素移动次数与直接插入排序相同，依赖于元素初始序列。
     * @Param [a]
     * @return
     * @Date 2020/7/21
     * @author FanRuikang
     **/
    static void InsertionSortDichotomy(@NotNull int[] a) {
        for (int i = 1; i < a.length; i++) {
            int get = a[i];// 右手抓到一张扑克牌
            int left = 0;// 拿在左手上的牌总是排序好的，所以可以用二分法
            int right = i - 1;// 手牌左右边界进行初始化
            while (left <= right) {// 采用二分法定位新牌的位置
                int mid = (left + right) / 2;
                if (a[mid] > get) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            System.out.println("i = " + i);
            printArray(a);
            for (int j = i - 1; j >= left; j--) {// 将欲插入新牌位置右边的牌整体向右移动一个单位
                a[j + 1] = a[j];
                printArray(a);
            }
            a[left] = get;// 将抓到的牌插入手牌
            printArray(a);
        }
    }

    /**
     * @Description
     * 插入排序的更高效改进：希尔排序(Shell Sort)　　希尔排序，也叫递减增量排序，是插入排序的一种更高效的改进版本。希尔排序是不稳定的排序算法。
     * 　　希尔排序是基于插入排序的以下两点性质而提出改进方法的：
     * 	*
     * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
     * 	*
     * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位
     *
     *
     * 　　希尔排序通过将比较的全部元素分为几个区域来提升插入排序的性能。这样可以让一个元素可以一次性地朝最终位置前进一大步。然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，但是到了这步，需排序的数据几乎是已排好的了（此时插入排序较快）。
     * 　　假设有一个很小的数据在一个已按升序排好序的数组的末端。如果用复杂度为O(n^2)的排序（冒泡排序或直接插入排序），可能会进行n次的比较和交换才能将该数据移至正确位置。而希尔排序会用较大的步长移动数据，所以小数据只需进行少数比较和交换即可到正确位置。
     * 　　希尔排序的代码如下：
     *
     * // 分类 -------------- 内部比较排序
     * // 数据结构 ---------- 数组
     * // 最差时间复杂度 ---- 根据步长序列的不同而不同。已知最好的为O(n(logn)^2)
     * // 最优时间复杂度 ---- O(n)
     * // 平均时间复杂度 ---- 根据步长序列的不同而不同。
     * // 所需辅助空间 ------ O(1)
     * // 稳定性 ------------ 不稳定
     * 以23, 10, 4, 1的步长序列进行希尔排序：　　
     * 　　希尔排序是不稳定的排序算法，虽然一次插入排序是稳定的，不会改变相同元素的相对顺序，但在不同的插入排序过程中，相同的元素可能在各自的插入排序中移动，最后其稳定性就会被打乱。
     * 　　比如序列：{ 3, 5, 10, 8, 7, 2, 8, 1, 20, 6 }，h=2时分成两个子序列 { 3, 10, 7, 8, 20 } 和  { 5, 8, 2, 1, 6 } ，未排序之前第二个子序列中的8在前面，现在对两个子序列进行插入排序，得到 { 3, 7, 8, 10, 20 } 和 { 1, 2, 5, 6, 8 } ，即 { 3, 1, 7, 2, 8, 5, 10, 6, 20, 8 } ，两个8的相对次序发生了改变。
     * @Param [a]
     * @return
     * @Date 2020/7/21
     * @author FanRuikang
     **/
    static void ShellSort(int[] a) {
        int h = 0;
        while (h <= a.length) {// 生成初始增量
            h = 3 * h + 1;
        }
        while (h >= 1) {
            printArray(a);
            for (int i = h; i < a.length; i++) {
                int j = i - h;
                int get = a[i];
                System.out.println("i = " + i);
                System.out.println("h = " + h);
                System.out.println("get a[i] = " + get);
                while (j >= 0 && a[j] > get) {
                    a[j + h] = a[j];
                    System.out.println("j = " + j + "  a[j]和a[i]换");
                    printArray(a);
                    j = j - h;
                }
                a[j + h] = get;
                printArray(a);
                System.out.println("");
            }
            h = (h - 1) / 3;// 递减增量
        }
    }

    /**
     * @Description
     * 归并排序(Merge Sort)　　归并排序是创建在归并操作上的一种有效的排序算法，效率为O(nlogn)，1945年由冯·诺伊曼首次提出。
     * 　　归并排序的实现分为递归实现与非递归(迭代)实现。递归实现的归并排序是算法设计中分治策略的典型应用，我们将一个大问题分割成小问题分别解决，然后用所有小问题的答案来解决整个大问题。非递归(迭代)实现的归并排序首先进行是两两归并，然后四四归并，然后是八八归并，一直下去直到归并了整个数组。
     * 　　归并排序算法主要依赖归并(Merge)操作。归并操作指的是将两个已经排序的序列合并成一个序列的操作，归并操作步骤如下：
     * 	1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
     * 	2.设定两个指针，最初位置分别为两个已经排序序列的起始位置
     * 	3.  比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
     * 	4. 重复步骤3直到某一指针到达序列尾
     * 	5.将另一序列剩下的所有元素直接复制到合并序列尾
     *
     * 　　归并排序的代码如下：
     *
     * // 分类 -------------- 内部比较排序
     * // 数据结构 ---------- 数组
     * // 最差时间复杂度 ---- O(nlogn)
     * // 最优时间复杂度 ---- O(nlogn)
     * // 平均时间复杂度 ---- O(nlogn)
     * // 所需辅助空间 ------ O(n)
     * // 稳定性 ------------ 稳定
     *
     * 归并排序除了可以对数组进行排序，还可以高效的求出数组小和（即单调和）以及数组中的逆序对
     * @Param [A, left, right]
     * @return
     * @Date 2020/7/21
     * @author FanRuikang
     **/
    static void MergeSortRecursion(int[] A, int left, int right) // 递归实现的归并排序(自顶向下)
    {
        if (left == right) // 当待排序的序列长度为1时，递归开始回溯，进行merge操作
            return;
        int mid = (left + right) / 2;
        MergeSortRecursion(A, left, mid);
        MergeSortRecursion(A, mid + 1, right);
        Merge(A, left, mid, right);
    }

    static void Swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    static void Heapify(int A[], int i, int size) // 从A[i]向下进行堆调整
    {
        int left_child = 2 * i + 1; // 左孩子索引
        int right_child = 2 * i + 2; // 右孩子索引
        int max = i; // 选出当前结点与其左右孩子三者之中的最大值
        if (left_child < size && A[left_child] > A[max])
            max = left_child;
        if (right_child < size && A[right_child] > A[max])
            max = right_child;
        if (max != i) {
            Swap(A, i, max); // 把当前结点和它的最大(直接)子节点进行交换
            Heapify(A, max, size); // 递归调用，继续从当前结点向下进行堆调整
        }
    }

    static int BuildHeap(int A[], int n) // 建堆，时间复杂度O(n)
    {
        int heap_size = n;
        for (int i = heap_size / 2 - 1; i >= 0; i--) {// 从每一个非叶结点开始向下进行堆调整
            Heapify(A, i, heap_size);
        }
        return heap_size;
    }

    /**
     * @Description
     * 堆排序(Heap Sort)　　
     * 　　堆排序是指利用堆这种数据结构所设计的一种选择排序算法。堆是一种近似完全二叉树的结构（通常堆是通过一维数组来实现的），并满足性质：以最大堆（也叫大根堆、大顶堆）为例，其中父结点的值总是大于它的孩子节点。
     * 　　我们可以很容易的定义堆排序的过程：
     * 	1. 由输入的无序数组构造一个最大堆，作为初始的无序区
     * 	2. 把堆顶元素（最大值）和堆尾元素互换
     * 	3. 把堆（无序区）的尺寸缩小1，并调用heapify(A, 0)从新的堆顶元素开始进行堆调整
     * 	4. 重复步骤2，直到堆的尺寸为1
     *
     *
     * 　　堆排序的代码如下：
     *
     * // 分类 -------------- 内部比较排序
     * // 数据结构 ---------- 数组
     * // 最差时间复杂度 ---- O(nlogn)
     * // 最优时间复杂度 ---- O(nlogn)
     * // 平均时间复杂度 ---- O(nlogn)
     * // 所需辅助空间 ------ O(1)
     * // 稳定性 ------------ 不稳定
     * 　堆排序是不稳定的排序算法，不稳定发生在堆顶元素与A[i]交换的时刻。
     * 　　比如序列：{ 9, 5, 7, 5 }，堆顶元素是9，堆排序下一步将9和第二个5进行交换，得到序列 { 5, 5, 7, 9 }，再进行堆调整得到{ 7, 5, 5, 9 }，重复之前的操作最后得到{ 5, 5, 7, 9 }从而改变了两个5的相对次序
     * @Param [A, n]
     * @return
     * @Date 2020/7/21
     * @author FanRuikang
     **/
    static void HeapSort(int A[], int n) {
        int heap_size = BuildHeap(A, n); // 建立一个最大堆
        while (heap_size > 1) // 堆（无序区）元素个数大于1，未完成排序
        {
// 将堆顶元素与堆的最后一个元素互换，并从堆中去掉最后一个元素
// 此处交换操作很有可能把后面元素的稳定性打乱，所以堆排序是不稳定的排序算法
            Swap(A, 0, --heap_size);
            Heapify(A, 0, heap_size); // 从新的堆顶元素开始向下进行堆调整，时间复杂度O(logn)
        }
    }

    static int Partition(int A[], int left, int right) // 划分函数
    {
        int pivot = A[right]; // 这里每次都选择最后一个元素作为基准
        int tail = left - 1; // tail为小于基准的子数组最后一个元素的索引
        for (int i = left; i < right; i++) // 遍历基准以外的其他元素
        {
            if (A[i] <= pivot) // 把小于等于基准的元素放到前一个子数组末尾
            {
                Swap(A, ++tail, i);
            }
        }
        Swap(A, tail + 1, right); // 最后把基准放到前一个子数组的后边，剩下的子数组既是大于基准的子数组,该操作很有可能把后面元素的稳定性打乱，所以快速排序是不稳定的排序算法
        return tail + 1; // 返回基准的索引
    }

    static void QuickSort(int A[], int left, int right) {
        if (left >= right)
            return;
        int pivot_index = Partition(A, left, right); // 基准的索引
        QuickSort(A, left, pivot_index - 1);
        QuickSort(A, pivot_index + 1, right);
    }
}
