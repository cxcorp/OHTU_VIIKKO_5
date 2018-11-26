package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    private final static int OLETUSKAPASITEETTI = 5; // aloitustalukon koko
    private final static int OLETUSKASVATUSKOKO = 5;  // luotava uusi taulukko on
    // näin paljon isompi kuin vanha

    private final int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukutaulukko;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenMaara;    // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        this(OLETUSKAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUSKOKO);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko < 1) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei voi olla negatiivinen");
        }

        this.lukutaulukko = new int[kapasiteetti];
        this.alkioidenMaara = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }

        if (lukutaulukkoOnTaynna()) {
            kasvataLukutaulukkoa();
        }

        lisaaLukutaulukonPeralle(luku);
        return true;
    }

    private void lisaaLukutaulukonPeralle(int luku) {
        lukutaulukko[alkioidenMaara] = luku;
        alkioidenMaara++;
    }

    private boolean lukutaulukkoOnTaynna() {
        return alkioidenMaara == lukutaulukko.length;
    }

    private void kasvataLukutaulukkoa() {
        int uusiKoko = laskeKasvatettuKoko();
        lukutaulukko = Arrays.copyOf(lukutaulukko, uusiKoko);
    }

    private int laskeKasvatettuKoko() {
        return alkioidenMaara + kasvatuskoko;
    }

    public boolean kuuluu(int etsittavaLuku) {
        return indexOf(etsittavaLuku) != -1;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == lukutaulukko[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                lukutaulukko[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenMaara - 1; j++) {
                apu = lukutaulukko[j];
                lukutaulukko[j] = lukutaulukko[j + 1];
                lukutaulukko[j + 1] = apu;
            }
            alkioidenMaara--;
            return true;
        }


        return false;
    }

    public int mahtavuus() {
        return alkioidenMaara;
    }

    @Override
    public String toString() {
        if (alkioidenMaara == 0) {
            return "{}";
        } else if (alkioidenMaara == 1) {
            return "{" + lukutaulukko[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenMaara - 1; i++) {
                tuotos += lukutaulukko[i];
                tuotos += ", ";
            }
            tuotos += lukutaulukko[alkioidenMaara - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenMaara];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukutaulukko[i];
        }
        return taulu;
    }


    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }

        return z;
    }

    private int indexOf(int etsittavaLuku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (lukutaulukko[i] == etsittavaLuku) {
                return i;
            }
        }
        return -1;
    }
}