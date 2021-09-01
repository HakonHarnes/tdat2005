package LempelZivHuffman;

import Huffman.Compress.Huffman;
import LempelZiv.Compress.LempelZivKompresjon;

import java.io.File;

public class Compress {
    private static final int INPUT       = 0;
    private static final int COMPRESSED  = 1;
    private static final int OUTPUT      = 2;

    //File paths
    private final static String[][] PATHS = {
            { "files/LempelZivHuffman/oppg12_txt/input.txt",  "files/LempelZivHuffman/oppg12_txt/.compressed.lz",  "files/LempelZivHuffman/oppg12_txt/compressed.lz.hf"  },
            { "files/LempelZivHuffman/oppg12_pdf/input.pdf",  "files/LempelZivHuffman/oppg12_pdf/.compressed.lz",  "files/LempelZivHuffman/oppg12_pdf/compressed.lz.hf"  },
            { "files/LempelZivHuffman/diverse_txt/input.txt", "files/LempelZivHuffman/diverse_txt/.compressed.lz", "files/LempelZivHuffman/diverse_txt/compressed.lz.hf" },
            { "files/LempelZivHuffman/diverse_pdf/input.pdf", "files/LempelZivHuffman/diverse_pdf/.compressed.lz", "files/LempelZivHuffman/diverse_pdf/compressed.lz.hf" }
    };

    public static void main(String[] args){
        System.out.printf("%26s%28s%11s%12s%9s%9s\n", "FILE", "STATUS", "INPUT", "LEMPELZIV", "HUFFMAN", "DIFF");

        //Compresses each file and prints stats
        for(String[] PATH : PATHS){
            LempelZivKompresjon lempelZiv = new LempelZivKompresjon(PATH[INPUT], PATH[COMPRESSED]);
            boolean compressed = lempelZiv.kjorKompresjon();

            if(compressed){
                Huffman huffman = new Huffman(PATH[COMPRESSED], PATH[OUTPUT]);
                compressed = huffman.compress();
            }

            System.out.printf("%-45s%-15s", PATH[INPUT].toUpperCase(), (compressed? " COMPRESSED" : " UNCOMPRESSED"));

            double inputSize     = new File(PATH[INPUT]).length();
            double lempelZivSize = new File(PATH[COMPRESSED]).length();
            double huffmanSize   = new File(PATH[OUTPUT]).length();

            System.out.format("%-10.0f%-10.0f%-10.0f%-+1.2f\n", inputSize, lempelZivSize, huffmanSize, ((huffmanSize / inputSize) * 100) - 100);
        }
    }
}