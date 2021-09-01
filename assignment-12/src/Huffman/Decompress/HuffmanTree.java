package Huffman.Decompress;

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

    //Returns the decompressed data
    byte[] decompressData(byte[] data, int bytesToDecompress){
        byte[] decompressedData = new byte[bytesToDecompress];
        int bytesDecompressed = 0;
        Node node = root;

        //Loops through each bit in the byte
        for(byte b : data){
            for(int i = 7; i >= 0; i--){
                int bit = (b >> i) & 1;

                //Traverses the tree by setting the current node to be a child node
                node = (bit == 1) ? node.getRightNode() : node.getLeftNode();

                //Checks if the node is a leaf node
                if(node.getRightNode() == null && node.getLeftNode() == null){
                    decompressedData[bytesDecompressed++] = node.getValue();
                    node = root;

                    //Checks if all the data has been decompressed
                    if(bytesDecompressed == bytesToDecompress) return decompressedData;
                }
            }
        }

        return null;
    }
}