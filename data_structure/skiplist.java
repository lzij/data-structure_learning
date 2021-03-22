import java.util.Random;

public class skipList {
    //MAX_LEVEL 跳表层数共16层
    private static  final int MAX_LEVEL = 16;
    //当前跳表的有效层
    private  int levelcount = 1;
    //跳表的头部节点
    private Node head = new Node(MAX_LEVEL);
    //随机数发生器，插入节点时生成level
    private Random r = new Random();
    //在跳表中寻找value大小的元素
    public Node find(int value){
        //将头节点赋值给p
        Node p = head;
        //这个for循环用来从索引层最顶层遍历到最下面一层
        for(int i = levelcount -1;i>= 0;--i){
            //对于某一索引层，用while循环的方式找到一个元素p，这个元素的下一个元素不是null，同时值大于目标值
            while(p.forwards[i] !=null&&p.forwards[i].data<value){
                p = p.forwards[i];
            }
        }
        //经过上述while循环，i=0时，会到索引层的最下面一层，也就是原链表层，去判断在这一层中，元素p的下一个元素值是否为目标值
        if(p.forwards[0]!= null && p.forwards[0].data == value){
            return p.forwards[0];
        }
        else return null;
    }
    //随机数发生器，用来生成level，insert时决定索引层高度
    public int randomLevel(){
        int level = 1;
        //一共有MAX_LEVEL次，每一个都会生成一个随机数，如果时偶数，level加1，这样避免伪随机
        for(int i =1;i<MAX_LEVEL;++i){
            if(r.nextInt()%2 == 1) ++level;
        }
        return level;
    }
    /*在跳表中插入一个值，同时需要输入索引层高度
    *新建一个Node对象，参数为这个Node出现在几个索引层中，如level就是出现在0层到level-1层
    * 然后给这个Node赋值value
    * 新建一个Node数组，大小为level，将来存储要插入新Node位置之前的元素，找到前一个元素，就好插入了
    *因为这个Node的索引层是level，所以这个新建的Node数组大小为level就够用了
    * 为了出现异常，先用for循环对其初始化为head
    * 将head赋值给p，在每一个索引层中找元素p，使得p的下一个元素大于目标值，将p存储到update[]数组中，将来在p后边插入
    * 用for循环对每一个索引层实施插入操作；将update[].forwards[]给到newNode.forwards[],再将newNode给到update[].forwards[]
    *
    */
    public void insert(int value,int level){
        if(level == 0){
            level = randomLevel();
        }
        Node newnode = new Node(level);
        newnode.data=value;
        newnode.maxlevel = level;
        Node[] update= new Node[level];
        for(int i =0;i<level;++i){
            update[i] = head;
        }
        Node p = head;
        for(int i = level-1;i>= 0;--i){
            while(p.forwards[i]!= null&& p.forwards[i].data <value){
                p=p.forwards[i];
            }
            update[i] = p;
        }
        for(int i =0;i<level;++i){
            newnode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newnode;
        }
    }
    /*删除跳表中的某一个节点
    *新建一个Node数组，用来存放，想要删除元素的前一个元素，找到前一个元素，就方便后续删除了
    * 数组大小为索引层实际高度levelcount，因为一共就这么多索引层，只需要在每一个索引层中删掉目标值
    * 将头节点赋值给p，利用for循环对每一个索引层进行遍历
    * 利用while循环语句，对指定索引层进行遍历，直到找到元素p的下一个元素大于目标值，将p值存储到update[]中
    * 利用if判断在底层链表中，找到的p元素的下一个元素是不是要删除的目标值
    * 如果是，就用for循环遍历所有索引层，在循环到每一层时，再用if判断每一个层中，p元素的下一个元素是目标值，如果是，将p.forwards[].forwards[]赋值给p.forwards[]
    *
     */
    public void delete(int value){
        Node[] update = new Node[levelcount];
        Node p = head;
        for(int i = levelcount -1;i>= 0;--i){
            while(p.forwards[i] !=null && p.forwards[i].data <value){
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if(p.forwards[0] != null&&p.forwards[0].data == value){
            for(int i = levelcount-1;i>=0;--i){
                if(p.forwards[i] !=null&&p.forwards[i].data == value){
                    update[i].forwards[i]=p.forwards[i].forwards[i];
                }
            }
        }
    }
    public void printAll(){
        Node p = head;
        while(p.forwards[0] !=null){
            System.out.println(p.forwards[0] + " ");
            p= p.forwards[0];
        }
    }

    public class Node{
        //data存储当前节点的数值大小
        private int data = -1;
        //forwards是一个元素类型是Node的数组，其存储着当前元素在各个索引层的下一个元素，数组下标为索引层数
        private Node forwards[];
        private  int maxlevel = 0;
        public Node(int level){
            forwards = new Node[level];
        }
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{data:");
            builder.append(data);
            builder.append(";levels: ");
            builder.append(maxlevel);
            builder.append("}");
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        skipList list = new skipList();
        list.insert(1,3);
        list.insert(2, 3);
        list.insert(3, 2);
        list.insert(4, 4);
        list.insert(5, 10);
        list.insert(6, 4);
        list.insert(8, 5);
        list.insert(7, 4);
        System.out.println();
        list.printAll();
    }
}
