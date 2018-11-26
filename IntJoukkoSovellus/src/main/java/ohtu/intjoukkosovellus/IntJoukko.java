package ohtu.intjoukkosovellus;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
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
        if (!kuuluu(luku)) {
            return false;
        }

        int index = indexOf(luku);
        poistaTaulukosta(index);
        alkioidenMaara--;
        return true;
    }

    private void poistaTaulukosta(int indeksi) {
        // Kopioi taulukon loppuosan indeksin alkion päälle
        // esim:
        // [0, 1, i, 3, 0, 0, 0]
        //        ^-indeksi
        // arraycopy(...)
        // [0, 1, 3, 0, 0, 0, 0]
        //        ^           ^- loppu paddataan nollilla
        //        |-indeksin alkion jälkeinen osa siirtynyt päälle
        System.arraycopy(
            lukutaulukko,
            indeksi + 1,
            lukutaulukko,
            indeksi,
            lukutaulukko.length - 1 - indeksi
        );
    }

    public int mahtavuus() {
        return alkioidenMaara;
    }

    @Override
    public String toString() {
        return String.format("{%s}", lukutaulukkoStringiksi());
    }

    private String lukutaulukkoStringiksi() {
        return Joiner.on(", ").join(Ints.asList(toIntArray()));
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