public class Queue {
    private Node[] list;

    private int length = 0;
    private int start = 0;
    private int end = 0;

    public Queue(int size){
        list = new Node[size];
    }

    public boolean empty(){
        return length == 0;
    }

    public boolean full(){
        return length == list.length;
    }

    public void add(Node node){
        if(full()) return;

        list[end] = node;
        end = (end + 1) % list.length;
        length++;
    }

    public Node next(){
        if(empty()) return null;

        Node node = list[start];
        start = (start + 1) % list.length;
        length--;

        return node;
    }
}
