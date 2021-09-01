package Huffman.Compress;

import java.io.File;

public class Main {
    private static final int INPUT  = 0;
    private static final int OUTPUT = 1;

    //File paths
    private final static String[][] PATHS = {
            { "files/Huffman/oppg12_txt/input.txt",  "files/Huffman/oppg12_txt/compressed.hf"  },
            { "files/Huffman/oppg12_pdf/input.pdf",  "files/Huffman/oppg12_pdf/compressed.hf"  },
            { "files/Huffman/diverse_txt/input.txt", "files/Huffman/diverse_txt/compressed.hf" },
            { "files/Huffman/diverse_pdf/input.pdf", "files/Huffman/diverse_pdf/compressed.hf" }
    };

    public static void main(String[] args){
        System.out.printf("%22s%24s%11s%12s%8s\n", "FILE", "STATUS", "INPUT", "COMPRESSED", "DIFF");

        //Compresses each file and prints stats
        for(String[] PATH : PATHS){
            Huffman huffman = new Huffman(PATH[INPUT], PATH[OUTPUT]);
            System.out.printf("%-37s%-15s", PATH[INPUT].toUpperCase(), (huffman.compress() ? " COMPRESSED" : " UNCOMPRESSED"));

            double inputSize      = new File(PATH[INPUT]).length();
            double compressedSize = new File(PATH[OUTPUT]).length();

            System.out.format("%-10.0f%-10.0f%-+1.2f\n", inputSize, compressedSize, ((compressedSize / inputSize) * 100) - 100);
        }
    }
}