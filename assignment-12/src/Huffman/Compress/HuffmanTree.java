package Huffman.Compress;

class HuffmanTree {
    private Heap heap = new Heap(256);
    private Node root = null;

    void initializeTree(int[] frequencies){
        //Initializes the nodes and adds them to the heap
        for(int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                Node node = new Node(frequencies[i], (byte) (i - 128));
                heap.add(node);
            }
        }

        //Initializes the Huffman tree by merging sub-trees in the heap
        while(heap.getLength() > 1){
            Node left  = heap.getMin();
            Node right = heap.getMin();

            Node head = new Node(left.getFrequency() + right.getFrequency());
            head.setLeftNode(left);
            head.setRightNode(right);

            root = head;
            heap.add(root);
        }
    }

    /*
        Returns an array of codes used for compressing
        E.g: byte 97 has code = codes[97 + 128] = 101
     */
    Bitstring[] getCodes(){
        Bitstring[] codes = new Bitstring[256];
        if(root == null) return null;

        getCode(codes, root.getRightNode(), 1, 0);
        getCode(codes, root.getLeftNode(), 0, 0);

        return codes;
    }

    //Initializes a value in the code array
    private void getCode(Bitstring[] codes, Node node, long code, int bitCount){
        bitCount++;

        //Checks if the node is a leaf node 
        if(node.getRightNode() == null && node.getLeftNode() == null){
            codes[node.getValue() + 128] = new Bitstring(code, bitCount);
            return;
        }

        getCode(codes, node.getRightNode(), (code * 2) + 1, bitCount);
        getCode(codes, node.getLeftNode(), (code * 2), bitCount);
    }
}