
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {

    @Test
    public void testSomething() {
        IntJoukko eka = teeJoukko(1, 2);
        IntJoukko toka = teeJoukko(3, 4);

        IntJoukko tulos = IntJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2, 3, 4};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    @Test
    public void yhdisteEiLisaaDuplikaatteja() {
        int[] ekat = {1, 2, 3, 4};
        int[] tokat = {4, 5, 6};
        IntJoukko eka = teeJoukko(ekat);
        IntJoukko toka = teeJoukko(tokat);

        IntJoukko tulosjoukko = IntJoukko.yhdiste(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();
        Arrays.sort(tulos);

        int[] odotettu = {1, 2, 3, 4, 5, 6};

        assertArrayEquals(odotettu, tulos);
    }

    @Test
    public void leikkausToimiiKunJoukoissaEiSamojaAlkioita() {
        IntJoukko eka = teeJoukko(1, 2);
        IntJoukko toka = teeJoukko(3, 4);

        IntJoukko tulosjoukko = IntJoukko.leikkaus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();
        Arrays.sort(tulos);

        int[] odotettu = {};

        assertArrayEquals(odotettu, tulos);
    }

    @Test
    public void leikkausToimiiKunJoukoissaOnJoitainSamojaAlkioita() {
        IntJoukko eka = teeJoukko(5, 2, 3, 1);
        IntJoukko toka = teeJoukko(3, 4, 5, 6);

        IntJoukko tulosjoukko = IntJoukko.leikkaus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();
        Arrays.sort(tulos);

        int[] odotettu = {3, 5};

        assertArrayEquals(odotettu, tulos);
    }

    @Test
    public void leikkausToimiiKunJoukoissaOnVainSamojaAlkioita() {
        IntJoukko eka = teeJoukko(10, 1, 5);
        IntJoukko toka = teeJoukko(5, 10, 1);

        IntJoukko tulosjoukko = IntJoukko.leikkaus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();
        Arrays.sort(tulos);

        int[] odotettu = {1, 5, 10};

        assertArrayEquals(odotettu, tulos);
    }

    @Test
    public void erotusAntaaTyhjastaErotuksestaTyhjan() {
        IntJoukko eka = new IntJoukko();
        IntJoukko toka = new IntJoukko();

        IntJoukko tulosjoukko = IntJoukko.erotus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();

        assertArrayEquals(new int[]{}, tulos);
    }

    @Test
    public void erotusToimiiJosToinenJoukkoOnTyhja() {
        IntJoukko eka = teeJoukko(1, 2, 3);
        IntJoukko toka = new IntJoukko();

        IntJoukko tulosjoukko = IntJoukko.erotus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();

        assertArrayEquals(new int[]{1, 2, 3}, tulos);
    }

    @Test
    public void erotusToimiiJosMolemmissaJoitainSamoja() {
        IntJoukko eka = teeJoukko(3, 100, 10);
        IntJoukko toka = teeJoukko(100, 17, 1);

        IntJoukko tulosjoukko = IntJoukko.erotus(eka, toka);
        int[] tulos = tulosjoukko.toIntArray();

        assertArrayEquals(new int[]{3, 10}, tulos);
    }

    @Test
    public void erotusToimiiJosMolemmatJoukotOvatSamoja() {
        IntJoukko eka = teeJoukko(1, 5, 0, 100);
        IntJoukko toka = teeJoukko(100, 1, 0, 5);

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
