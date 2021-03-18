import java.util.Arrays;

public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {3,2,4,5,3,7,5,4,7};
        bucketSort(arr,3);
        System.out.println(Arrays.toString(arr));
    }

    public static void bucketSort(int[] arr, int buckSize){
        if(arr.length<2)return ;
        //寻找数组最小值、最大值
        int minValue = arr[0];
        int maxValue =arr[1];
        for(int i = 0;i<arr.length;i++){
            if(arr[i]<minValue) minValue = arr[i];
            else if(arr[i]>maxValue) maxValue = arr[i];
        }

        //桶数量
        int bucketCount = (maxValue - minValue)/buckSize +1;
        int[][] buckets = new int[bucketCount][buckSize];
        //indexArr存储着每个桶中的元素个数
        int[] indexArr = new int[bucketCount];

        //将数组中的值分配到各个桶里
        for(int i =0;i<arr.length;i++){
            int bucketIndex = (arr[i] - minValue)/buckSize;
            if(indexArr[bucketIndex] == buckets[bucketIndex].length){
                ensureCapacity(buckets,bucketIndex);
            }
            //将arr[i]放到桶编号为bucketIndex的桶的第indexArr[bucketIndex]位置上
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }
        //对每个桶进行排序，使用快排法
        int k =0;//当一个桶排序后立刻将其写入原来的arr中，k充当arr的索引
        for(int i =0;i<buckets.length;i++){
            if(indexArr[i] ==0) continue;
            quickSort(buckets[i],0,indexArr[i]-1);
            for(int j =0;j<indexArr[i];j++){
                arr[k++] = buckets[i][j];
            }
        }
    }
    //快速排序递归函数
    private static void  quickSort(int[] arr, int p,int r){
        if(p>=r) return;
        int q = partition(arr,p,r);
        quickSort(arr,p,q-1);
        quickSort(arr,q+1,r);
    }

    //分区函数
    private static int partition(int[] a,int p, int r){
        int pivot = a[r];
        int i = p;
        for(int j = p;j<r;j++){
            if(a[j] <=pivot){
                if(i == j) ++i;
                else {
                    swap(a,i,j);
                    i++;
                }
            }
        }
        swap(a,i,r);
        return i;
    }

    private static void swap(int[] arr, int i ,int j){
        if(i ==j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //数组扩容方法
    private static void ensureCapacity(int[][] buckets,int bucketIndex){
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length*2];
        for(int j =0;j<tempArr.length;j++){
            newArr[j] = tempArr[j];
        }
        buckets[bucketIndex] = newArr;
    }
}
