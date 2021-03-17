import java.util.Arrays;

public class quickSort {
    public static void main(String[] args) {
        int[] a = {3,5,3,5,3,5,6,7};
        quickSort(a,a.length);
        System.out.println(Arrays.toString(a));
    }
    public static void quickSort(int[] a,int n){
        quickSortInternally(a,0,n-1);
    }
    private static void quickSortInternally(int[] a,int p ,int r){
        if(p>=r) return;
        int q = partition(a,p,r);
        quickSortInternally(a,p,q-1);
        quickSortInternally(a,q+1,r);
    }
    private static int partition(int[] a,int p,int r){
        int pivot = a[r];
        int i=p;
        for(int j=p;j<r;++j){
            if(a[j] <pivot) {
                if(i ==j){
                    ++i;
                }else {
                    int temp = a[i];
                    a[i++] = a[j];
                    a[j] = temp;
                }
            }
        }

        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;

        return i;
    }
}
