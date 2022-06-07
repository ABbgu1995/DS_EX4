public class Main {
    public static void main(String[] args){
        HashClosed hash = Concert.registerCrowd("input1.txt");
        int[] arrived = Concert.reception("input2.txt", hash);
        int avg = Concert.reception_AverageSteps("input2.txt", hash);
        int[] steps1 = Concert.seatingArrangement(arrived, hash, 1);
        int[] steps2 = Concert.seatingArrangement(arrived, hash, 2);
        }
    }
}
