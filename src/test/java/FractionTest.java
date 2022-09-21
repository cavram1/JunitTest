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

    @Test
    void add() {
        Fraction f1 = new Fraction(2,4);
        Fraction f2 = new Fraction(2,2);

        Fraction result = f1.add(f2);

        Assertions.assertEquals(8, result.getDevisor());
        Assertions.assertEquals(12,result.getDividend());
    }

    @Test
    void shorten() {

    }

    @Test
    void subtract() {
        Fraction f1 = new Fraction(4,2);
        Fraction f2 = new Fraction(2,4);

        Fraction result = f1.subtract(f2);

        Assertions.assertEquals(8, result.getDevisor());
        Assertions.assertEquals(12,result.getDividend());
    }

    @Test
    void multiply() {
        Fraction f1 = new Fraction(4,2);
        Fraction f2 = new Fraction(2,4);

        Fraction result = f1.multiply(f2);

        Assertions.assertEquals(8, result.getDevisor());
        Assertions.assertEquals(8,result.getDividend());
    }

    @Test
    void divide() {

        Fraction f1 = new Fraction(1,2);
        Fraction f2 = new Fraction(4,2);

        Fraction result = f1.divide(f2);
        result.shorten();

        Assertions.assertEquals(8, result.getDevisor());
        Assertions.assertEquals(2,result.getDividend());
    }


    @Test
    void testShorten() {

        Fraction f2 = new Fraction(35,80);
        Fraction result = f2.shorten();

        Assertions.assertEquals(16, result.getDevisor());
        Assertions.assertEquals(7,result.getDividend());

        Assertions.assertEquals("7/16", result.toString());
    }
}