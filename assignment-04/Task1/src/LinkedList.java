public class LinkedList {
    private Node head = null;
    private Node tail = null;

    private int length = 0;

    public int getLength(){ return length; }

    public Node getHead(){ return head; }

    public void add(Node node){
        if(head == null){
            head = node;
            tail = node;
            node.next = head;
        }
        else {
            tail.next = node;
            tail = node;
            tail.next = head;
        }

        length++;
    }

    public Node remove(Node node){
        if(head == null) return null;

        Node current = head;
        Node previous = null;

        while(!current.equals(node) && current != tail){
            previous = current;
            current = current.next;
        }

        if(!current.equals(node)) return null;

        if(previous == null) head = current.next;
        else previous.next = current.next;

        current.next = null;

        --length;

        return current;
    }

    public String toString(){
        String res = "";

        Node current = head;

        for(int i = 0; i < length; i++){
            res += current.toString() + " ";
            current = current.next;
        }

        return res;
    }
}
