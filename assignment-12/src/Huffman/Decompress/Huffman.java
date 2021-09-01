package Huffman.Decompress;

import java.io.*;

public class Huffman {
    private String pathInput;
    private String pathOutput;

    public Huffman(String pathInput, String pathOut){
        File file = new File(pathInput);
        if (!file.exists() || file.isDirectory())
            throw new IllegalArgumentException("Invalid file path");

        this.pathInput  = pathInput;
        this.pathOutput = pathOut;
    }

    public boolean decompress(){
        //Initializes reader
        FileReader reader = new FileReader(pathInput);

        //Reads in the frequency array
        int[] frequencies = reader.readFrequencies();
        if(frequencies == null || frequencies.length == 0) return false;

        //Reads in the data
        byte[] data = reader.readData();
        if(data == null || data.length == 0) return false;

        //Finds the number of bytes to be decompressed
        int bytesToDecompress = 0;
        for(int frequency : frequencies)
            bytesToDecompress += frequency;

        //Initializes the Huffman tree
        HuffmanTree tree = new HuffmanTree();
        tree.initializeTree(frequencies);

        //Decompresses the data
        byte[] decompressedData = tree.decompressData(data, bytesToDecompress);
        if(decompressedData == null || decompressedData.length == 0) return false;

        //Writes the decompressed data to file
        FileWriter fileWriter = new FileWriter(pathOutput);
        fileWriter.write(decompressedData);

        return true;
    }
}