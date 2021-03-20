public class bsearch {

    public static void main(String[] args) {
        int[] a = {3,4,5,6,7};
        System.out.println(bsearchtest(a,7));
        System.out.println(binarySearch(a,4));
    }
    public static int bsearchtest(int[] a ,int val){
        int start = 0;
        int end = a.length-1;

        while(start<=end){
            int mid = start +((end -start)>>1);
            if(a[mid] == val) return mid;
            else if(a[mid] > val) end = mid-1;
            else if(a[mid]< val) start = mid+1;
        }
        return -1;
    }
    
    
    //递归实现
    public static int binarySearch(int[] a,int val){
        return binarySearchInternally(a,val,0,a.length-1);
    }
    public static int binarySearchInternally(int[] a,int val,int start,int end){
        if(start>end) return -1;
        int mid = start +((end -start)>>1);
        if(a[mid] == val) return mid;
        else if(a[mid] > val) return binarySearchInternally(a,val,start,mid-1);
        else return binarySearchInternally(a,val,mid +1,end);
    }
}
