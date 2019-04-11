package DemoOfExam.sort;

/**
 * @ClassName sort
 * @Description TODO
 * @Auto 宗坚
 * @Date 2019/4/11 15:47
 * @Version 1.0
 */
public class sort {


    /**
    * @Author 宗坚
    * @Description 冒泡
    * @Since 15:49 2019/4/11
    * @Param [arr]
    * @return void
    */
    static void bubbleSort(int[] arr){
        for(int end = arr.length - 1; end > 0; end--){
            boolean isSort = true;
            for(int i = 0; i < end; i++){
                if(arr[i] > arr[i+1]) {
                    swap(arr, i, i + 1);
                    isSort = false;
                }
            }
            if(isSort) break;
        }
    }

    /**
    * @Author 宗坚
    * @Description 选择
    * @Since 15:49 2019/4/11
    * @Param [arr]
    * @return void
    */
    static void selectSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int minIdx = i;
            for(int j = i + 1; j < arr.length; j++) minIdx = arr[j] < arr[minIdx] ? j : minIdx;
            swap(arr, minIdx, i);
        }
    }

    /**
    * @Author 宗坚
    * @Description 插入
    * @Since 15:50 2019/4/11
    * @Param [arr]
    * @return void
    */
    // 几个边界: i=1开始(不是必须)、j >= 0, arr[j+1] = key注意一下
    static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j;
            for (j = i - 1; j >= 0 && arr[j] > key; j--) arr[j + 1] = arr[j];
            arr[j + 1] = key;
        }
    }

    /**
    * @Author 宗坚
    * @Description 希尔排序    采取的是增量序列每次减半的策略。
    * @Since 15:51 2019/4/11
    * @Param [arr]
    * @return void
    */
    static void shellSort(int[] arr) {
        for (int g = arr.length; g > 0; g /= 2) { // 增量序列 gap
            for (int end = g; end < arr.length; end++) { // 每一个组的结束元素, 从数组第gap个元素开始
                // 每组做插入排序
                int key = arr[end], i;
                for (i = end - g; i >= 0 && key < arr[i]; i -= g) arr[i + g] = arr[i];
                arr[i + g] = key;
            }
        }
    }

    /**
    * @Author 宗坚
    * @Description 快排 三路快排
    * @Since 15:52 2019/4/11
    * @Param [arr]
    * @return void
    */
    static void quickSort(int[] arr){
        if(arr == null || arr.length == 0) return;
        quickRec(arr, 0, arr.length - 1);
    }

    static void quickRec(int[] arr, int L, int R) {
        if (L >= R) return;
        swap(arr, L, L + (int) (Math.random() * (R - L + 1)));
        int[] p = partition(arr, L, R);
        quickRec(arr, L, p[0] - 1);
        quickRec(arr, p[1] + 1, R);
    }

    // 用arr[L]作为划分点
    static int[] partition(int[] arr, int L, int R) {
        int key = arr[L];
        int less = L, more = R + 1;
        int cur = L + 1;
        while (cur < more) {
            if (arr[cur] < key) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > key) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        swap(arr, L, less);
        // 返回相等的两个下标，　less位置是我最后交换过来的划分值，more位置是>的，所以返回more-1
        return new int[]{less, more - 1};
    }



    /**
    * @Author 宗坚
    * @Description 归并排序
    * @Since 15:53 2019/4/11
    * @Param [arr]
    * @return void
    */
    static void mergeSort(int[] arr){
        if(arr == null || arr.length == 0) return;
        mergeRec(arr, 0, arr.length - 1);
    }

    //注意是mergeSort(arr, L, m); 不是mergeSort(arr, L, m-1)
    static void mergeRec(int[] arr, int L, int R) {
        if (L >= R) return;
        int m = L + (R - L) / 2;
        mergeRec(arr, L, m);
        mergeRec(arr, m + 1, R);
        merge(arr, L, m, R);
    }

    static void merge(int[] arr, int L, int mid, int R) {
        int[] h = new int[R - L + 1];
        int p1 = L, p2 = mid + 1;
        int k = 0;
        while (p1 <= mid && p2 <= R)
            h[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];  // 注意保证稳定性
        while (p1 <= mid) h[k++] = arr[p1++];
        while (p2 <= R) h[k++] = arr[p2++];
        for (int i = 0; i < k; i++) arr[L + i] = h[i];
    }

    /**
    * @Author 宗坚
    * @Description 非递归归并排序
    * @Since 15:53 2019/4/11
    * @Param [arr]
    * @return void
    */
    static void mergeSortBU(int[] arr) {
        for (int sz = 1; sz <= arr.length; sz += sz) { // 区间的个数，1..2..4..8
            for (int i = 0; sz + i < arr.length; i += sz + sz) {  // 对[i...i+sz-1]和[i+sz...i+2*sz-1]内归并
                merge(arr, i, i + sz - 1, Math.min(arr.length - 1, i + 2 * sz - 1)); // min防止越界
            }
        }
    }


    /**
    * @Author 宗坚
    * @Description 堆排  使用heapfiy的优化，只需要使用siftDown过程即可
    * @Since 15:55 2019/4/11
    * @Param [arr]
    * @return void
    */
    // 注意这里是size+1,因为这个不是交换了最后一个，所以要考虑arr[size]，下面不要考虑arr[size]
//   if (arr == null || arr.length <= 1) return; 是必须的
    static void heapSort2(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int size = arr.length - 1;
        for (int i = (size - 1) / 2; i >= 0; i--)
            siftDown(arr, i, size + 1);
        swap(arr, 0, size);
        while (size > 0) {
            siftDown(arr, 0, size);
            swap(arr, 0, --size);
        }
    }
    //其中siftDown过程也可以使用递归的写法
    static void siftDown(int[] arr, int i, int heapSize) { //从arr[i] 开始往下调整
        int L = 2 * i + 1;
        int R = 2 * i + 2;
        int maxIdx = i;
        if (L < heapSize && arr[L] > arr[maxIdx]) maxIdx = L;
        if (R < heapSize && arr[R] > arr[maxIdx]) maxIdx = R;
        if (maxIdx != i) {
            swap(arr, i, maxIdx);
            siftDown(arr, maxIdx, heapSize);
        }
    }


    /**
    * @Author
    * @Description 二分查找
    * @Since 15:58 2019/4/11
    * @Param [arr, key]
    * @return int
    */
    static int b(int[] arr, int key){
        int L = 0, R = arr.length - 1;
        while(L <= R){
            int mid = L + (R - L) / 2;
            if(arr[mid] == key) return mid;
            if(arr[mid] > key)
                R = mid - 1;
            else
                L = mid + 1;
        }
        return -1;
    }

    
    /*-------------------------------------------------------------------------*/

    
    /**
    * @Author 宗坚
    * @Description 先给出一个swap函数，代表交换数组两个位置的值，很多排序用到这个函数:
    * @Since 15:48 2019/4/11
    * @Param [arr, a, b]
    * @return void
    */
    static void swap(int[] arr, int a, int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
}
