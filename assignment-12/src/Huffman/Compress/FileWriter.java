package Huffman.Compress;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

class FileWriter {
    private String filename;

    private int byteCount = 0;
    private int bitCount  = 0;

    FileWriter(String filename){
        this.filename = filename;
    }

    //Writes the frequencies to file
    void write(int[] frequencies){
        try (DataOutputStream file = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            for(int frequency : frequencies)
                file.writeInt(frequency);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Writes the compressed data to file
    void write(Bitstring[] output){
        byte[] data = getByteArray(output);

        try (DataOutputStream file = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename, true)))){
            file.write(data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Returns an array of bytes
    private byte[] getByteArray(Bitstring[] output){
        //Finds the array size
        int bits = 0;
        for(Bitstring b : output)
            bits += b.getBitCount();

        //Initializes the array
        byte[] data = new byte[(bits / 8) + 1];

        for(Bitstring b : output){
            int remainingBits = b.getBitCount();

            while(remainingBits > 0){
                int blockSize = Math.min(8 - bitCount, remainingBits);

                //Masks the bits to be written to file
                int mask = ((int) Math.pow(2, blockSize) - 1) << (remainingBits - blockSize);
                long val = (b.getVal() & mask);

                //Shifts the bits into the correct bit-index
                int shift = (8 - remainingBits - bitCount);
                if(shift >= 0)
                    data[byteCount] |= (val << shift);
                else
                    data[byteCount] |= (val >> -shift);

                //Checks if the current byte is full
                if((bitCount += blockSize) == 8){
                    bitCount = 0;
                    byteCount++;
                }

                //Calculates the remaining bits to be written in the next byte
                remainingBits -= blockSize;
            }
        }

        return data;
    }
}