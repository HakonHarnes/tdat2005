package LempelZiv.Decompress;

public class Main {
    private static final int INPUT      = 0;
    private static final int OUTPUT     = 1;

    //File paths
    private final static String[][] PATHS = {
            { "files/LempelZiv/oppg12_txt/compressed.lz",  "files/LempelZiv/oppg12_txt/output.txt"  },
            { "files/LempelZiv//oppg12_pdf/compressed.lz", "files/LempelZiv/oppg12_pdf/output.pdf"  },
            { "files/LempelZiv/diverse_txt/compressed.lz", "files/LempelZiv/diverse_txt/output.txt" },
            { "files/LempelZiv/diverse_pdf/compressed.lz", "files/LempelZiv/diverse_pdf/output.pdf" }
    };

    public static void main(String[] args){
        System.out.printf("%24s%30s\n", "FILE", "STATUS");

        for(String[] PATH : PATHS){
            LempelZivDekompresjon lempelZiv = new LempelZivDekompresjon(PATH[INPUT], PATH[OUTPUT]);
            System.out.printf("%-43s%s\n", PATH[INPUT].toUpperCase() , (lempelZiv.kjorDekomprimering() ? "DECOMPRESSED" : " COMPRESSED"));
        }
    }
}