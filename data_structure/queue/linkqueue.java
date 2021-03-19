public class myLinkQueue {
    private Node head=null;
    private Node tail=null;
    public void enqueue(String value){
        if(tail != null){
            tail.next = new Node(value,null);
            tail= tail.next;
        }
        else{
            Node newnode = new Node(value,null);
            head = newnode;
            tail = newnode;
        }
    }
    
    public String dequeue(){
        if(head == null) return null;
        String value = head.data;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return value;
    }
    
    public void printAll(){
        Node p = head;
        while(p !=null){
        System.out.print(p.data+ " ");
        p = p.next;}
        System.out.println();
    }
    
    
    public static class Node{
        private String data;
        private Node next;
        public Node(String data,Node next){
            this.data = data;
            this.next = next;
        }
        public String getData(){
            return data;
        }
        
    }
