public class HashTable<k,v> {
    private static final int DEFAULT_INITAL_CAPACITY = 8;
    private static final float LOAD_FACTOR = 0.75F;
    private  Entry<k,v>[] table;
    //实际元素数量
    private int size = 0;
    //散列表索引数量,就是槽的数量，因为采用链表法解决冲突问题，所以槽里存储的也是链表的头结点
    private int use = 0;
    public HashTable(){
        table =(Entry<k, v>[]) new Entry[DEFAULT_INITAL_CAPACITY];
    }

    public void put(k key, v value){
        int index = hash(key);
        if(table[index] == null) {
            //如果槽里是空，就先建立一个哨兵节点
            table[index] = new Entry<>(null,null,null);
        }
        //将对应槽的头结点赋值给tmp，后续用tmp当作链表的头结点，去遍历链表
        Entry<k,v> tmp = table[index];

        if(tmp.next == null){
            //这个槽位对应的链表中没有元素，所以第一个就可以新建一个结点
            tmp.next = new Entry<>(key,value,null);
            size++;
            //因为新建了一个哈希值对应的槽位，所以use++
            use++;
            //当新建槽位后，槽位数量超过数组长度*重载因子，就需要扩容
            if(use>= table.length*LOAD_FACTOR){
                resize();
            }
        }
        //解决散列冲突，使用链表法
        else{
            do{
                //如果槽位对应的链表中存在元素，则通过节点的next指针遍历
                tmp = tmp.next;
                //如果遍历到某个元素等于目标值，也就是链表中存在同样key的元素，就更新它的value值
                if(tmp.key == key){
                    tmp.value =value;
                    return;
                }
            }while(tmp.next != null);//直到遍历完整个链表，结束
            //将槽位对应链表的第一个元素，先赋给一个新职，让链表头指针指向新建的目标值节点，目标值节点的next指向原来的第一个元素，这样就插入了一个新值
            Entry<k,v> temp = table[index].next;
            table[index].next = new Entry<>(key,value,temp);
            size++;
        }
    }

    private int hash(Object key){
        int h;
        return (key == null) ? 0:((h= key.hashCode())^(h>>>16)) % table.length;
    }
    //给数组扩容
    private void resize(){
        //将现有数组赋值给oldTable这个变量
        Entry<k,v>[] oldTable = table;
        //新建一个长度为原来数组两倍的新数组
        table =(Entry<k, v>[]) new Entry[table.length*2];
        //新数组槽位为0
        use = 0;
        //遍历旧数组
        for(int i = 0;i<oldTable.length;++i){
            //如果旧数组槽位为空，或者槽位对应的链表中没有值
            if(oldTable[i] == null || oldTable[i].next == null){
                continue;
            }
            //如果槽位不是空，将槽位中的头结点赋值给e
            Entry<k,v> e = oldTable[i];
            //这个链表中存在元素
            while(e.next != null){
                //不断遍历这个链表
                e = e.next;
                //求得链表中对应元素的哈希值，这样就知道是哪个槽位了
                int index = hash(e.key);
                //如果新数组中，这个槽位是空
                if(table[index] == null){
                    //新数组槽位加1
                    use++;
                    //槽位中存储的头结点初始化
                    table[index] = new Entry<>(null,null,null);
                }
                //如果槽位对应的列表不是空，就在第一个节点位置加上旧数组中对应链表的元素的key value，再指向新数组对应槽位的链表的元素
                table[index].next = new Entry<>(e.key,e.value,table[index].next);
            }
        }
    }

    public void remove( k key){
        int index = hash(key);
        Entry e = table[index];
        //如果对应槽位是空，就代表没有链表，何谈链表中存在元素；或者链表中没有元素
        if(e == null || e.next == null){
            return;
        }

        Entry pre;
        Entry<k,v> headNode = table[index];
        do{
            pre = e;
            //遍历该链表
            e = e.next;
            if( key == e.key){
                //在链表中删除目标值对应的元素
                pre.next = e.next;
                size--;
                //如果这个链表已经没有元素了，也就意味着头结点的next指针指向null，这个槽位就可以减掉了
                if(headNode.next == null) use --;
                return;
            }
        }while ( e.next != null);
    }

    public v get(k key){
        int index = hash(key);
        Entry<k,v> e =table[index];
        if(e == null || e.next == null){
            return null;
        }
        while (e.next != null){
            e = e.next;
            if(key == e.key){
                return e.value;
            }
        }
        return null;
    }

    static class  Entry<k,v>{
        k key;
        v value;
        Entry<k,v> next;
        Entry(k key,v value, Entry<k,v> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
