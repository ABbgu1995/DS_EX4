public class PersonCompareByTicketType implements Comparator
{
    public int compare(Object person1, Object person2){
        int ticket1_score=0;
        int ticket2_score=0;
        String ticket1 = ((Node) person1).getData()[3];
        String ticket2 = ((Node) person2).getData()[3];
        if (ticket1.equals("VIP") || ticket2.equals("VIP")){
            ticket1_score=4;
            ticket2_score=4;
        }
        if (ticket1.equals("GOLDEN_RING") || ticket2.equals("GOLDEN_RING")) {
            ticket1_score = 3;
            ticket2_score = 3;
        }
        if (ticket1.equals("INNER_RING") || ticket2.equals("INNER_RING")) {
            ticket1_score = 2;
            ticket2_score = 2;
        }
        if (ticket1.equals("OUTER_RING") || ticket2.equals("OUTER_RING")) {
            ticket1_score = 1;
            ticket2_score = 1;
        }
        if (ticket1_score<ticket2_score){
            // person1 won
            return 1;
        }
        else {
            return 2;
        }
    }
}
