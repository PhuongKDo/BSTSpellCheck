import java.util.Scanner;
public class BSTSpellCheck {
    public void callMe() {
        BSTSpellChecker<String> bst = new BSTSpellChecker<String>(); 
        Scanner s = new Scanner(System.in);       
                
        bst.readList();  
        bst.sortedArrayToBSTRecursiveAdd();
        
        System.out.println("Pick type:");
        System.out.println("1 : BST unbalance");
        System.out.println("2 : BST balanced");
        String t;
        int exit = 1;
        do{
          t = s.nextLine();
           if (t.equals("1")){
            System.out.println("You picked BST unbalance.");
            System.out.println("Enter phrase:");
            String in = s.nextLine();
            Scanner scanner = new Scanner(in);
            long start_time = System.currentTimeMillis();
            while(scanner.hasNext()) {
                String word = scanner.next();
                word = word.toLowerCase();
                System.out.println("Closematches for: " + word);
                System.out.println(bst.closeMatches(word));
            }
            long end_time = System.currentTimeMillis();
            long difference = end_time - start_time;
            System.out.println("Time taken in milli sec : " + difference);
            exit = 0;
          }
          else if (t.equals("2"))  {
            //balance
            bst.isBalanced();  
            
            System.out.println("You picked BST balanced.");
            System.out.println("Enter phrase:");
            String in = s.nextLine();
            Scanner scanner = new Scanner(in);
            long start_time = System.currentTimeMillis();
                while(scanner.hasNext()) {
                String word = scanner.next();
                word = word.toLowerCase();
                System.out.println("Closematches for: " + word);
                System.out.println(bst.closeMatches(word));
            } 
            long end_time = System.currentTimeMillis();
            long difference = end_time - start_time;
            System.out.println("Time taken in milli sec : " + difference);
            exit = 0;
          }else
                System.out.println("Enter again:");
        }while(exit == 1); 
    }
}