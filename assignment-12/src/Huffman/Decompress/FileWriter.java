package Huffman.Decompress;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

class FileWriter {
    private String filename;

    FileWriter(String filename){
        this.filename = filename;
    }

    //Writes the bytes to file
    void write(byte[] data){
        try (DataOutputStream stream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            stream.write(data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}