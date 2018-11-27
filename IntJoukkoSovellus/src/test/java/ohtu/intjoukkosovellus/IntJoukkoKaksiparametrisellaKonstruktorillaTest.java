
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoKaksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {

    @Before
    public void setUp() {
        joukko = new IntJoukko(4, 2);
        joukko.lisaa(10);
        joukko.lisaa(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void konstruktoriHeittaaVirheenJosKapasiteettiOnNegatiivinen() {
        new IntJoukko(-1, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void konstruktoriHeittaaVirheenJosKasvatuskokoOnNegatiivinen() {
        new IntJoukko(4, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void konstruktoriHeittaaVirheenJosKapasiteettiJaKasvatuskokoOnNegatiivinen() {
        new IntJoukko(-1, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void konstruktoriHeittaaVirheenJosKasvatuskokoOnAlleYksi() {
        new IntJoukko(5, 0);
    }
}
