/**
 * Class which define Linked list with the operates of add, getSize, getHead
 */
public class LinkedList {
    protected Node head;
    protected Node tail;
    protected int size=0;

    /**
     * Constructor for the class which sets the pointers to null
     */
    public LinkedList(){
        head=null;
        tail=null;
    }

    /**
     * Return nothing and add new node to the end of the current linked list
     * @param person contain id, firstname, lastname and card type
     */
    public void add(String[] person){
        Node temp = new Node(person);
        // size 0 means this is the first node enter the list and if so, the head and tail points to it.
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

    /**
     * return the size (amount of nodes) within the linked list
     * @return int which represent the size of linked list (amount of nodes)
     */
    public int getSize(){
        return size;
    }

    /**
     * return the pointer to the head of the current linked list
     * @return the pointer(address) for the head of the linked list
     */
    public Node getHead(){
        return head;
    }
}
