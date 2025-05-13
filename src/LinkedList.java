import java.util.Random;

// linked list class for a deck of cards
public class LinkedList {

    public Node head;
    public Node tail;
    public int size = 0;

    LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void shuffle(int shuffle_count) {

        Random rand = new Random();
        for(int i = 0; i < shuffle_count; i++) {
            // pick two random integers
            int r1 = rand.nextInt(52);
            int r2 = rand.nextInt(52);

            swap(r1,r2); // swap nodes at these indices
        }
    }

    // remove a card from a specific index
    public Card remove_from_index(int index) {
        if (index < 0 || index >= size) return null;
        Node current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        if (current == head) return remove_from_head();
        if (current == tail){
            tail = tail.prev;
            tail.next = null;
        }
        else{
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size --;
        return current.data;
    }

    // insert a card at a specific index
    public void insert_at_index(Card x, int index) {
        if (index < 0 || index > size)return;

        Node newNode = new Node (x);

        if (index == 0) {
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        }
            else if (index == size){
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            else{
                Node current = head;
                for (int i = 0; i<index; i++){
                    current = current.next;
                }
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            }
        size++;
    }

    // swap two cards in the deck at the specific indices
    public void swap(int index1, int index2) {
        if (index1 == index2 || index1 < 0 || index2 < 0|| index1 >= size|| index2 >= size)return;

        if (index1 > index2) {
            Card temp1 = remove_from_index(index1);
            Card temp2 = remove_from_index(index2);
            insert_at_index(temp2, index2);
            insert_at_index(temp1, index1);
        }
        else{
            Card temp2 = remove_from_index(index2);
            Card temp1 = remove_from_index(index1);
            insert_at_index(temp1, index1);
            insert_at_index(temp2, index2);
        }
    }

    // add card at the end of the list
    public void add_at_tail(Card data) {
        Node newNode = new Node (data);
        if (head == null){
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // remove a card from the beginning of the list
    public Card remove_from_head() {
        if (head == null) return null;
        Card removedCard = head.data;
        if (head == tail){
            head = tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return removedCard;
    }

    // check to make sure the linked list is implemented correctly by iterating forwards and backwards
    // and verifying that the size of the list is the same when counted both ways.
    // 1) if a node is incorrectly removed
    // 2) and head and tail are correctly updated
    // 3) each node's prev and next elements are correctly updated
    public void sanity_check() {
        // count nodes, counting forward
        Node curr = head;
        int count_forward = 0;
        while (curr != null) {
            curr = curr.next;
            count_forward++;
        }

        // count nodes, counting backward
        curr = tail;
        int count_backward = 0;
        while (curr != null) {
            curr = curr.prev;
            count_backward++;
        }

        // check that forward count, backward count, and internal size of the list match
        if (count_backward == count_forward && count_backward == size) {
            System.out.println("Basic sanity Checks passed");
        }
        else {
            // there was an error, here are the stats
            System.out.println("Count forward:  " + count_forward);
            System.out.println("Count backward: " + count_backward);
            System.out.println("Size of LL:     " + size);
            System.out.println("Sanity checks failed");
            System.exit(-1);
        }
    }

    // print the deck
    public void print() {
        Node curr = head;
        int i = 1;
        while(curr != null) {
            System.out.print("[" + curr.data.getSuit() + " " + curr.data.getRank()
            + "]");
            if(curr.next != null)
                System.out.print(" -->  ");
            else
                System.out.println(" X");

            if (i % 7 == 0) System.out.println("");
            i++;
            curr = curr.next;
        }
        System.out.println("");
    }
    public int size(){
        return size;
    }
}