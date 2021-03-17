import java.util.Arrays;

public class mergeSort {

    public static void main(String[] args) {
        int[] a = {3,4,2,3,4};
        mergeSort(a,a.length);
        System.out.println(Arrays.toString(a));
    }
    private static void mergeSort(int[]a,int n){
        mergeSortInternally(a,0,n-1);
    }
    private static void mergeSortInternally(int[]a,int p,int r){
        if(p>=r) return;
        int q = p +(r-p)/2;
        mergeSortInternally(a,p,q);
        mergeSortInternally(a,q+1,r);
        merge(a,p,q,r);
    }
    private static void merge(int[]a,int p,int q,int r){
        int i = p;
        int j = q+1;
        int k = 0;
        int[] temp = new int[r-p+1];
        while(i<=q&&j<=r){
            if(a[i]<=a[j]){
                temp[k++] = a[i++];
            }else temp[k++] = a[j++];
        }

        int start = i;
        int end = q;
        if(j<=r){
            start = j;
            end = r;
        }

        while(start <= end){
            temp[k++] = a[start++];
        }

        for(i = 0;i<=r-p;++i){
            a[p+i] = temp[i];
        }
    }
}
