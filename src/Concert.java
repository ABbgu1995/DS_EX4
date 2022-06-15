import java.io.*;

public class Concert {
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
        public static int reception_AverageSteps(String file_path, HashClosed registered) {
            LinkedList linked_list_reception = getFromFile(file_path);
            int sum_of_steps=0;
            int[] search_result;
            Node runner = linked_list_reception.getHead();
            while (runner!=null){
                search_result = registered.search(runner.getData());
                if(search_result[0]==1){
                    sum_of_steps=sum_of_steps+search_result[1];
                }
                runner=runner.getNext();
            }
            return sum_of_steps/registered.total_nodes;


    }
    private static int [] bubble_sort(int[] arr_to_sort) {
        int n = arr_to_sort.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr_to_sort[j] > arr_to_sort[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr_to_sort[j];
                    arr_to_sort[j] = arr_to_sort[j + 1];
                    arr_to_sort[j + 1] = temp;
                }
        return arr_to_sort;
    }

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
                runner.getData()[3]="0";
                index++;
            }
            runner=runner.getNext();
        }
        int num_of_waiters=linked_list_reception.getSize() - index;
        num_of_tickets_available=ids_allow_enter.length-index;
        runner = linked_list_reception.getHead();
        while (num_of_tickets_available!=0 && num_of_waiters>0){
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
        return bubble_sort(ids_allow_enter);

    }
    public static int[] seatingArrangement(int[] sortedCrowed, HashClosed registered, int functionNum) {
        // HashOpen(registered.total_nodes) -> HashOpen(sortedCrowed.length)
        HashOpen open_hash_table = new HashOpen(registered.total_nodes);
        int[] statistics = new int[4];
        int[] steps = new int[sortedCrowed.length];
        for (int i=0; i<=sortedCrowed.length-1; i++){
            steps[i]=open_hash_table.insert(sortedCrowed[i],functionNum);
        }
        for (int i=0; i<sortedCrowed.length/2; i++){
            statistics[0]+=steps[i];
        }
        for (int i=0; i<3*(sortedCrowed.length)/4; i++) {
            statistics[1] += steps[i];
        }
        for (int i=0; i<sortedCrowed.length-(int)Math.pow(sortedCrowed.length,0.5); i++){
            statistics[2]+=steps[i];
        }
        for (int i=sortedCrowed.length-1; i>=sortedCrowed.length-(int)Math.pow(sortedCrowed.length,0.5); i--){
            statistics[3]+=steps[i];
        }
        return statistics;
    }
}



