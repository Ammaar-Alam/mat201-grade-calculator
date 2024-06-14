public class PsetInfo {
    private double psetEarned;          // points earned
    private double psetPossible;        // points possible

    public PsetInfo (int psetNumber, double psetEarned, double psetPossible) {
        this.psetEarned = psetEarned;
        this.psetPossible = psetPossible;
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
