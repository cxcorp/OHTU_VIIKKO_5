package ohtu.intjoukkosovellus;

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
        int eiOle = 0;
        if (alkioidenMaara == 0) {
            lukutaulukko[0] = luku;
            alkioidenMaara++;
            return true;
        } else {
        }
        if (!kuuluu(luku)) {
            lukutaulukko[alkioidenMaara] = luku;
            alkioidenMaara++;
            if (alkioidenMaara % lukutaulukko.length == 0) {
                int[] taulukkoOld = new int[lukutaulukko.length];
                taulukkoOld = lukutaulukko;
                kopioiTaulukko(lukutaulukko, taulukkoOld);
                lukutaulukko = new int[alkioidenMaara + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, lukutaulukko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        int on = 0;
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == lukutaulukko[i]) {
                on++;
            }
        }
        if (on > 0) {
            return true;
        } else {
            return false;
        }
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

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
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
}