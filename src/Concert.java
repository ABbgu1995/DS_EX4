import java.io.*;
/**
 * The main class which aggregate and process the whole process of managing the concert
 * The class uses both hash table format and reads persons information from 2 input files
 */
public class Concert {

    /**
     * the function reads crowd information from a file and return a linked link with nodes which represent the person
     * @param FileName String which represent the file name that contains the crowd information
     * @return Linked list of nodes with person's information
     */
    public static LinkedList getFromFile(String FileName) { //Return LinkedList with Crowd or people in reception
        LinkedList linked_list = new LinkedList();
        try {
            FileReader reader = new FileReader(FileName);
            BufferedReader BR = new BufferedReader(reader);
            String line ;
            while ((line = BR.readLine()) != null) {
                String[] arr;
                arr = line.split(",");
                linked_list.add(arr);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return linked_list;
    }

    /**
     * the function reads crowd information from a file and return hash table which represent people that register
     * to the concert
     * @param file_path String which represent the file name that contains the crowd information
     * @return hash table of HashClose object with person's information that register the concert
     */
    public static HashClosed registerCrowd(String file_path) { // Return HashClosed with all registered people
        LinkedList linked_list = getFromFile(file_path);
        HashClosed hash_table = new HashClosed(linked_list.getSize());
        Node runner = linked_list.getHead();
        while (runner != null) {
            hash_table.insert(runner.getData());
            runner = runner.getNext();
        }
        return hash_table;
    }

    /**
     * the function calculate the average steps to find each person from an input file within the hash table
     * of registered people
     * @param file_path String which represent the file name that contains the crowd information
     * @param registered HashClosed which represent the information about the people that registered the concert
     * @return number of total steps to find each person divide by amount of person's within the file the function
     * receive which represent the person that are in the reception
     */
    public static int reception_AverageSteps(String file_path, HashClosed registered) {
        LinkedList linked_list_reception = getFromFile(file_path);
        int sum_of_steps=0;
        int[] search_result;
        Node runner = linked_list_reception.getHead();
        while (runner!=null){
            search_result = registered.search(runner.getData());
            // Count also people that not exists in the hashtable
            sum_of_steps=sum_of_steps+search_result[1];
            runner=runner.getNext();
        }
        return sum_of_steps/ linked_list_reception.getSize();
    }

    /**
     * supporting private function that sorting the ids which entered the hall
     * @param arr_to_sort array of ids(int)
     * @return sorted array from low to high
     */
    private static int [] bubble_sort(int[] arr_to_sort) {
        int n = arr_to_sort.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr_to_sort[j] > arr_to_sort[j + 1]) {
                    int temp = arr_to_sort[j];
                    arr_to_sort[j] = arr_to_sort[j + 1];
                    arr_to_sort[j + 1] = temp;
                }
        return arr_to_sort;
    }

    /**
     * the function return a list of person which enter the concert from the reception list. the function first allow
     * to the registered person's to enter and if there more tickets left the function insert id's based on ticket
     * priority as following: VIP, GOLDEN_RING, INNER_RING, OUTER_RING. As the insert completed the function return
     * a sorted array of id's
     * @param file_path String which represent the file name that contains the crowd information
     * @param registered HashClosed which represent crowd that registered the concert
     * @return a sorted array of int that contains id's
     */
    public static int[] reception(String file_path, HashClosed registered) { // Return int[] with sortedIds after reception
        int index=0;
        int num_of_tickets_available = 0;
        int [] ids_allow_enter = new int[registered.total_nodes];
        LinkedList linked_list_reception = getFromFile(file_path);
        Node runner = linked_list_reception.getHead();
        while(runner!=null){
            int[] temp_arr = registered.search(runner.getData());
            if(temp_arr[0]==1){
                ids_allow_enter[index]=Integer.parseInt(runner.getData()[0]);
                // the function change the card type to zero in order to prevent duplicate checks for registered person
                // that already enter the concert. by changing the card type to zero the function ensure that next tests
                // that look for card type priority are going to fail due to the card value of 0 so those already entered
                // person won't check.
                runner.getData()[3]="0";
                index++;
            }
            runner=runner.getNext();
        }

        int num_of_waiters=linked_list_reception.getSize() - index;
        num_of_tickets_available=ids_allow_enter.length-index;
        runner = linked_list_reception.getHead();
        while (num_of_tickets_available!=0 && num_of_waiters>0){
            // until all the tickets sold or num of waiters in the reception are zero, the function will look for
            // more person to insert the concert based on the ticket priority. first the function look for person with
            // VIP and go over all the person within in the reception list and then go over again and look for person
            // with GOLDEN_RING and same for INNER_RING and OUTER_RING.
            while(runner!=null && num_of_tickets_available!=0){
                if (runner.getData()[3].equals("VIP")){
                    ids_allow_enter[index]=Integer.parseInt(runner.getData()[0]);
                    index++;
                    num_of_waiters--;
                    num_of_tickets_available--;
                    runner=runner.getNext();
                    }
                else
                    runner = runner.getNext();
                }
            runner = linked_list_reception.getHead();
            while(runner!=null && num_of_tickets_available!=0) {
                if (runner.getData()[3].equals("GOLDEN_RING")) {
                    ids_allow_enter[index] = Integer.parseInt(runner.getData()[0]);
                    index++;
                    num_of_waiters--;
                    num_of_tickets_available--;
                    runner = runner.getNext();
                }
                else
                    runner = runner.getNext();
            }
            runner = linked_list_reception.getHead();
            while(runner!=null && num_of_tickets_available!=0) {
                if (runner.getData()[3].equals("INNER_RING")) {
                    ids_allow_enter[index] = Integer.parseInt(runner.getData()[0]);
                    index++;
                    num_of_waiters--;
                    num_of_tickets_available--;
                    runner = runner.getNext();
                }
                else
                    runner = runner.getNext();
            }
            runner = linked_list_reception.getHead();
            while(runner!=null && num_of_tickets_available!=0) {
                if (runner.getData()[3].equals("OUTER_RING")) {
                    ids_allow_enter[index] = Integer.parseInt(runner.getData()[0]);
                    index++;
                    num_of_waiters--;
                    num_of_tickets_available--;
                    runner = runner.getNext();
                }
                else
                    runner = runner.getNext();
            }
        }
        // In case of m people registered but n people arrived and m>n
        // The size of ids_allow_enter is m, and that case if we will only return ids_allow_enter
        // we will return array which contains m-n zeros, so we filter the zeros and return array with the size of only
        // people that really arrived.
        int [] total_entered_id = new int[index];
        while(index>0){
            total_entered_id[index-1]=ids_allow_enter[index-1];
            index--;
        }
        return bubble_sort(total_entered_id);
    }

    /**
     * the function return statistics information about the crowd that enter the concert
     * @param sortedCrowed int array that contains id's of persons that entered the concert
     * @param registered HashClosed that contains id's of person who registered the concert
     * @param functionNum int which represent the hash function use to mapped id to an index (location) in HashOpen table
     * @return an int array of the following statistics based on HashOpen table: first N/2, first 3N/4, first N-N^0.5, last N^0.5
     */
    public static int[] seatingArrangement(int[] sortedCrowed, HashClosed registered, int functionNum) {
        // Create HashOpen table in the size of the amount of registered people
        HashOpen open_hash_table = new HashOpen(registered.total_nodes);
        int[] statistics = new int[4];
        // unavailable seats contains the amount of unavailable seats before seating in HashOpen table for each people in sortedCrowed
        int[] unavailable_seats = new int[sortedCrowed.length];
        for (int i=0; i<=sortedCrowed.length-1; i++){
            unavailable_seats[i]=open_hash_table.insert(sortedCrowed[i],functionNum);
        }
        for (int i=0; i<sortedCrowed.length/2; i++){
            statistics[0]+=unavailable_seats[i];
        }
        for (int i=0; i<3*(sortedCrowed.length)/4; i++) {
            statistics[1] += unavailable_seats[i];
        }
        for (int i=0; i<sortedCrowed.length-(int)Math.pow(sortedCrowed.length,0.5); i++){
            statistics[2]+=unavailable_seats[i];
        }
        for (int i=sortedCrowed.length-1; i>=sortedCrowed.length-(int)Math.pow(sortedCrowed.length,0.5); i--){
            statistics[3]+=unavailable_seats[i];
        }
        return statistics;
    }
}



