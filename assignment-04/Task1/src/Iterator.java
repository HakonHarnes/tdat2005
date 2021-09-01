public class Iterator {
    private Node node;

    public Iterator(LinkedList list){
        node = list.getHead();
    }

    public void next(){
        if(node != null) {
            node = node.next;
        }
    }

    public Node getNode(){ return node; }
}
