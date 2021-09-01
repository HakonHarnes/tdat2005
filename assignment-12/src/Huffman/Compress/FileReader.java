package Huffman.Compress;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

class FileReader {
    private String filename;

    FileReader(String filename){
        this.filename = filename;
    }

    //Reads all bytes from file
    byte[] readBytes(){
        try (DataInputStream file = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)))){
            return file.readAllBytes();
        } catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
}