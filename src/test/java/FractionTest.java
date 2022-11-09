import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    private Connection connect(String db) {
        Connection con = null;

        try {
            if (db.length() > 0) {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            } else {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            }
        } catch (SQLException s) {
            System.out.println("SQLException" + s.getMessage());
        }
        return con;
    }

    //ob damit verbinden kann
    @Test
    @Order(1)
    void connectToDb() {
        /*
        Assertions.assertDoesNotThrow(new ThrowingSupplier<Object>() {
            @Override
            public Object get() throws Throwable {
                return connect("");
            }
        });
        Connection c = connect("");
        */

        Assertions.assertDoesNotThrow(() -> connect(""));
    }

    //ob datenbank anlegen können
    @Test
    @Order(2)
    void createDb() {

        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("");//mit keiner datenbank verbinden

            Statement s = c.createStatement();
            s.executeUpdate("CREATE DATABASE testdb");

            s.close();
            c.close();
        });
    }

    @Test
    void getDividend() {
        Fraction f = new Fraction(1, 10);
        Assertions.assertEquals(1, f.getDividend());
    }

    @Test
    void getDevisor() {
        Fraction f = new Fraction(1, 10);
        f.setDevisor(2);
        Assertions.assertEquals(2, f.getDevisor());

    }

    @Test
    void setDividend() {

        Fraction f = new Fraction(1, 10);
        f.setDividend(2);
        Assertions.assertEquals(2, f.getDividend());
    }

    @Test
    void setDevisor() {
        Fraction f = new Fraction(1, 10);
        f.setDevisor(20);
        Assertions.assertEquals(20, f.getDevisor());
    }

    @Test
    void testToString() {
        Fraction f = new Fraction(17, 13);
        Assertions.assertEquals("17/13", f.toString());
    }

    @Test
    void add() {
        Fraction f1 = new Fraction(2, 4);
        Fraction f2 = new Fraction(2, 2);

        Fraction result = f1.add(f2);

        Assertions.assertEquals(8, result.getDevisor());
        Assertions.assertEquals(12, result.getDividend());
    }

    @Test
    void shorten() {

    }

    @Test
    void subtract() {
        Fraction f1 = new Fraction(4, 2);
        Fraction f2 = new Fraction(2, 4);

        Fraction result = f1.subtract(f2);

        Assertions.assertEquals(8, result.getDevisor());
        Assertions.assertEquals(12, result.getDividend());
    }

    @Test
    void multiply() {
        Fraction f1 = new Fraction(4, 2);
        Fraction f2 = new Fraction(2, 4);

        Fraction result = f1.multiply(f2);

        Assertions.assertEquals(8, result.getDevisor());
        Assertions.assertEquals(8, result.getDividend());
    }

    @Test
    void divide() {

        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(4, 2);

        Fraction result = f1.divide(f2);
        result.shorten();

        Assertions.assertEquals(8, result.getDevisor());
        Assertions.assertEquals(2, result.getDividend());
    }


    @Test
    void testShorten() {

        Fraction f2 = new Fraction(35, 80);
        Fraction result = f2.shorten();

        Assertions.assertEquals(16, result.getDevisor());
        Assertions.assertEquals(7, result.getDividend());

        Assertions.assertEquals("7/16", result.toString());
    }


    @Test
    @Order(1)
    void workflow() {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:8080/Fraction?useSSL=false",
                    "root", "root");
            //Connection c = DbInstance.getInstance();
            Assertions.assertNotNull(c);

            PreparedStatement createdb = c.prepareStatement("CREATE DATABASE dbFraction");
            Assertions.assertNotNull(createdb.execute());
            c.close();

            c = DriverManager.getConnection("jdbc:mysql://localhost:8080/Fraction?useSSL=false", "root",
                    "root");
            Assertions.assertNotNull(c);

            PreparedStatement createtable = c.prepareStatement("CREATE TABLE dbFraction.Number (number INT)");
            Assertions.assertNotNull(createtable.execute());

            PreparedStatement insertnum = c.prepareStatement("INSERT INTO dbFraction.Number (number) values" +
                    "(1)");
            Assertions.assertNotNull(insertnum.execute());

            PreparedStatement show = c.prepareStatement("SELECT * FROM dbFraction.Number");
            Assertions.assertNotNull(show.execute());
            System.out.println(show);

        } catch (SQLException sqlException) {

        }
    }
}