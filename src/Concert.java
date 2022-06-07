public class Concert {
    public LinkedList getFromFile(String FileName) // Return LinkedList with Crowd or people in reception
    public HashClosed registerCrowd(String file_path) // Return HashClosed with all registered people
    public int[] reception(String file_path, HashClosed registered) // Return int[] with sortedIds after reception
    public int reception_AverageSteps(String file_path, HashClosed registered) // Return average steps of searching in HashClosed
    public int[] seatingArrangement(int[] sortedCrowed, HashClosed registered, int functionNum) // return stats as mentioned in the assignment

}
