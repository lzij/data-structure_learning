public class binarySearchTree {

    //定义一个树节点
    private Node tree;

    //二叉树查找函数
    public Node find(int data){
        Node p = tree;
        while(p!= null){
            if(data<p.data) p = p.left;
             else if (data>p.data) p = p.right;
             else return p;
        }
        return null;
    }

    //二叉树插入函数
    public void insert(int data ){
        if(tree == null){
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while(p!=null){
            if(data>p.data){
                if(p.right == null){
                    p.right = new Node(data);
                    return;
                }
                else p = p.right;
            }
            else {
                if(p.left == null){
                    p.left = new Node(data);
                    return;
                }
                else p = p.left;
            }
        }
    }

    //删除

    public void delete (int data){
        Node p = tree ; //p指向要删除的节点，初始化为根节点
        Node pp = null;//pp记录的是p的父节点
        while(p != null && p.data != data){
            pp = p;
            if(data>p.data) p =p.right;
            else p = p.left;
        }
        //对应p!=null
        if(p == null) return;

        //以下对应 p.data = data
        //要删除的节点有两个子节点
        if(p.left != null && p.right != null){
            Node minp = p.right;
            Node minpp = p;//minpp表示minp的父节点
            while(minp.left != null){
                minpp = minp;
                minp = minp.left;
            }
            p.data = minp.data;
            p=minp;
            pp=minpp;//至此，要删除的节点已经变成了没有子节点或者只有一个右节点的节点
        }

        //删除的节点是叶子节点或者仅有一个子节点
        Node child;//p的子节点
        if(p.left !=null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;
        
        if(pp.left == p) pp.left = child;
        else if (pp.right == p) pp.right = child;
        //否则说明p为根节点，pp自然为null
        else tree = child;
    }
    
    
    //查找树中最大的节点，一定在右子节点
    public Node findMax(){
        if(tree == null) return null;
        Node p = tree;
        while (p.right != null){
            p = p.right;
        }
        return p;
    }
    
    //查找树中最小的节点，一定在左子节点
    public Node findMin(){
        if(tree == null) return null;
        Node p = tree;
        while(p.left != null){
            p = p.left;
        }
        return p;
    }

    public static class Node{
        private int data;
        private Node left;
        private Node right;
        public Node(int data){
            this.data = data;
        }
    }


}
