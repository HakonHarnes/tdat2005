import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class Data {
    public static void quicksort(int[] t, int v, int h){
        if(h - v > 2){
            int partingpos = split(t, v, h);
            quicksort(t, v, partingpos - 1);
            quicksort(t, partingpos + 1, h);
        } else {
            median3sort(t, v, h);
        }
    }

    public static void quicksortImproved(int[] t, int v, int h){
        if (v > 0 && h < t .length - 1 && t [v - 1] == t [h + 1]) return;

        if(h - v > 2){
            int partingpos = split(t, v, h);
            quicksortImproved(t, v, partingpos - 1);
            quicksortImproved(t, partingpos + 1, h);
        } else {
            median3sort(t, v, h);
        }
    }

    private static int split(int[] t, int v, int h){
        int iv, ih;
        int m = median3sort(t, v, h);
        int dv = t[m];

        swap(t, m, h - 1);

        for(iv = v, ih = h - 1;;){
            while(t[++iv] < dv);
            while(t[--ih] > dv);

            if(iv >= ih) break;

            swap(t, iv, ih);
        }

        swap(t, iv, h - 1);
        return iv;
    }

    private static void swap(int[] t, int i, int j){
        int temp = t[j];
        t[j] = t[i];
        t[i] = temp;
    }

    private static int median3sort(int[] t, int v, int h){
        int m = (v + h) / 2;

        if(t[v] > t[m]) swap(t, v, m);
        if(t[m] > t[h]){
            swap(t, m, h);
            if(t[v] > t[m]) swap(t, v, m);
        }

        return m;
    }

    private static int[] generateUniqueData(int n){
        ArrayList<Integer> arrayList = new ArrayList<>(n);
        int[] data = new int[n];

        for(int i = 0; i < n; i++)
            arrayList.add(i);

        Collections.shuffle(arrayList);

        for(int i = 0; i < n; i++)
            data[i] = arrayList.get(i);

        return data;
    }

    private static int[] generateData(int n){
        final int MIN_VAL = 0;
        final int MAX_VAL = (n > 1000) ? n/100 : n/10;

        int[] data = new int[n];

        Random random = new Random();

        for(int i = 0; i < n; i++)
            data[i] = random.nextInt(MAX_VAL - MIN_VAL + 1) + MIN_VAL;

        return data;
    }

    private static boolean checkOrder(int[] data){
        for(int i = 0; i < data.length - 1; i++){
            if(data[i] > data[i + 1]){
                return false;
            }
        }

        return true;
    }

    private static int getChecksum(int[] data){
        int checksum = 0;

        for(int n : data)
            checksum += n;

        return checksum;
    }

    private static void testAlgorithm(boolean UNIQUE_DATA, boolean IMPROVED, int DATASIZE){
        System.out.println("-- VERSION: " + (IMPROVED ? "IMPROVED" : "NORMAL") + ", DATA: " + (UNIQUE_DATA ? "UNIQUE" : "RANDOM") + ", DATASIZE: " + DATASIZE + " --");

        int[] data = UNIQUE_DATA ? generateUniqueData(DATASIZE) : generateData(DATASIZE);

        for(int i = 0; i < 2; i++){
            int checksum = getChecksum(data);

            Date start = new Date();
            Date stop;

            int rounds = 0;

            do {
                if (IMPROVED) {
                    Data.quicksortImproved(data, 0, data.length - 1);
                } else {
                    Data.quicksort(data, 0, data.length - 1);
                }

                stop = new Date();
                rounds++;
            } while (stop.getTime() - start.getTime() < 1000);

            double time = (double) (stop.getTime() - start.getTime()) / rounds;

            System.out.format("time: %.4f, ", time);
            System.out.print("TEST 1 " + (checkOrder(data) ? "OK" : "NOT OK") + ", ");
            System.out.println("TEST 2 " + (getChecksum(data) == checksum ? "OK" : "NOT OK"));
        }

        System.out.println();
    }

    public static void main(String[] args){
        testAlgorithm(false, false, 1000000);
        testAlgorithm(false, true, 1000000);

        testAlgorithm(true, false, 1000000);
        testAlgorithm(true, true, 1000000);

        testAlgorithm(false, false, 100000);
        testAlgorithm(false, true, 100000);

        testAlgorithm(false, false, 10000);
        testAlgorithm(false, true, 10000);

        testAlgorithm(false, false, 1000);
        testAlgorithm(false, true, 1000);

        testAlgorithm(false, false, 100);
        testAlgorithm(false, true, 100);
    }
}