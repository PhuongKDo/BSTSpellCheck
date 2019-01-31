import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.ArrayList;

public class HashTableSpellCheck {
        //list contains all the words
    public static List<String> readWords( BufferedReader in ) throws IOException
    {
            String oneLine;
            List<String> lst = new ArrayList<>( );

            while( ( oneLine = in.readLine( ) ) != null )
                    lst.add( oneLine );

            return lst;
    }
    
    private static <E> void update( Map<E,List<String>> m, E key, String value ) {
            List<String> lst = m.get( key );
            if( lst == null )
            {
                lst = new ArrayList<>( );
                m.put( key, lst );
            }

            lst.add( value );
    }
    
    public static void closeMatches( String word, List<String> words ) {
        List<String> adjWords = new ArrayList<>( );
        Map<Integer,List<String>> wordsByLength = new TreeMap<>( );

        // Group the words by their length
        for( String w : words )
            update( wordsByLength, w.length( ), w );

        List<String> entry = wordsByLength.get(word.length());                        
        for(String s : entry) 
            if(oneCharOff(s,word))
                System.out.print(s+" ");        
    }
    
    private static boolean oneCharOff( String word1, String word2 ) {
            if( word1.length( ) != word2.length( ) )
                return false;

            int diffs = 0;

            for( int i = 0; i < word1.length( ); i++ )
                if( word1.charAt( i ) != word2.charAt( i ) )
                    if( ++diffs > 1 )
                        return false;
            return diffs == 1;
    }    
    
    public static void callMe() {           
        try{
            FileReader fin = new FileReader( "Project3_wordlist.txt" );
            BufferedReader bin = new BufferedReader( fin );
            List<String> words = readWords( bin );
    
            Scanner s = new Scanner(System.in);
    
            HashTable<String> wordlists = new HashTable<>();
            for (String word : words) 
                wordlists.insert(word);
    
            System.out.println("Enter phrase:");
            String in = s.nextLine();
            Scanner scanner = new Scanner(in);
            long start_time = System.currentTimeMillis();
            while(scanner.hasNext()) {
                String word = scanner.next();
                word = word.toLowerCase();
                for(int i = 0; i<word.length(); i++) {
                    String temp = "";
                    while(i<word.length() && " -,.;?".indexOf(word.charAt(i))==-1) {
                        temp = temp + word.charAt(i);
                        i++;
                    }
                    if(!temp.equals("")&&!wordlists.contains(temp)) {
                        System.out.println("Closematches for: " + temp);
                        closeMatches(temp, words );
                        System.out.println();
                    }   
                }                
            } 
            int col =  wordlists.getCollisions();
            System.out.println("Collisions: " + col);
            long end_time = System.currentTimeMillis();
            long difference = end_time - start_time;
            System.out.println("Time taken in milli sec : " + difference);
        }catch (Exception e){
               System.out.println("file not exisst!");
        }
    }
}
