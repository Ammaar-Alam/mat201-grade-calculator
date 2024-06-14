import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.Map;

public class GradeCalculator {

    private static Map<Integer, LectureInfo> lectures = new HashMap<>();  // hashmap of all indexed lectures
    private static Map<Integer, PsetInfo> psets = new HashMap<>();        // hashmap of all indexed PSETs
    private static double totalPossiblePoints = 0;  // sum of all points up to the current date
    private static double totalEarnedPoints = 0;    // sum of all points earned up to current date
    private static double inClassGrade = 0;
    private static double totalEarnedPSET = 0;
    private static double totalPossiblePSET = 0;
    private static double psetGrade = 0;
    private static double totalEarnedMidterm = 0;
    private static double totalPossibleMidterm = 160; // two midterms, each 80 points
    private static double midtermGrade = 0;
    private static double finalGrade = 0;

    public static void main(String[] args) {
        initializeLectures();
        initializePSETS();
        initializeGrades();
        calculateOverallGrade();

        StdOut.println("Current In-Class Work Grade: " + inClassGrade + "%");
        StdOut.println("Current PSET Grade: " + psetGrade + "%");
        StdOut.println("Current Midterms Grade: " + midtermGrade + "%");
        StdOut.println("Current Overall Class Grade: " + finalGrade + "%");

        In in = new In();
        StdOut.println("Please select which category you would like to update:");
        StdOut.println("1: In-Class Grade\n2: PSET Grade\n3: Midterm Grade");
        String choice = in.readString();
        if (choice.equals("1")) runDialogueINCLASS();
        else if (choice.equals("2")) runDialoguePSET();
        else if (choice.equals("3")) runDialogueMIDTERM();

        savePSETs();
        saveLectures();
        saveGrades();
        calculateOverallGrade();

        StdOut.println("Updated Overall Class Grade: " + finalGrade + "%");
    }

    private static void runDialogueINCLASS() {
        In in = new In();
        StdOut.print("Lecture Number: ");
        int lectureNumber = in.readInt();
        while (lectures.containsKey(lectureNumber)) {
            LectureInfo li = lectures.get(lectureNumber);
            StdOut.println("\nLECTURE ENTERED: " + li.getDate() + ", " + li.getDayOfWeek());
            StdOut.print("Points earned for the day: ");
            int dailyEarnedPoints = in.readInt();
            li.updateDailyEarnedPoints(dailyEarnedPoints);
            updateTotals(dailyEarnedPoints, li.getPossiblePoints());
            inClassGrade = (totalEarnedPoints / totalPossiblePoints) * 100;
            StdOut.println("\nUpdated In-Class Work Grade: " + inClassGrade + "%");
            StdOut.print("Enter another day? (y or n): ");
            if (in.readString().equalsIgnoreCase("y")) {
                StdOut.println("-------------\n\n");
                StdOut.print("Lecture Number: ");
                lectureNumber = in.readInt();
            } else {
                break;
            }
        }
        calculateOverallGrade();
    }

    private static void runDialoguePSET() {
        In in = new In();
        StdOut.print("PSET Number: ");
        int psetNumber = in.readInt();
        while (psets.containsKey(psetNumber)) {
            PsetInfo ps = psets.get(psetNumber);
            StdOut.print("Points earned on PSET: ");
            double newEarnedPoints = in.readDouble();

            totalEarnedPSET = totalEarnedPSET - ps.getPsetEarned() + newEarnedPoints;
            ps.setPsetEarned(newEarnedPoints);

            psetGrade = (totalEarnedPSET / totalPossiblePSET) * 100;
            StdOut.println("Updated PSET Grade: " + psetGrade + "%");
            StdOut.print("Enter another PSET? (y or n): ");
            if (in.readString().equalsIgnoreCase("y")) {
                StdOut.println("-------------\n\n");
                StdOut.print("PSET Number: ");
                psetNumber = in.readInt();
            } else {
                break;
            }
        }
        calculateOverallGrade();
    }

    private static void runDialogueMIDTERM() {
        In in = new In();
        StdOut.print("Points earned on Midterm: ");
        double newEarnedPoints = in.readDouble();

        totalEarnedMidterm += newEarnedPoints;
        midtermGrade = (totalEarnedMidterm / totalPossibleMidterm) * 100;
        StdOut.println("Updated Midterm Grade: " + midtermGrade + "%");
        calculateOverallGrade();
    }

    private static void updateTotals(int dailyEarnedPoints, int possiblePoints) {
        totalEarnedPoints += dailyEarnedPoints;
        totalPossiblePoints += possiblePoints;
    }

    private static void saveGrades() {
        Out out = new Out("grades.csv");
        out.println("totalEarnedPoints,totalPossiblePoints,inClassGrade");
        out.println(totalEarnedPoints + " " + totalPossiblePoints + " " + inClassGrade);
        out.println("totalEarnedPSET,totalPossiblePSET,psetGrade");
        out.println(totalEarnedPSET + " " + totalPossiblePSET + " " + psetGrade);
        out.println("midtermEarned,midtermPossible,midtermGrade");
        out.println(totalEarnedMidterm + " " + totalPossibleMidterm + " " + midtermGrade);
        out.close();
    }

    private static void savePSETs() {
        Out out = new Out("psets.csv");
        out.println("psetEarned,psetPossible");
        for (Map.Entry<Integer, PsetInfo> entry : psets.entrySet()) {
            PsetInfo ps = entry.getValue();
            out.println(ps.getPsetEarned() + " " + ps.getPsetPossible());
        }
        out.close();
    }

    private static void saveLectures() {
        Out out = new Out("lectures.csv");
        out.println("LectureNumber,date,dayOfWeek,possiblePoints,earnedPoints");
        for (Map.Entry<Integer, LectureInfo> entry : lectures.entrySet()) {
            int lectureNumber = entry.getKey();
            LectureInfo li = entry.getValue();
            out.printf("%d,%s,%s,%d,%d\n", lectureNumber, li.getDate(), li.getDayOfWeek(), li.getPossiblePoints(), li.getEarnedPoints());
        }
        out.close();
    }

    private static void initializeLectures() {
        In in = new In("lectures.csv");
        in.readLine(); // skipping header
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            int lectureNumber = Integer.parseInt(fields[0]);
            lectures.put(lectureNumber, new LectureInfo(fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4])));
        }
    }

    private static void initializePSETS() {
        In in = new In("psets.csv");
        in.readLine(); // skipping header
        int psetNumber = 1;
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(" ");
            double earnedPoints = Double.parseDouble(fields[0]);
            double possiblePoints = Double.parseDouble(fields[1]);
            psets.put(psetNumber++, new PsetInfo(earnedPoints, possiblePoints));
        }
    }

    private static void initializeGrades() {
        In in = new In("grades.csv");
        in.readLine(); // skip in-class grades header
        String line = in.readLine();
        String[] fields = line.split(" ");
        totalEarnedPoints = Double.parseDouble(fields[0]);
        totalPossiblePoints = Double.parseDouble(fields[1]);
        inClassGrade = Double.parseDouble(fields[2]);

        in.readLine(); // skip pset grades header
        line = in.readLine();
        fields = line.split(" ");
        totalEarnedPSET = Double.parseDouble(fields[0]);
        totalPossiblePSET = Double.parseDouble(fields[1]);
        psetGrade = Double.parseDouble(fields[2]);

        in.readLine(); // skip midterm grades header
        line = in.readLine();
        fields = line.split(" ");
        totalEarnedMidterm = Double.parseDouble(fields[0]);
        totalPossibleMidterm = Double.parseDouble(fields[1]);
        midtermGrade = Double.parseDouble(fields[2]);

        calculateOverallGrade();
    }

    private static void calculateOverallGrade() {
        double weightPSET = 0.2;
        double weightInClass = 0.2;
        double weightMidterm = 0.3;
        double weightFinal = 0.3;

        double earnedPercentage = (weightInClass * inClassGrade) + (weightPSET * psetGrade) + (weightMidterm * midtermGrade);
        double totalWeight = weightPSET + weightInClass + weightMidterm; // no final weight yet

        finalGrade = (earnedPercentage / totalWeight) * 100; // normalized grade without final
    }
}
