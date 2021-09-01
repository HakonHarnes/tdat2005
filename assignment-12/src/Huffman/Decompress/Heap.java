package Huffman.Decompress;

class Heap {
    private int length = 0;
    private Node[] nodes;

    Heap(int size){
        nodes = new Node[size];
    }

    void add(Node node){
        int i = length++;
        nodes[i] = node;
        createHeap();
    }

    Node getMin(){
        Node min = nodes[0];

        nodes[0] = nodes[--length];
        fixHeap(0);

        return min;
    }

    private void createHeap(){
        int i = length / 2;
        while(i-- > 0) fixHeap(i);
    }

    private void fixHeap(int i){
        int m = left(i);

        if(m < length){
            int h = m + 1;

            if(h < length && nodes[h].getFrequency() < nodes[m].getFrequency())
                m = h;

            if(nodes[m].getFrequency() < nodes[i].getFrequency()){
                swap(i, m);
                fixHeap(m);
            }
        }
    }

    private void swap(int i, int m){
        Node tmp = nodes[i];
        nodes[i] = nodes[m];
        nodes[m] = tmp;
    }

    private int left(int i){ return (i << 1) + 1; }
    int getLength(){ return this.length; }
}