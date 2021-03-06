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

    public int mahtavuus() {
        return alkioidenMaara;
    }

    @Override
    public String toString() {
        return String.format("{%s}", lukutaulukkoStringiksi());
    }

    public int[] toIntArray() {
        return Arrays.copyOf(lukutaulukko, alkioidenMaara);
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        lisaaKaikki(uusiJoukko, a.toIntArray());
        lisaaKaikki(uusiJoukko, b.toIntArray());
        return uusiJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        lisaaMolemmissaOlevat(a, b, uusiJoukko);
        return uusiJoukko;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = yhdiste(a, b);
        poistaKaikki(uusiJoukko, b.toIntArray());
        return uusiJoukko;
    }

    private static void poistaKaikki(IntJoukko joukko, int[] luvut) {
        for (int luku : luvut) {
            joukko.poista(luku);
        }
    }

    private static void lisaaMolemmissaOlevat(IntJoukko a, IntJoukko b, IntJoukko kohde) {
        for (int luku : a.toIntArray()) {
            if (b.kuuluu(luku)) {
                kohde.lisaa(luku);
            }
        }
    }

    private static void lisaaKaikki(IntJoukko joukko, int[] luvut) {
        for (int luku : luvut) {
            joukko.lisaa(luku);
        }
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

    private String lukutaulukkoStringiksi() {
        return Joiner.on(", ").join(Ints.asList(toIntArray()));
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

    private int indexOf(int etsittavaLuku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (lukutaulukko[i] == etsittavaLuku) {
                return i;
            }
        }
        return -1;
    }
}