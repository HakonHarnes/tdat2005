package LempelZiv.Compress;

import java.util.ArrayList;

public class LempelZivKompresjon {
    private final int MIN_LENGDE = 3;
    private String filInn;
    private String filUt;

    public LempelZivKompresjon(String filInn, String filUt) {
        this.filInn = filInn;
        this.filUt = filUt;
    }

    public byte[] komprimer(byte[] data) {
        ArrayList<Byte> komprimert = new ArrayList<Byte>();

        byte uendret = MIN_LENGDE;
        int i;
        for (i = MIN_LENGDE; i < data.length; i++) {
            boolean funnet = false;
            short avstand = 0;
            byte lengdeFunnet = 0;
            for (short d = 3; d <= i && d < Short.MAX_VALUE; d++) {
                if (data[i - d] == data[i]) {
                    int funn;
                    for (funn = 1; funn < d && funn < Byte.MAX_VALUE && i + funn < data.length;) {	// Poor search algorithm.
                        if (data[i + funn] == data[i - d + funn]) {funn++;}
                        else {break;}
                    }
                    if (funn > MIN_LENGDE && funn > lengdeFunnet) {
                        funnet = true;
                        lengdeFunnet = (byte)funn;
                        avstand = d;
                        if (lengdeFunnet == Byte.MAX_VALUE) {break;}
                    }
                }
            }
            if (funnet) {
                if (uendret != 0) {
                    komprimert.add(uendret);
                    for (int j = i - uendret; j < i; j++) {komprimert.add(data[j]);}
                    uendret = 0;
                }
                komprimert.add((byte)(-lengdeFunnet));	// MIN_LENGDE.
                komprimert.add((byte)(avstand & 0xff));
                komprimert.add((byte)((avstand >> 8) & 0xff));

                i += ((int)(lengdeFunnet) - 1);
            }
            else {uendret++;}
            if (uendret == Byte.MAX_VALUE) {
                komprimert.add(uendret);
                for (int j = i - uendret + 1; j <= i; j++) {komprimert.add(data[j]);}
                uendret = 0;
            }
        }
        komprimert.add(uendret);
        for (int j = i - uendret; j < i; j++) {komprimert.add(data[j]);}
        return FilAdmin.listeTilArray(komprimert);
    }

    public boolean kjorKompresjon() {
        byte[] lestData = FilAdmin.lesBytesFraFil(this.filInn);
        byte[] komprimert = komprimer(lestData);
        FilAdmin.skrivBytestilFil(komprimert, this.filUt);

        return true;
    }
}