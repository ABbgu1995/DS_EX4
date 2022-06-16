/**
 * Class which defines the HashClose table that operate as the data structure to keep the registered people information
 */
public class HashClosed {
    // HashTable for register people (phase1)
    protected LinkedList[] hash_table;
    protected int total_nodes;

    /**
     * Constructor of the class which defines the size of the hashtable by divided m(which is N) with 3.
     * @param m amount of people that registered
     */
    public HashClosed(int m) {
        int table_size=m/3;
        this.hash_table=new LinkedList[table_size];
        // initials the hashtable with empty linked list objects
        for (int i=0; i<table_size; i++){
            this.hash_table[i]= new LinkedList();
        }
    }


    /**
     * hash function which calculate the relevant key for a cell using (id mod hash_table.length (which is N/3=m))
     * @param id id of a person
     * @return index of a mapped cell
     */
    private int h1(int id){
        return id % this.hash_table.length;
    }

    /**
     * insert a new person to the end of the linked list
     * @param person array of string which contains id, firstname, lastname, cardtype
     */
    public void insert(String[] person) {
        this.hash_table[h1(Integer.parseInt(person[0]))].add(person);
        total_nodes++;
    }

    /**
     * count the amount of nodes exists in each linked list within the list
     * @return array of size of each linked list
     */
    public int[] getNodesSize() {
        int[] node_size_array=new int[this.hash_table.length];
        for (int i=0; i<=this.hash_table.length-1; i++){
            node_size_array[i]=this.hash_table[i].getSize();
        }
        return node_size_array;
    }

    /**
     * search a given person in the hash table
     * @param person array that represent a person
     * @return an array in the following format {[0 if not exists and 1 if exists, number of steps to find it]}
     */
    public int[] search(String[] person) {
        int steps_counter=1;
        int key = h1(Integer.parseInt(person[0]));
        Node link_list_pointer = this.hash_table[key].getHead();
            while(link_list_pointer!=null){
                if (link_list_pointer.getData()[0].equals(person[0])) {
                    return new int[]{1, steps_counter};
                }
                steps_counter++;
                link_list_pointer=link_list_pointer.getNext();
            }
        return new int[]{0, steps_counter};
        }

    /**
     * return the size of the hash table
      * @return length of the hashtable which means the amount of cells
     */
    public int getSize() {
        return this.hash_table.length;
    }
}


