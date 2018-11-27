
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

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();

        for (int luku : luvut) {
            joukko.lisaa(luku);
        }

        return joukko;
    }
}
