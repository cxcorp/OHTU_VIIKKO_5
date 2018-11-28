
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {

    private IntJoukko eka = new IntJoukko();
    private IntJoukko toka = new IntJoukko();

    @Test
    public void testSomething() {
        eka = teeJoukko(1, 2);
        toka = teeJoukko(3, 4);

        IntJoukko tulos = IntJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2, 3, 4};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    @Test
    public void yhdisteEiLisaaDuplikaatteja() {
        eka = teeJoukko(1, 2, 3, 4);
        toka = teeJoukko(4, 5, 6);

        IntJoukko tulosjoukko = IntJoukko.yhdiste(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();
        Arrays.sort(tulos);

        int[] odotettu = {1, 2, 3, 4, 5, 6};

        assertArrayEquals(odotettu, tulos);
    }

    @Test
    public void leikkausToimiiKunJoukoissaEiSamojaAlkioita() {
        eka = teeJoukko(1, 2);
        toka = teeJoukko(3, 4);

        IntJoukko tulosjoukko = IntJoukko.leikkaus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();
        Arrays.sort(tulos);

        int[] odotettu = {};

        assertArrayEquals(odotettu, tulos);
    }

    @Test
    public void leikkausToimiiKunJoukoissaOnJoitainSamojaAlkioita() {
        eka = teeJoukko(5, 2, 3, 1);
        toka = teeJoukko(3, 4, 5, 6);

        IntJoukko tulosjoukko = IntJoukko.leikkaus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();
        Arrays.sort(tulos);

        int[] odotettu = {3, 5};

        assertArrayEquals(odotettu, tulos);
    }

    @Test
    public void leikkausToimiiKunJoukoissaOnVainSamojaAlkioita() {
        eka = teeJoukko(10, 1, 5);
        toka = teeJoukko(5, 10, 1);

        IntJoukko tulosjoukko = IntJoukko.leikkaus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();
        Arrays.sort(tulos);

        int[] odotettu = {1, 5, 10};

        assertArrayEquals(odotettu, tulos);
    }

    @Test
    public void erotusAntaaTyhjastaErotuksestaTyhjan() {
        eka = new IntJoukko();
        toka = new IntJoukko();

        IntJoukko tulosjoukko = IntJoukko.erotus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();

        assertArrayEquals(new int[]{}, tulos);
    }

    @Test
    public void erotusToimiiJosToinenJoukkoOnTyhja() {
        eka = teeJoukko(1, 2, 3);
        toka = new IntJoukko();

        IntJoukko tulosjoukko = IntJoukko.erotus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();

        assertArrayEquals(new int[]{1, 2, 3}, tulos);
    }

    @Test
    public void erotusToimiiJosMolemmissaJoitainSamoja() {
        eka = teeJoukko(3, 100, 10);
        toka = teeJoukko(100, 17, 1);

        IntJoukko tulosjoukko = IntJoukko.erotus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();

        assertArrayEquals(new int[]{3, 10}, tulos);
    }

    @Test
    public void erotusToimiiJosMolemmatJoukotOvatSamoja() {
        eka = teeJoukko(1, 5, 0, 100);
        toka = teeJoukko(100, 1, 0, 5);

        IntJoukko tulosjoukko = IntJoukko.erotus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();

        assertArrayEquals(new int[]{}, tulos);
    }

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();

        for (int luku : luvut) {
            joukko.lisaa(luku);
        }

        return joukko;
    }
}
