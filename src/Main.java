public class Main {
    public static void main(String[] args){

        HashClosed hash = Concert.registerCrowd("input1.txt");
        int[] num_of_nodes=hash.getNodesSize();
        int[] arrived = Concert.reception("input2.txt", hash);
        int avg = Concert.reception_AverageSteps("input2.txt", hash);
        int[] steps1 = Concert.seatingArrangement(arrived, hash, 1);
        int[] steps2 = Concert.seatingArrangement(arrived, hash, 2);

        //testers
        // Search tests
        String[] person = {"387579003","dfd","34","3434"};
        int[] answer = hash.search(person);
        System.out.println();

        }
    }