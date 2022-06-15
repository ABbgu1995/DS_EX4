public class HashClosed {
    // HashTable for register people (phase1)
    protected LinkedList[] hash_table;
    protected int total_nodes;
    public HashClosed(int m) {
        int table_size=m/3;
        this.hash_table=new LinkedList[table_size];
        for (int i=0; i<table_size; i++){
            this.hash_table[i]= new LinkedList();
        }
    }

    public void insert(String[] person) {
        int key_index = Integer.parseInt(person[0]) % this.hash_table.length;
        this.hash_table[key_index].add(person);
        total_nodes++;
    }

    public int[] getNodesSize() {
        int[] node_size_array=new int[this.hash_table.length];
        for (int i=0; i<=this.hash_table.length-1; i++){
            node_size_array[i]=this.hash_table[i].getSize();
        }
        return node_size_array;
    }

    public int[] search(String[] person) {
        int steps_counter=1;
        int key = Integer.parseInt(person[0]) % this.hash_table.length;
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

    // Return array: [found(1 if found, 0 if not), number of steps] , as mentioned in the assignment

    public int getSize() {
        return this.hash_table.length;
    }// returns table size
}


