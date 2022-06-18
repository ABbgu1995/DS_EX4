/**
 * Class which define Node with the operates of getNext and getData
 */
public class Node {
    String[] person = new String[4];
    protected Node next;

    /**
     * Constructor that initials the String array which represent the attributes of a person
     * @param person String array which contains id, firstname, lastname and card type of a person
     */
    public Node(String[] person){
        this.person[0]=person[0];
        this.person[1]=person[1];
        this.person[2]=person[2];
        this.person[3]=person[3];
    }

    /**
     * return the pointer of the next node
     * @return Node object which is a pointer to the next node
     */
    public Node getNext(){
        return this.next;
    }

    /**
     * return the information about the person
     * @return String array which contains the id, firstname, lastname and card type of a person
     */
    public String[] getData(){
        return this.person;
    }
}
