import java.util.Scanner;

public class TogetherClient
{
    public static void main(String [] args)
    {
        Scanner s = new Scanner(System.in);
        HashTableSpellCheck callHash = new  HashTableSpellCheck();
        BSTSpellCheck callBst = new  BSTSpellCheck();
        System.out.println("Pick type:");
        System.out.println("1 : Binary Search Tree (unbalance/balanced)");
        System.out.println("2 : Hash Table (djb2/sdbm/loselosehash)");
        System.out.println("3 : Exit");
        String t;
        int exit = 1;
        do{
            t = s.next();
            if (t.equals("1")){
                System.out.println("You picked Binary Search Tree.");
                callBst.callMe();
                
                System.out.println("Pick type:");
                System.out.println("1 : BST (unbalance/balanced)");
                System.out.println("2 : HASH (djb2/sdbm/loselosehash)");
                System.out.println("3 : Exit");
            }
            else if (t.equals("2"))  {
                System.out.println("You picked Hash Table.");
                callHash.callMe();
                
                System.out.println("Pick type:");
                System.out.println("1 : BST (unbalance/balanced)");
                System.out.println("2 : HASH (djb2/sdbm/loselosehash)");
                System.out.println("3 : Exit");
            }
            else if (t.equals("3")) {
                System.out.println("Thank you and Goodbye!");
                break;
            } else
                System.out.println("Enter again:");
        }while(!t.equals("1") || !t.equals("2")); 
    }
}
