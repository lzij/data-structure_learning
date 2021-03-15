import java.util.Arrays;

public class permutation {
    public static void main(String[] args) {
        int[] a ={2,2,3};
        allPermutation(a,0,a.length-1);

    }
    public static void allPermutation(int[] a,int cursor,int k){
        if(cursor == k) System.out.println(Arrays.toString(a));
        for(int i = cursor;i<=k;i++){
            if(!judgecursor(a, cursor, i)){
                continue;
            }
            swap(a,cursor,i);
            allPermutation(a,cursor+1,k);
            swap(a,cursor,i);
        }
    }
    public static void swap(int[] a ,int cursor,int k){
        int temp = a[cursor];
        a[cursor] = a[k];
        a[k] = temp;
    }

    public static boolean judgecursor(int[] a,int cursor,int i){
        for(int n = cursor;n<i;n++){
            if(a[n] == a[i]){
                return false;
            }
        }
        return true;
    }
}
