import java.util.HashMap;

public class LinkHashMap<k,v> {
    //默认链表容量
    private final static Integer DEFAULT_CAPACITY = 10;
    //双向链表的头结点和尾结点
    private DNode<k,v> headNode;
    private DNode<k,v> tailNode;
    //链表的实际存储长度
    private Integer length;
    //链表的容量
    private Integer capacity;
    //建立hashmap，table存储的是散列表里的头结点
    private HashMap<k,DNode<k,v>> table;
    //定义双向链表
    static class DNode<k,v>{
        private k key;
        private v value;
        private DNode<k,v> prev;
        private DNode<k,v> next;
        DNode(){}
        DNode(k key,v value) {
            this.key = key;
            this.value = value;
        }
    }
    public LinkHashMap(int capacity){
        this.length = 0;
        this.capacity=capacity;
        this.headNode= new DNode<>();
        this.tailNode = new DNode<>();
        headNode.next=tailNode;
        tailNode.prev = headNode;
        table = new HashMap<>();
    }
    public LinkHashMap(){
        this(DEFAULT_CAPACITY);
    }
    //向linkhashmap中添加元素
    public void add(k key,v value){
        //通过散列表结构，判断key，迅速找到这个结点
        DNode<k,v> node = table.get(key);
        //如果这个结点是空的
        if(node == null) {
            //先新建一个结点，因为在散列表中，key是键值，value就是一个结点
            DNode<k,v> newNode = new DNode<>(key,value);
            //将这个结点放到key的位置
            table.put(key,newNode);
            //通过双向链表结构，将新节点放到头部
            addNode(newNode);
            //每次放入一个新节点，长度加1，若大于链表容量，将尾部结点删掉
            if(++length>capacity){
                DNode<k,v> tail = popTail();
                table.remove(tail.key);
                length--;
            }
        }
        else{
            //如果这个节点不是空的，就用新value覆盖掉旧value
            node.value = value;
            //将这个已有的节点提到头部
            moveToHead(node);
        }
    }

    //将新节点增加到头部
    private void addNode(DNode<k,v> newNode){
        //注意这是双向链表，所以节点之间的关心要考虑 next和prev双层
        newNode.next = headNode.next;
        newNode.prev = headNode;

        headNode.next.prev = newNode;
        headNode.next = newNode;
    }

    //弹出尾部数据结点
    private DNode<k,v> popTail(){
        DNode<k,v> node = tailNode.prev;
        removeNode(node);
        return node;
    }

    //移除结点
    private void removeNode(DNode<k,v> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //将结点移动到头部
    private void moveToHead(DNode<k,v> node){
        removeNode(node);
        addNode(node);
    }
    //获取结点数据
    public v get(k key){
        DNode<k,v> node = table.get(key);
        if(node == null){
            return null;
        }
        //因为是采用按照访问顺序排列的，所以在访问到它之后需要将其排到头部
        moveToHead(node);
        return node.value;
    }
    //移除结点数据
    //这个在散列表结构和双向链表结构中 均删除掉
    public void remove(k key){
        DNode<k,v> node = table.get(key);
        if(node == null){
            return;
        }
        removeNode(node);
        length--;
        table.remove(node.key);
    }

    private void printAll(){
        DNode<k,v> node = headNode.next;
        while(node.next!=null){
            System.out.println(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
}
