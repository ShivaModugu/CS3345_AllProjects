//Name: Shiva Modugu 
//Net ID: sxm220181
//Class: CS3345.503
//Project 2

import java.util.*;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType> {
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    public BinarySearchTree() {
        r = null;
    }

    //using to construct the tree
    public void makingTree( AnyType x )
    {
        r = makingTree( x, r );
        //System.out.println(r); making sure tree was printed properly
    }

    private BinaryNode<AnyType> makingTree( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
        {
            return new BinaryNode<>( x, null, null );
        }

        int compareResult = x.compareTo( t.element );

        if( compareResult > 0 )
        {
            t.right = makingTree( x, t.right );
        }
        if( compareResult < 0 )
        {
            t.left = makingTree( x, t.left );
        }

        return t;
    }

    // a) nodeCount
    public int nodeCount() {
        return nodeCount(r);
        //System.out.println("Check 1: Number of Nodes");
    }

    private int nodeCount(BinaryNode<AnyType> t) {
        if(t != null)
        {
            return 1 + nodeCount(t.left) + nodeCount(t.right);
        }

        return 0;
    }

    //b) isFull
    public boolean isFull() {
        return isFull(r);
        //System.out.println("check 2") 
    }

    //throwing exception here because isFull can't be full if there is no tree :D
    private boolean isFull(BinaryNode<AnyType> t) {
        if(r == null)
        {
            throw new UnderflowException();
        }
        if (t == null)
        {
            return true;
        }
        if (t.left == null)
        {
            if(t.right ==null)
            {
                return true;
            }
        }
        if (t.left != null) 
        {
            if(t.right != null)
            {
                return isFull(t.left) && isFull(t.right);
            }
        }

        return false;
    }   

    //c) compareStructure
    public boolean compareStructure(BinarySearchTree<AnyType> s) {
        return compareStructure(r, s.r);
    }

    private boolean compareStructure(BinaryNode<AnyType> t, BinaryNode<AnyType> s) {
        if (t != null)
        {
            if(s != null)
            {
                return compareStructure(t.left, s.left) && compareStructure(t.right, s.right);
            }
        }

        if (t == null)
        {
            if(s == null)
            {
                return true;
            }   
        }

        return false;
    }

    //d) equals
    public boolean equals(BinarySearchTree<AnyType> s) {
        return equals(r, s.r);
    }

    private boolean equals(BinaryNode<AnyType> t, BinaryNode<AnyType> s) {
        if (t != null)
        {
            if(s != null)
            {
                return (t.element.equals(s.element) && equals(t.left, s.left) && equals(t.right, s.right));
            }   
        }

        if (t == null)
        {
            if(s == null)
            {
                return true;
            }
        }

        return false;
    }

    //e) copy
    public BinarySearchTree<AnyType> copy() {
        BinarySearchTree<AnyType> s = new BinarySearchTree<>();
        s.r = copy(r);
        return s;
        //System.out.println("check 4" + s) <-- wasn't copying correctly 

    }

    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t) {
        if (t != null)
        {
            return new BinaryNode<AnyType>(t.element, copy(t.left), copy(t.right));
        }

        return null;
    }
    //f) mirror()
    public BinarySearchTree<AnyType> mirror() {
        BinarySearchTree<AnyType> s = new BinarySearchTree<>();
        s.r = mirror(r);
        return s;
    }

    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t) {
        if (t != null)
        {
            return new BinaryNode<AnyType>(t.element, mirror(t.right), mirror(t.left));
        }

        return null;
    }

    //g) isMirror()
    public boolean isMirror(BinarySearchTree<AnyType> s) {
        return isMirror(r, s.r);
        //System.out.println("Why is it not going into here");
    }

    private boolean isMirror(BinaryNode<AnyType> t, BinaryNode<AnyType> s) {
        if (t == null)
        {
            if(s == null)
            {
                return true;
            }
        }

        if (t != null)
        {
            if(s != null)
            {
                return isMirror(t.left, s.right) && isMirror(t.right, s.left);
            }
        }
        return false;
    }

    //h) rotateLeft()
    public void rotateLeft(AnyType x) {
        r = rotateLeft(x, r); 
    }

    private BinaryNode<AnyType> rotateLeft(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
        {
            return null;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
        {
            t.left = rotateLeft(x, t.left);
        }

        if (compareResult > 0)
        {
            t.right = rotateLeft(x, t.right);
        }

        if(compareResult == 0)
        {
            if (t.right != null) {
                BinaryNode<AnyType> newr = t.right;
                t.right = newr.left;
                newr.left = t;
                return newr;
            }
        }
        return t;
    }

    //i) rotateRight)
    public void rotateRight(AnyType x) {
        r = rotateRight(x, r);
    }

    private BinaryNode<AnyType> rotateRight(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
        {
            return null;
        }
        int compareResult = x.compareTo(t.element);

        if (compareResult > 0)
        {
            t.right = rotateRight(x, t.right);
        }

        if (compareResult < 0)
        {
            t.left = rotateRight(x, t.left);
        }

        if(compareResult == 0)
        {
            if (t.left != null) {
                BinaryNode<AnyType> newr = t.left;
                t.left = newr.right;
                newr.right = t;
                return newr;
            }
        }
        return t;
    }

    //j) printLevels()
    public void printLevels() {
        Queue<BinaryNode<AnyType>> queue = new LinkedList<BinaryNode<AnyType>>();
        if (r != null)
        {
            queue.add(r);
        }

        while (!queue.isEmpty())
        {
            BinaryNode<AnyType> node = queue.poll();
            System.out.print(node.element + " ");

            if (node.right != null)
            {
                queue.add(node.right);
            }

            if (node.left != null)
            {
                queue.add(node.left);
            }

        }
    }

    private int height(BinaryNode<AnyType> t) {

        if (t != null)
        {
            return 1 + Math.max(height(t.left), height(t.right));

        }
        return -1;
    }

    private BinaryNode<AnyType> r;
    //k) main()
    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        BinarySearchTree<Integer> s = new BinarySearchTree<>();
        final int[] array = {100, 150, 50, 40, 55};
        int i = 0;
        while(i < array.length)
        {
            t.makingTree(array[i]);
            i = i+1;
        }

        System.out.println("Node count: " + t.nodeCount());
        System.out.print("\n");

        System.out.println("Is full: " + t.isFull());
        System.out.print("\n");

        int j = 10;
        while(j != 0)
        {
            s.makingTree(j);
            j = (j + 10) % 50;
        }

        System.out.println("Compare structure: " + t.compareStructure(s));
        System.out.print("\n");

        BinarySearchTree<Integer> copyT = t.copy();
        System.out.println("Equals original and copied tree: " + t.equals(copyT));
        System.out.print("\n");

        System.out.println("Original tree:");
        t.printLevels();
        System.out.print("\n");

        System.out.println("\nCopied tree:");
        copyT.printLevels();
        System.out.print("\n");
        System.out.print("\n");

        BinarySearchTree<Integer> mirroredT = t.mirror();
        System.out.println("Mirrored tree:");
        mirroredT.printLevels();
        System.out.print("\n");
        System.out.print("\n");

        System.out.println("Is mirrored: " + t.isMirror(mirroredT));
        System.out.print("\n");

        System.out.println("Before rotation (h):");
        t.printLevels();
        System.out.print("\n");
        t.rotateRight(100);
        System.out.println("\nAfter rotation (h):");
        t.printLevels();
        System.out.print("\n");        
        System.out.print("\n");

        System.out.println("Before rotation (i):");
        t.printLevels();
        System.out.print("\n");
        t.rotateLeft(100);
        System.out.println("\nAfter rotation (i):");
        t.printLevels();
        System.out.print("\n");

        System.out.println("\nLevel-by-level print:");
        t.printLevels();
    }
    //
}
