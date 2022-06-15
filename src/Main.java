public class Main {
    public static void main(String[] args){

        HashClosed hash = Concert.registerCrowd("input1.txt");
        int[] num_of_nodes=hash.getNodesSize();
        int[] arrived = Concert.reception("input2.txt", hash);
        int avg = Concert.reception_AverageSteps("input2.txt", hash);
        int[] steps1 = Concert.seatingArrangement(arrived, hash, 1);
        int[] steps2 = Concert.seatingArrangement(arrived, hash, 2);
        int k=13/5;
        int t= (int)Math.sqrt(8);

        //testers

        }
    }