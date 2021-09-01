package Huffman.Decompress;

class Node {
    private int frequency;
    private byte value;

    private Node right = null;
    private Node left  = null;

    Node(int frequency, byte value){
        this.frequency = frequency;
        this.value = value;
    }

    Node(int frequency) {
        this.frequency = frequency;
    }

    int getFrequency(){ return frequency; }
    byte getValue(){ return value; }

    Node getRightNode() { return right; }
    void setRightNode(Node right) { this.right = right; }

    Node getLeftNode() { return left; }
    void setLeftNode(Node left) { this.left = left; }
}