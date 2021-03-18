import java.util.Arrays;

public class countingSort {

    public static void main(String[] args) {
        int[] a = {2,2,4,6,3,7,7,66};
        mycountingSort(a,a.length);
        System.out.println(Arrays.toString(a));
    }

    private static void mycountingSort(int[] a,int n){
        if(n<=1) return;
        //寻找数组a的最大值
        int max = a[0];
        for(int i =1;i<n;i++){
            if(a[i]>max) max = a[i];
        }

        //创建桶数组
        int[] c = new int[max+1];
        //初始化桶数组
        for(int i= 0;i<=max;i++){
            c[i] = 0;
        }
        //统计数组a中某元素出现次数，放入桶数组中
        for(int i = n-1;i>= 0;i--){
            c[a[i]]++;
        }
        //对桶数组顺序求和
        for(int i =1;i<=max;i++){
            c[i] = c[i] +c[i-1];
        }
        //创建数组r存储排序结果
        int[] r = new int[n];
        //找到数组a中的元素对应在数组c下标的元素，代表这个数在排序中的位置，利用这个位置，将其放入r中，再减一
        for(int i = n-1;i>=0;i--){
            r[c[a[i]]-1] = a[i];
            c[a[i]]--;
        }
        //将排好序的数组r拷贝给数组a
        for(int i =0;i<n;i++){
            a[i] = r[i];
        }
    }
}
