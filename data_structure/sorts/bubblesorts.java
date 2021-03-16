import java.util.Arrays;

public class Sorts {
    public static void main(String[] args) {
        int[] a = {4,3,2,5,4,2,3,5,3,54};
        System.out.println(Arrays.toString(bubbleSort(a,a.length)));
    }
    public static int[] bubbleSort(int[]a,int n){
        if(n<=1) return a;
        for(int i =0;i<n;++i){
            boolean flag = false;
            for(int j = 0;j<n-1-i;++j){
                if(a[j] < a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag) break;
        }
        return a;
    }
    
    //插入排序
        public static int[] insertSort(int[]a,int n){
        if(n<=1) return a;
        for(int i = 1;i<n;++i){
            int value = a[i];
            int j = i-1;
            for(;j>=0;--j){
                if(a[j] > value){
                    a[j+1] = a[j];
                }
                else break;
            }
            a[j+1] = value;
        }
        return a;
    }
}
