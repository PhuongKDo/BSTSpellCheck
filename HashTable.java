import java.util.Scanner;
public class HashTable<E> {
    //default table size of 5
    private static final int DEFAULT_TABLE_SIZE = 5;
    private E[ ] array;         
    private int occupied;       
    StringHasher hasher;
    private int collisions = 0;
    
    public HashTable() {
        this( DEFAULT_TABLE_SIZE );
    }     
    
    public HashTable(int size) {   
        Scanner s = new Scanner(System.in);
        System.out.println("Type of hash:");
        System.out.println("Pick hasher types:");
        System.out.println("1 : djb2");
        System.out.println("2 : sdbm");
        System.out.println("3 : loselosehash");
        System.out.println("Enter number:");
        String t;
        int exit = 1;
        do{
            t = s.next();
            if(t.equals("1")){
               System.out.println("You picked djb2.");
               djb2  hasherr = new djb2();
               exit = 0;
            }else if(t.equals("2")){
               System.out.println("You picked sdbm.");
               sdbm  hasherr = new sdbm();
               exit = 0;
            }else if(t.equals("2")){
               System.out.println("You picked loselosehash."); 
               loselosehash  hasherr = new loselosehash();
               exit = 0;
            }else 
               System.out.println("Enter again:");
        }while (exit == 1); 
        
        allocateArray(size);
        occupied = 0;
    }
    
    private void allocateArray( int arraySize ) {
        array = (E[]) new Object[nextPrime(arraySize) ];
    }
    
    //override with new hash function
    private int hash(E someItem) {
         int hashVal = someItem.hashCode( );

         hashVal %= array.length;
         if( hashVal < 0 ){
             hashVal += array.length;
         }
 
         return hashVal;
    }

    private int findpos(E someItem) {
        int currentpos = hash(someItem); 
        while(array[currentpos]!=null&&!array[currentpos].equals(someItem)) {
            currentpos += 1;
            if(currentpos >= array.length-1)
                currentpos -= array.length-1;
        }
        return currentpos;
    }

    public boolean contains (E someItem) {
        int currentpos = findpos(someItem);
        return array[currentpos]!=null&&array[currentpos].equals(someItem);    
    }
    
    //add
    public boolean insert (E someItem) {
        int currentpos = findpos(someItem);
        if (array[currentpos]!=null&&array[currentpos].equals(someItem))
            return false;
        array[currentpos] = someItem;
        occupied++;
        if (occupied>array.length/2){
            collisions++;
            rehash();
        }
        return true;
    }
    
    private void rehash() {
        E[] oldArray = array;
        
        //Rehashing table length to 2*(old length) + 1 to prevent collision
        allocateArray(2*oldArray.length + 1);
        
        occupied = 0;
        for( E entry : oldArray ){
            if(entry != null)
                insert(entry);
        }
    }
    
    //return collision
    public int getCollisions() {
        return collisions;
    }
    
    private static int nextPrime( int n ) {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    private static boolean isPrime( int n ) {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }

    public static void main(String[] args) {
         HashTable<String> H = new HashTable<>( );       
         
         final int NUMS = 200000;
         final int GAP  =   37;
         
         for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ) 
             H.insert( "" + i);
    }
}