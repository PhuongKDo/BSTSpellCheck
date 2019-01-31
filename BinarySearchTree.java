public class BinarySearchTree<E extends Comparable<E>> {
    // Nested class representing one node of the tree.  Very similar to the Node
    //  class we used for LinkedList -- the only difference is that we keep
    //  track of two children instead of a single "next" node.
    static class Node<E> {
        protected E data;
        protected Node<E> left, right;
        
        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    
    protected Node<E> root;
    
    // Wrapper method for pre-order traversal.
    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    // Pre-order traverses the tree, starting from the node where.
    private void preOrderTraverse(Node<E> where) {
        if (where != null) {    // base case is when where == null... do nothing
            System.out.println(where.data);
            preOrderTraverse(where.left);
            preOrderTraverse(where.right);
        }
    }

    // Wrapper method for in-order traversal.
    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    // In-order traverses the tree, starting from the node where.
    private void inOrderTraverse(Node<E> where) {
        if (where != null) {    // base case is when where == null... do nothing
            inOrderTraverse(where.left);
            System.out.println(where.data);
            inOrderTraverse(where.right);
        }
    }
    
    public void addIterative(E newItem) {
        //temp node, that traverses the BST
        Node<E> temp = root;
        
        //if there is no root node, start a new BST, with a new node
        if (root == null)
        {
            root = new Node<E>(newItem, null, null);    
        }
        else
        {   //this while loop traverses through the BST, inorder to find the exact location to put the new data, into a new node
            while(1 != 2)
            {   
                // if the point being looked at is the correct location for the node, and there is a spot open for the new item, place it in the open slot
                if (temp.data.compareTo(newItem) < 0 && temp.right == null)         // add a new left child
                {
                    temp.right = new Node<E>(newItem, null, null);  
                    // break out of the while loop
                    break;
                }
                // if the point being looked at is the correct location for the node, and there is a spot open for the new item, place it in the open slot
                    else if (temp.data.compareTo(newItem) > 0 && temp.left == null) 
                    {                                                               //  add a new right child
                        temp.left = new Node<E>(newItem, null, null);
                        //break out of the while loop
                        break;
                    }
                //if the value of the item is the same as the current temp node, break the loop and do nothing.
                if (temp.data.compareTo(newItem) == 0)
                    break;
                //if the new item is less than the data at the temp location, traverse left through the bst
                else if (temp.data.compareTo(newItem) > 0)
                    temp = temp.left;
                //if the new item is greater than the data at the temp location, traverse right through the bst
                else if (temp.data.compareTo(newItem) < 0)
                    temp = temp.right;
            }
        }
    }
    
    public boolean isBalanced() {
        if (root == null)
            return true;
        if (getHeight(root) == -1)
            return false;
        return true;
    }
 
    public int getHeight(Node<E> root) {
        if (root == null)
            return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == -1 || right == -1)
            return -1;
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    // Wrapper method for add
    public void add(E newItem) {
        if (root == null)   // special case for adding to the root (we need to modify the root reference)
            root = new Node<E>(newItem, null, null);
        else
            add(newItem, root);
    }
    
    // Recursively adds newItem to the tree rooted at where.
    private void add(E newItem, Node<E> where) {
        // remember that a.compareTo(b) returns 0 if a is "equal to" b, a
        // negative value if a is "less than" b, or
        // a positive value if a is "greater than" b
        int compare = newItem.compareTo(where.data);
        if (compare < 0 && where.left == null) // base case - add a new left
                                                // child to where
            where.left = new Node<E>(newItem, null, null);
        else if (compare > 0 && where.right == null) // base case - add a new
                                                        // right child to where
            where.right = new Node<E>(newItem, null, null);
        else if (compare < 0)
            add(newItem, where.left); // recursively add newItem to where's left
                                        // subtree
        else if (compare > 0)
            add(newItem, where.right); // recursively add newItem to where's
                                        // right subtree

        // (do nothing if compare == 0... we don't allow duplicate elements in
        // the tree)
    }

    public E findIterative(E someItem) {
        Node<E> temp = root;

        //return null if empty bst
        if(temp == null)
            return null;
        
        //runs until something is either a null or the data is returned.
        while(1 != 2)
        {   
            //if the data is exactly the same as someItem at the current temp node in BST, return this data
            if (temp.data.compareTo(someItem) == 0)
                return temp.data;
            
            //return null if the list traversed and found nothing, and reached a null
            if (temp.data.compareTo(someItem) < 0 && temp.right == null)            
            {
                return null;                
            }
            //return null if the list traversed and found nothing, and reached a null
            else if (temp.data.compareTo(someItem) > 0 && temp.left == null)    
                {                                                               
                    return null;
                }
            
            //if the new item is less than the data at the temp location, traverse left through the bst
            if (temp.data.compareTo(someItem) > 0)
                temp = temp.left;
            //if the new item is greater than the data at the temp location, traverse right through the bst
            else if (temp.data.compareTo(someItem) < 0)
                temp = temp.right;
        }   
    }
    
    // Wrapper method for toString
    public String toString() {
        return toString(root, "");
    }
    
    // Recursive version of toString
    private String toString(Node<E> where, String offset) {
        if (where == null)
            return offset + "null";
        else
            return offset + where.data + "\n" + toString(where.left, offset + " ") + "\n" + toString(where.right, offset + " ");
    }
}