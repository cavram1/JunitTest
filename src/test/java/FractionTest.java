import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void getDividend() {
        Fraction f = new Fraction(1,10);
        Assertions.assertEquals(1, f.getDividend());
    }

    @Test
    void getDevisor() {
        Fraction f = new Fraction(1,10);
        f.setDevisor(2);
        Assertions.assertEquals(2, f.getDevisor());

    }

    @Test
    void setDividend() {

        Fraction f = new Fraction(1,10);
        f.setDividend(2);
        Assertions.assertEquals(2, f.getDividend());
    }

    @Test
    void setDevisor() {
        Fraction f = new Fraction(1,10);
        f.setDevisor(20);
        Assertions.assertEquals(20, f.getDevisor());
    }

    @Test
    void testToString() {
        Fraction f = new Fraction(17,13);
        Assertions.assertEquals("17/13", f.toString());
    }
}