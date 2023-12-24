import java.util.NoSuchElementException;
import java.util.*;

public class MemLinkedList {
    private class Node {
        char flag; 
        int size; 
        Node prev;
        Node next;

        Node(char flag, int size) {
            this.flag = flag;
            this.size = size;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    public MemLinkedList() {
        head = new Node('F', 1000);
        //System.out.println(head); 
        tail = head;
    }

    public void request(int size) {
        //System.out.println("Check 1");

        if (size <= 0) 
        {
            throw new NoSuchElementException();
        }

        int newsize = size;
        Node cur = head;

        do{
            if (cur.size >= newsize)
            {
                if(cur.flag == 'F')
                {
                    if (cur.size == newsize) 
                    {
                        //System.out.println("Check");
                        cur.flag = 'A';
                    } 
                    else 
                    {
                        //System.out.println("Check");
                        Node newFN = new Node('F', cur.size - newsize);
                        Node newAN = new Node('A', newsize);
                        newAN.prev = cur;
                        newAN.next = cur.next;

                        if (cur.next != null) 
                        {
                            //System.out.println("Check");
                            cur.next.prev = newFN;
                        }
                        else if (cur == tail) 
                        {
                            tail = newAN;
                        }

                        cur.size = newsize;
                        cur.flag = 'A';
                        cur.next = newFN;
                    }
                    return;
                }
            }
            cur = cur.next;
        }while (cur != null); 

        if(cur == null) 
        {
            throw new NoSuchElementException();
        }
    }

    public void release(int size) 
    {
        if (size <= 0)
        {
            throw new NoSuchElementException();
        }

        Node cur = head;
        //Node prev1 = null;
        //Node next1 = null;
        int checkAllocation = 0;
        int checkFree = 0;
        int newsize = 0;

        //System.out.println(current.flag);
        //attempted to get it to merge, coudln't figure it out :/
        do
        {
            //if (cur.flag == 'A')
            {
                if(cur.size == size)
                {
                    cur.flag = 'F';
                    //System.out.println(current.next.flag);
                    //break;
                    //System.out.println(current.prev.flag);
                    if(cur.prev != null)
                    {
                        if(cur.prev.flag == 'F')
                        {
                            //System.out.println("check1");
                            cur.prev.next = cur.next;   
                            cur.next.prev = cur.prev;
                            newsize = cur.size; 
                            cur = cur.prev;
                            cur.size += newsize;
                        }
                    } 

                    if(cur.next != null)
                    {
                        if(cur.next.flag == 'F')
                        {
                            if(cur.next.next != null)
                            {
                                cur.next.next.prev = cur;
                                cur.next = cur.next.next;
                                cur = cur.next;

                            }
                        }
                    }
                    /*if(current.prev=head)
                    {
                    head = current;

                    }*/
                    break;
                }	
            }
            //break;
            //prev = current;
            cur = cur.next;
        }while(cur != null); 

        /*if(current.next.flag == 'F')
        {

        }*/
        cur = head;

        
        /* 
         * 
         * checking to see if numbers were actually summing properly
        do{
        if(current.flag=='F')
        {
        checkFree += current.size;
        }
        if(current.flag=='A')
        {
        checkAllocation += current.size;
        }
        if(checkFree == 1000)//these numbers are hardcoded
        {
        clear();
        }

        //add if statement
        current = current.next;
        }while(current != null); 
         */

        /*System.out.println("Current Memory: " + checkFree); 
        if(checkFree == 1000)
        {
        //System.out.println(1000);
        current.size = 1000;
        }*/

        if(cur == null) 
        {
            throw new NoSuchElementException();
        }
        return;

    }

    public void clear() {
        head = new Node('F', 1000); //these numbers are hardcoded
        tail = head;
    }

    public void print() {

        MemLinkedList memList = new MemLinkedList();
        Node cur = head;

        while (cur != null) {
            //if()
            System.out.print("| " + cur.flag + " | " + cur.size + "K | <-- ");
            cur = cur.next;
        }

        System.out.println("Tail");
    }

    public static void main(String[] args) {
        MemLinkedList memList = new MemLinkedList();

        System.out.println("Initial state:");
        memList.print();

        System.out.println("\nRequesting 100K:");
        memList.request(100);
        memList.print();

        System.out.println("\nRequesting 200K:");
        memList.request(200);
        memList.print();

        System.out.println("\nReleasing 100K:");
        memList.release(100);
        memList.print();

        System.out.println("\nReleasing 200K:");
        memList.release(200);
        memList.print();

        //System.out.println("\nClearing the memory:");
        //memList.clear();
        //memList.print();
    }
}

