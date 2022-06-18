/**
 * Class which defines the HashOpen table that operate the concert that contains people that enter the hall
 */
public class HashOpen {
    protected int[] hash_table;

    /**
     * Constructor of the class which initials the HashTable length (amount of cells)
     * @param m is the amount of cells within the hashtable
     */
    public HashOpen(int m)
    {
        this.hash_table=new int[m];

    }

    /**
     * private function that reverse a given id
     * @param id integer which represent an id
     * @return reverse order of the id
     */
    private int reverse(int id){
        int reversed=0;
        for(;id != 0; id /= 10) {
            int digit = id % 10;
            reversed = reversed * 10 + digit;
        }
        return reversed;
    }

    /**
     * hash function which calculate the relevant key for a cell using (id mod hash_table.length (which is m))
     * @param id id of a person
     * @return index of a mapped cell
     */
    private int h1(int id){
        return id % this.hash_table.length;
    }

    /**
     * hash function which calculate the relevant key for a cell using (reverse id mod hash_table.length (which is m))
     * @param id id of a person
     * @return index of a mapped cell
     */
    private int h2(int id){
        return reverse(id) % this.hash_table.length;
    }

    /**
     * insert new id to the hashtable based on hash function (h1,h2) and return the amount of steps till the id insert
     * to an cell within the array
     * @param id int which represent an id
     * @param hashFunc int which represent an hash function
     * @return int which represent the amount of steps (or unavailable_seats) till the id insert
     */
    public int insert(int id, int hashFunc) {
        int unavailable_seats=0;
        int counter=0;
        int location_by_function=0;
        if (hashFunc==1){
            location_by_function=h1(id);
        }
        else
            location_by_function=h2(id);

        if (this.hash_table[location_by_function]!=0){
            while(true) {
                if (location_by_function + counter == this.hash_table.length - 1) {
                    for (int i = location_by_function - counter; i >= 0; i--) {
                        if (this.hash_table[i] == 0) {
                            this.hash_table[i] = id;
                            return unavailable_seats;
                        }
                        unavailable_seats++;
                    }
                }
                if (location_by_function - counter == 0) {
                    for (int i = location_by_function + counter; i <= this.hash_table.length - 1; i++) {
                        if (this.hash_table[i] == 0) {
                            this.hash_table[i] = id;
                            return unavailable_seats;
                        }
                        unavailable_seats++;
                    }
                }
                counter++;
                // in case of h+1,h-1,h+2,h-2,h+3,h-3 and is the first cell
                unavailable_seats++; // visit the h+counter cell
                if (this.hash_table[location_by_function + counter] == 0) {
                    this.hash_table[location_by_function + counter] = id;
                    return unavailable_seats;
                }

                unavailable_seats++; // visit the h-counter cell
                if (this.hash_table[location_by_function - counter] == 0) {
                    this.hash_table[location_by_function - counter] = id;
                    return unavailable_seats;
                }
            }
        }
        else
            this.hash_table[location_by_function]=id;
        return unavailable_seats;
    }

    /**
     * calculate the total ids which entered the concert (which represent by the open hash table)
     * @return int which represent the amount of ids in the open hash table
     */
    public int getNumberElements(){
        int counter_elements=0;
        for (int i=0; i<=this.hash_table.length-1; i++){
            if(this.hash_table[i]!=0)
                counter_elements++;
        }
        return counter_elements;
    }

    /**
     * return the amount of exists cells (empty or non-empty) within the hashtable
     * @return int which represent the size of the hashtable
     */
    public int getSize(){
        return this.hash_table.length;
    }
}
