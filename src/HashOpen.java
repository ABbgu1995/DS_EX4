public class HashOpen {
    protected int[] hash_table;
    public HashOpen(int m)
    {
        this.hash_table=new int[m];

    }
    private int reverse(int id){
        int reversed=0;
        for(;id != 0; id /= 10) {
            int digit = id % 10;
            reversed = reversed * 10 + digit;
        }
        return reversed;
    }
    private int h1(int k){
        return k % this.hash_table.length;
    }
    private int h2(int k){
        return reverse(k) % this.hash_table.length;
    }
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
            while(true){
                if(location_by_function+counter==this.hash_table.length-1){
                    for(int i=location_by_function-counter; i>=0; i--){
                        if(this.hash_table[i]==0){
                            this.hash_table[i]=id;
                            return unavailable_seats;
                        }
                        unavailable_seats++;
                    }
                }
                if (location_by_function-counter==0){
                    for(int i=location_by_function+counter; i<=this.hash_table.length-1;i++){
                        if(this.hash_table[i]==0){
                            this.hash_table[i]=id;
                            return unavailable_seats;
                        }
                        unavailable_seats++;
                    }
                }
                counter++;
                unavailable_seats++;
                if(this.hash_table[location_by_function+counter]==0){
                    this.hash_table[location_by_function+counter]=id;
                    return unavailable_seats;
                }
                unavailable_seats++;
                if(this.hash_table[location_by_function-counter]==0){
                    this.hash_table[location_by_function-counter]=id;
                    return unavailable_seats;
                }
            }
        }
        else
            this.hash_table[location_by_function]=id;
        return unavailable_seats;
    }
    public int getNumberElements(){
        int counter_elements=0;
        for (int i=0; i<=this.hash_table.length-1; i++){
            if(this.hash_table[i]!=0)
                counter_elements++;
        }
        return counter_elements;
    }

    public int getSize(){
        return this.hash_table.length;
    }
}
