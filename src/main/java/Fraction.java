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

    @Override
    public java.lang.String toString() {
        return this.dividend + "/" + this.devisor;
    }


}
