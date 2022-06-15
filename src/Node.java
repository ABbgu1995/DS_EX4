public class Node {
    String[] person = new String[4];
    protected Node next;
    public Node(String[] person){
        this.person[0]=person[0];
        this.person[1]=person[1];
        this.person[2]=person[2];
        this.person[3]=person[3];
    }
    public Node getNext(){
        return this.next;
    }
    public String[] getData(){
        return this.person;
    }
}
