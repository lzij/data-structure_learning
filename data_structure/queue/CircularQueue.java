public class CircularQueue {
    private String[] items;
    private int n;
    private int head;
    private int tail;
    
    public CircularQueue(int capacity){
        this.items = new String[capacity];
        this.n = capacity;
        this.head = 0;
        this.tail = 0;
    }
    
    public String dequeue(){
        if(head == tail) return null;
        String value = items[head];
        head = (head+1)%n;
        return value;
    }
    //队空的条件  head == tail
    //队满的条件  (tail+1)%n == head  其中n是数组长度
    //入队，tail++时，(tail+1)%n
    //出队，head++时，(head+1)%n
    
    public boolean enqueue(String value){
        if((tail+1)%n == head) return false;
        items[tail] = value;
        tail =(tail+1)%n;
        return true;
        }
    public void printAll(){ 
        for(int i = head;i<tail;++i){
            System.out.println(items[i]+" ");
        }
        System.out.println();
    }
}
