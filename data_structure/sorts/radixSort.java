import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] a = {2,2,4,6,3,7,7,66};
        radisSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void  radisSort(int[] arr){
        int max= arr[0];
        for(int i =0;i<arr.length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }

        //从 个位  开始，对数组arr进行排序
        for(int exp = 1;max/exp>0;exp*=10){
            countingSort(arr,exp);
        }
    }
    public static  void  countingSort(int[] arr,int exp){
        if(arr.length<=1){
            return;
        }
        //创建桶数组，其下标是arr[]中元素的个位数，元素为对应下标数值出现的次数
        int[] c = new int[10];
        for(int i = 0;i<arr.length;i++){
            //（arr[i]/exp）%10   结果就是相应位的数字，%取得的都是这个数的个位数值，比如123经过上述公式结果是3
            //exp= 10的时候，123就变成了12.3，会自动转成int型 12，再%结果就是2，是十位的数值，以此类推
            c[(arr[i]/exp)%10]++;
        }
        //对c[]顺序求和，这样c[i]存储的就是小于等于i的元素的个数
        for(int i =1;i<c.length;i++){
            c[i] += c[i-1];
        }
        //临时数组r,存储排序之后的结果
        int[] r = new int[arr.length];
        for(int i = arr.length-1;i>=0;i--){
            r[c[(arr[i]/exp)%10]-1] =arr[i];
            c[(arr[i]/exp)%10]--;
        }
        for(int i =0;i<arr.length;i++){
            arr[i] =r[i];
        }
    }
}
