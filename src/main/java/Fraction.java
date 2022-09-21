
public class Fraction {

    private int dividend = 1;
    private int devisor = 1;

    public Fraction(int dividend, int devisor) {

        this.devisor = devisor;
        this.dividend = dividend;

    }

    public int getDividend() {
        return dividend;
    }

    public int getDevisor() {
        return devisor;
    }

    public void setDividend(int dividend) {
        this.dividend = dividend;
    }

    public void setDevisor(int devisor) {
        this.devisor = devisor;
    }

    public Fraction add(Fraction other) {
        int a;
        int b;

        if (this.devisor != other.devisor) {
            int x = this.dividend * other.devisor;
            int y = other.dividend * this.devisor;

            a = x + y;
            b = this.devisor * other.devisor;
        } else {
            a = this.dividend + other.dividend;
            b = this.devisor;
        }

        return new Fraction(a, b);
    }


    public Fraction subtract(Fraction other) {
        int a;
        int b;

        if (this.devisor != other.devisor) {
            int x = this.dividend * other.devisor;
            int y = other.dividend * this.devisor;

            a = x - y;
            b = this.devisor * other.devisor;
        } else {
            a = this.dividend - other.dividend;
            b = this.devisor;
        }

        return new Fraction(a, b);
    }

    public Fraction multiply(Fraction other) {
        int a = this.dividend * other.dividend;
        int b = this.devisor * other.devisor;

        return new Fraction(a, b);

    }

    public Fraction divide(Fraction other) {
        int a = this.dividend * other.devisor;
        int b = this.devisor * other.dividend;

        return new Fraction(a, b);
    }

    //auf selben nenner bringen
    //Ergebis neuer Bruch gekürtzt -> new Fraction
    public Fraction shorten() {
        int gcd = 1; //fängt bei 1 an

        //i ist der teiler, prueft so lange bis zur kleinsten zahl vorhanden
        for (int i = 1; i <= this.dividend && i <= this.devisor; i++) {
            //prueft ob der  teiler keinen rest zurueck gibt kein komma
            if (this.dividend % i == 0 && this.devisor % i == 0)
                //teiler wird refresht
                gcd = i;
        }

        //ergebnis durch den neuen teiler
        int a = this.dividend / gcd;
        int b = this.devisor / gcd;

        return new Fraction(a, b);
    }


    @Override
    public java.lang.String toString() {
        return this.dividend + "/" + this.devisor;
    }


}
