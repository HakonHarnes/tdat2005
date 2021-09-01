package LempelZivHuffman;

import Huffman.Decompress.Huffman;
import LempelZiv.Decompress.LempelZivDekompresjon;

public class Decompress {
    private static final int INPUT         = 0;
    private static final int DECOMPRESSED  = 1;
    private static final int OUTPUT        = 2;

    //File paths
    private final static String[][] PATHS = {
            { "files/LempelZivHuffman/oppg12_txt/compressed.lz.hf",  "files/LempelZivHuffman/oppg12_txt/.compressed.lz",  "files/LempelZivHuffman/oppg12_txt/output.txt" },
            { "files/LempelZivHuffman/oppg12_pdf/compressed.lz.hf",  "files/LempelZivHuffman/oppg12_pdf/.compressed.lz",  "files/LempelZivHuffman/oppg12_pdf/output.pdf"   },
            { "files/LempelZivHuffman/diverse_txt/compressed.lz.hf", "files/LempelZivHuffman/diverse_txt/.compressed.lz", "files/LempelZivHuffman/diverse_txt/output.txt"},
            { "files/LempelZivHuffman/diverse_pdf/compressed.lz.hf", "files/LempelZivHuffman/diverse_pdf/.compressed.lz", "files/LempelZivHuffman/diverse_pdf/output.pdf"}
    };

    public static void main(String[] args){
        System.out.printf("%26s%36s", "FILE", "STATUS");

        //Compresses each file and prints stats
        for(String[] PATH : PATHS){
            Huffman huffman = new Huffman(PATH[INPUT], PATH[DECOMPRESSED]);
            boolean decompressed = huffman.decompress();

            if(decompressed) {
                LempelZivDekompresjon lempelZiv = new LempelZivDekompresjon(PATH[DECOMPRESSED], PATH[OUTPUT]);
                decompressed = lempelZiv.kjorDekomprimering();
            }

            System.out.printf("\n%-52s%-15s", PATH[INPUT].toUpperCase(), (decompressed? " DECOMPRESSED" : " UNCOMPRESSED"));
        }
    }
}