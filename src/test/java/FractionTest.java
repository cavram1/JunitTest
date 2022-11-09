import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FractionTest {

    private Connection connect(String db) throws SQLException {
        Connection con = null;

            if (db.length() > 0) {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            } else {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
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
    @Order(3)
    void connectToSpecificDb() {
        Assertions.assertDoesNotThrow(() -> connect("testdb"));
    }

    @Test
    @Order(4)
    void creatTable() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");//mit keiner datenbank verbinden

            Statement s = c.createStatement();
            s.executeUpdate("CREATE TABLE testtable (mycolumn VARCHAR(255) NULL)");

            s.close();
            c.close();
        });
    }

    @Test
    @Order(5)
    void insertIntoTable() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");//mit keiner datenbank verbinden

            Statement s = c.createStatement();
            s.executeUpdate("INSERT INTO testtable (mycolumn) VALUES ('some text')");

            s.close();
            c.close();
        });
    }

    @Test
    @Order(6)
    void selectFromTable() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");//mit keiner datenbank verbinden

            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM testtable LIMIT 1");

            if(r.next()){
                Assertions.assertEquals(r.getString("myColumn"), "some text");
            }

            s.close();
            c.close();
        });
    }

    @Test
    @Order(7)
    void deleteEntry() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");//mit keiner datenbank verbinden

            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM testtable");


            s.close();
            c.close();
        });
    }

    @Test
    @Order(8)
    void deleteTable() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");//mit keiner datenbank verbinden

            Statement s = c.createStatement();
            s.executeUpdate("DROP TABLE testtable");

            s.close();
            c.close();
        });
    }

    @Test
    @Order(9)
    void deleteDb() {
        Assertions.assertDoesNotThrow(() -> {
            Connection c = connect("testdb");//mit keiner datenbank verbinden

            Statement s = c.createStatement();
            s.executeUpdate("DROP DATABASE testdb");

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

}