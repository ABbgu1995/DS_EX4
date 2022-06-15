public class LinkedList {
    protected Node head;
    protected Node tail;
    protected int size=0;
    public LinkedList(){
        head=null;
        tail=null;
    }
    public void add(String[] person){
        Node temp = new Node(person);
        if (size==0){
            head = temp;
            tail = temp;
        }
        else {
            tail.next=temp;
            tail = temp;
        }
        size++;

    }
    public int getSize(){
        if (head==null) {
            return 1;
        }
        return size;
    }
    public Node getHead(){
        return head;
    }
}
