package Huffman.Decompress;

public class Main {
    private static final int INPUT      = 0;
    private static final int OUTPUT     = 1;

    //File paths
    private final static String[][] PATHS = {
            { "files/Huffman/oppg12_txt/compressed.hf",  "files/Huffman/oppg12_txt/output.txt"  },
            { "files/Huffman//oppg12_pdf/compressed.hf", "files/Huffman/oppg12_pdf/output.pdf"  },
            { "files/Huffman/diverse_txt/compressed.hf", "files/Huffman/diverse_txt/output.txt" },
            { "files/Huffman/diverse_pdf/compressed.hf", "files/Huffman/diverse_pdf/output.pdf" }
    };

    public static void main(String[] args){
        System.out.printf("%25s%25s\n", "FILE", "STATUS");

        for(String[] PATH : PATHS){
            Huffman huffman = new Huffman(PATH[INPUT], PATH[OUTPUT]);
            System.out.printf("%-41s%s\n", PATH[INPUT].toUpperCase() , (huffman.decompress() ? "DECOMPRESSED" : " COMPRESSED"));
        }
    }
}