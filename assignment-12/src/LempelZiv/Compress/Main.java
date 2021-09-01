package LempelZiv.Compress;

import java.io.File;

public class Main {
    private static final int INPUT  = 0;
    private static final int OUTPUT = 1;

    //File paths
    private final static String[][] PATHS = {
            { "files/LempelZiv/oppg12_txt/input.txt",  "files/LempelZiv/oppg12_txt/compressed.lz"  },
            { "files/LempelZiv/oppg12_pdf/input.pdf",  "files/LempelZiv/oppg12_pdf/compressed.lz"  },
            { "files/LempelZiv/diverse_txt/input.txt", "files/LempelZiv/diverse_txt/compressed.lz" },
            { "files/LempelZiv/diverse_pdf/input.pdf", "files/LempelZiv/diverse_pdf/compressed.lz" }
    };

    public static void main(String[] args){
        System.out.printf("%22s%25s%11s%12s%8s\n", "FILE", "STATUS", "INPUT", "COMPRESSED", "DIFF");

        //Compresses each file and prints stats
        for(String[] PATH : PATHS){
            LempelZivKompresjon lempelZiv = new LempelZivKompresjon(PATH[INPUT], PATH[OUTPUT]);
            System.out.printf("%-38s%-15s", PATH[INPUT].toUpperCase(), (lempelZiv.kjorKompresjon() ? " COMPRESSED" : " UNCOMPRESSED"));

            double inputSize      = new File(PATH[INPUT]).length();
            double compressedSize = new File(PATH[OUTPUT]).length();

            System.out.format("%-10.0f%-10.0f%-+1.2f\n", inputSize, compressedSize, ((compressedSize / inputSize) * 100) - 100);
        }
    }
}