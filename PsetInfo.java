public class PsetInfo {
    private int psetNumber;          // which pset
    private double psetEarned;          // points earned
    private double psetPossible;        // points possible

    public PsetInfo (int psetNumber, double psetEarned, double psetPossible) {
        this.psetNumber = psetNumber;
        this.psetEarned = psetEarned;
        this.psetPossible = psetPossible;
    }

    public int getPsetNumber() {
        return psetNumber;
    }

    public double getPsetEarned() {
        return psetEarned;
    }

    public double getPsetPossible() {
        return psetPossible;
    }


    public static void main(String[] args) {

    }
}
