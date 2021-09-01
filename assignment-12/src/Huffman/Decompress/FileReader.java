package Huffman.Decompress;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

class FileReader {
    private String filename;
    private int[] frequencies = new int[256];
    private byte[] data = null;

    FileReader(String filename){
        this.filename = filename;
    }

    //Reads the data from file
    private void read(){
        try (DataInputStream stream = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)))){
            for(int i = 0; i < frequencies.length; i++)
                frequencies[i] = stream.readInt();

            data = stream.readAllBytes();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Returns the frequencies from file
    int[] readFrequencies(){
        if(data == null) read();

        return frequencies;
    }

    //Returns the data from file
    byte[] readData(){
        if(data == null) read();

        return data;
    }
}
