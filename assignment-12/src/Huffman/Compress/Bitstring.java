package Huffman.Compress;

class Bitstring {
    private long val;
    private int bitCount;

    Bitstring(long val, int bitCount){
        this.val = val;
        this.bitCount = bitCount;
    }

    long getVal(){ return val; }
    int getBitCount() { return bitCount; }
}