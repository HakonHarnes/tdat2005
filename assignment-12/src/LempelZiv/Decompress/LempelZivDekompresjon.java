package LempelZiv.Decompress;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class LempelZivDekompresjon {

    private final int REFERENCE_BYTES = 3;
    private String filInn;
    private String filUt;

    public LempelZivDekompresjon(String filInn, String filUt) {
        this.filInn = filInn;
        this.filUt = filUt;
    }

    public boolean kjorDekomprimering() {
        byte[] lesteBytes = readBytesFromFile();
        writeDataToFile(decompress(lesteBytes));

        return true;
    }

    public byte[] decompress(byte[] data) {
        ArrayList<Byte> out = new ArrayList<Byte>();
        for (int i = 0, iOut = 0; i < data.length; i++) {
            byte x = data[i];
            if (x < 0) {
                //System.out.println(i + " D1: " + (data[i + 1] & 0xff) + " D2: " + (data[i + 2] & 0xff) + ", " + (data[i + 2] << 8) + " Result: " + (data[i + 1] | (data[i + 2] << 8)));
                short distance = (short)((data[i + 1] & 0xff) | ((data[i + 2] & 0xff) << 8));
                int start = iOut;
                //System.out.println("Start: " + (start - distance) + " Distance: " + distance + " iOut: " + iOut + " X: " + x);
                for (int j = start - distance; j < start - distance - x; j++) {
                    out.add(out.get(j));
                    iOut++;
                }
                i += (REFERENCE_BYTES - 1);
            }
            else {
                //System.out.println(i + " Add: " + (i + 1) + " <= " + (i + x));
                for (int j = i + 1; j <= i + x && j < data.length; j++) {
                    out.add(data[j]);
                    iOut++;
                }
                i += (int)x;
            }
        }
        return listToArray(out);
    }

    private byte[] readBytesFromFile() {
        try {
            return Files.readAllBytes(Paths.get(this.filInn));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private void writeDataToFile(byte[] output) {
        try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.filUt)))){
            dos.write(output);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private byte[] listToArray(ArrayList<Byte> out) {
        int length = out.size();
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {result[i] = out.get(i);}
        return result;
    }
}