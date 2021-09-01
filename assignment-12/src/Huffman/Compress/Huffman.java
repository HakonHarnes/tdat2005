package Huffman.Compress;

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

    public boolean compress(){
        //Reads bytes from the input file
        FileReader reader = new FileReader(pathInput);
        byte[] data = reader.readBytes();
        if(data == null || data.length == 0) return false;

        //Initializes the frequency array
        int[] frequencies = new int[256];
        for(byte b : data){
            frequencies[b + 128] += 1;
        }

        //Initializes the Huffman tree
        HuffmanTree tree = new HuffmanTree();
        tree.initializeTree(frequencies);

        //Gets the codes
        Bitstring[] codes = tree.getCodes();

        //Converts the input data to compressed data
        Bitstring[] compressedData = new Bitstring[data.length];
        for(int i = 0; i < data.length; i++)
            compressedData[i] = codes[data[i] + 128];

        //Writes the compressed output to file
        FileWriter fileWriter = new FileWriter(pathOutput);
        fileWriter.write(frequencies);
        fileWriter.write(compressedData);

        return true;
    }
}