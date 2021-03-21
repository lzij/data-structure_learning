public class bsearch {

    public static void main(String[] args) {
        int[] a = {3,3,4,4,5,6,6,7};
        System.out.println(bsearchfirst(a,4));
        System.out.println(bsearchfirsteasy(a,4));
        System.out.println(bsearchlast(a,4));
        System.out.println(bsearchlasteasy(a,4));
        //第一个大于等于目标值的元素
        System.out.println(bsearchfirstlarge(a,4));
        //最后一个小于等于目标值的元素
        System.out.println(bsearchlastsmall(a,4));
    }
  //在有重复元素的数组中，查找第一个目标值的位置
    public static int bsearchfirst(int[] a,int value){
        int low = 0;
        int high = a.length-1;
        while(low<=high){
            int mid = low+((high -low)>>1);
            if(a[mid] >= value) high = mid-1;
            else low = mid +1;
        }
        if(low<a.length&&a[low] ==value) return low;
        return -1;
    }
  //  //在有重复元素的数组中，查找第一个目标值的位置，更容易理解的思路
    public static int bsearchfirsteasy(int[] a,int value){
        int low = 0;
        int high = a.length-1;
        while(low<=high){
            int mid = low+((high - low)>>1);
            if(a[mid]>value) high = mid-1;
            else if(a[mid]<value) low = mid+1;
            else {
                if(mid==0||a[mid-1] != value) return mid;
                else high =mid -1;
            }
        }
        return -1;
    }
    //在有重复元素的数组中，查找最后一个目标值的位置
    public static int bsearchlast(int[] a,int value){
        int low = 0;
        int high = a.length-1;
        while(low<=high) {
            int mid = low +((high-low)>>1);
            if(a[mid]>value) high = mid -1;
            if(a[mid]<=value) low = mid +1;
        }
        if(a[high] == value&& high>=0) return high;
        else return -1;
    }
      //在有重复元素的数组中，查找最后一个目标值的位置，容易理解的思路
    public static int bsearchlasteasy(int[] a,int value){
        int low = 0;
        int high =a.length-1;
        while(low<=high){
            int mid = low+((high -low)>>2);
            if(a[mid]>value) high =mid -1;
            else if(a[mid]<value) low = mid+1;
            else {
                if(mid == a.length-1||a[mid+1]!=value) return mid;
                else low = mid+1;
            }
        }
        return -1;
    }
  //查找第一个大于目标值的元素位置
    public static int bsearchfirstlarge(int[] a,int value){
        int low =0;
        int high = a.length-1;
        while(low<=high){
            int mid = low+((high -low)>>1);
            if(a[mid] <value) low = mid +1;
            else {
                if(mid == 0||a[mid-1] <value) return mid;
                else high = mid -1;
            }
        }
        return -1;
    }
  //查找最后一个小于目标值的元素位置
    public static int bsearchlastsmall(int[]a,int value){
        int low = 0;
        int high = a.length-1;
        while(low<=high){
            int mid = low+((high-low)>>1);
            if(a[mid]>value) high = mid -1;
            else{
                if(mid == (a.length-1)||a[mid+1]>value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
}
