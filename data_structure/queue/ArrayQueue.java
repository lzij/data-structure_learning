public class myArrayQueue {
  //数组items  数组大小 n
    private String[] items;
    private int n;
    private int head;
    private int tail;

    public myArrayQueue(int capacity){
        this.items =new String[capacity];
        this.n = capacity;
        this.head = 0;
        this.tail = 0;
    }

    public boolean enqueue(String value){
        if(tail == n){
            if(head == 0) return false;
            else {
              //充分利用数组空间，当tail到达尾部，head并没有在开头时，可以待tail到达尾部后，一次性挪到头
                for(int i = head;i<=tail;i++){
                    items[i-head] = items[i];
                }
                tail = tail - head;
                head = 0;
            }
        }
        items[tail] =value;
        ++tail;
        return true;
    }

    public String dequeue(){
        if(head == tail) return null;
        String value = items[head];
        ++head;
        return value;
    }
}
