public class Node {
    int data;
    Node next;

    public Node(int data, Node next){
        this.data = data;
        this.next = next;
    }

    public Node(int data){
        this.data = data;
        this.next = null;
    }

    public String toString(){
        return data + "";
    }

    public boolean equals(Node node){
        return node.data == data;
    }
}
