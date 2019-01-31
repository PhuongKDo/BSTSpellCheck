import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;

public class BSTSpellChecker<E> extends BinarySearchTree<String> {
    BinarySearchTree<String> wordList = new BinarySearchTree<String>(); //BST instance is needed for this class.
    ArrayList<String> list = new ArrayList<String>(); //This array list is used to store the words from the file.
    int indexNumber = 0; //This integer value will be the print out value determining the index number of the word.
    
    public void add(String s) { //This adds a specific word to the BST tree.
        wordList.add(s);
    }
    
    public boolean contains(String s) { //Returns whether the specified word is in the tree.
        return wordList.findIterative(s) != null; //Returns a true or false. True meaning the specified word is contained in the BST tree.
    }

    public void addLinearly() { //Adds the words from the array list to the BST tree in alphabetical order, resulting in a linear tree.
        indexNumber = 0; //Restarts the indexNumber just in case another text file is read and added.
        for(int j = 0; j < list.size(); j++) // Goes through the elements in the entire array list.
        {   
            if(!contains(list.get(j))) //If the tree does not have the word specified yet, this if-statement runs.
            {
                wordList.addIterative(list.get(j)); //Adds the word iteratively from the array list to the BST tree.
                indexNumber++; //Increments indexNumber since an item was added, due to the code in the previous line.
                //System.out.println(indexNumber + " : " + list.get(j)); //Prints out the indexNumber of that word and the word itself.
            }
        }
    }
    
     //Wrapper method for sortedArrayToBSTRecursiveAdd
    public void sortedArrayToBSTRecursiveAdd() {   
        sortedArrayToBSTRecursiveAdd(0, list.size()); //Runs this method selecting the parameters at index 0 and the size of the list.
        for(int j = 0; j < list.size(); j++) //Runs a for loop to go through the elements in the array list.
        {   
            if(!contains(list.get(j))) //If the BST tree does not contain the element specified, this if statement runs.
            {
                wordList.add(list.get(j)); // Adds the word to the BST tree.
                indexNumber++; //Increments the indexNumber due to the adding.
                //System.out.println(indexNumber + " : " + list.get(j)); //Prints out the indexNumber of the element and the element itself. (Element in this case is the word)
            }
        }  
    }

    public void sortedArrayToBSTRecursiveAdd(int start, int end) { //This method recursively adds the halves of the array list to the BST tree.
        if (start >= end) //
             return;
            
        int mid = start + (end - start) / 2;  //Halves the array list tree for the first time or again(Depending if it is the first time going through this method).
        wordList.add(list.get(mid)); //Adds the specified halved element to the BST tree.
        indexNumber++; //Increments the indexNumber due to the adding.
        //System.out.println(indexNumber + "  : " + list.get(mid));  //Prints out the indexNumber of the element and the element itself. (Element in this case is the word)
            

        sortedArrayToBSTRecursiveAdd(start + 1, mid-1); //This will recursively add the halves of the left portion of the array list and add it to the BST tree.
        sortedArrayToBSTRecursiveAdd(mid+1, end - 1); //This will recursively add the halves of the right portion of the array list and add it to the BST tree.
    }
    
    //Reads the file from the list.
    public void readList() {
        // Name of the file
        String fileName = "Project3_wordlist.txt";
        try {
            // Create object of FileReader
            FileReader inputFile = new FileReader(fileName);

            // Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);

            // Variable to hold the one line data
            String line;

            // Read file line by line and print on the console
            while ((line = bufferReader.readLine()) != null)
            {
                list.add(line);
            }
            // Close the buffer reader
            bufferReader.close();
        } catch (Exception e) {
            System.out.println("Error while reading file line by line:" + e.getMessage());
        }
    }

    //this method will determine if the word might have a match, if the given word is considered as a typo
    public Set<String> closeMatches(String word) {
        //instantiate a new tree set of type string
        Set<String> potentials = new TreeSet<String>();
        //create a string to store the given word, which will be modified for the following processes of replacement, insertion, and removing
        String mutated = word;

        //firstly, if the word given is a real word, add it to the set to begin with
        if (contains(word)) 
        {
            potentials.add(word); //add the word
        }

        //now add words to the list, by checking all combinations of removing characters from the word sequentially, and then checking if the resulting word is a word.
        for (int i = 0; i < word.length(); ++i)
        {
            //mutated word, by removing the characters from the given word
            mutated = (word.substring(0, i) + word.substring(i + 1));
            //if the resulting is a word
            if (contains(mutated)) 
            {   //add to the set
                potentials.add(mutated);
            }
        }
        
        //now this is the replacing a character into a given string, by first going through the whole word
        for (int i = 0; i < word.length(); ++i) 
        {   //now go through the whole alphabet, and replace each character individually, with a character from the alphabet and check if the resulting word is a real word
            for (char c = 'a'; c <= 'z'; ++c) 
            {
                // replace each character in the word, beginning at the first chracter of the given word
                mutated = (word.substring(0, i) + String.valueOf(c) + word
                        .substring(i + 1));
                
                //check if resulting word is a word, if it is, add the word
                if (contains(mutated)) 
                {
                    potentials.add(mutated);
                }
            }
        }

        //now this is the inserting a character into a given string, by first going through the whole word
        for (int i = 0; i <= word.length(); ++i) 
        {   //now go through the whole alphabet, and add each character individually, and check if the resulting word is a real word

            for (char c = 'a'; c <= 'z'; ++c) 
            {
                // now add a character before and after the string, and then in between each character in the string
                mutated = (word.substring(0, i) + String.valueOf(c) + word
                        .substring(i));
                //check if resulting word is a word, if it is, add the word
                if (contains(mutated)) 
                {
                    potentials.add(mutated);
                }
            }
        }
        //return the resulting set
        return potentials;
    }
}
