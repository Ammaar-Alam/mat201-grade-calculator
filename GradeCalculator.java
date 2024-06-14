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
    private static double midtermGrade = 0;

    public static void main(String[] args) {
        initializeLectures();
        initializePSETS();
        initializeGrades();
        StdOut.println("Current In-Class Work Grade: " + inClassGrade + "%");
        StdOut.println("Current PSET Grade: " + psetGrade + "%");
        StdOut.println("Current Midterms Grade: " + midtermGrade + "%");

        In in = new In();
        StdOut.println("Please select which category you would like to update:");
        StdOut.println("1: In-Class Grade\n2: PSET Grade\n3: Midterm Grade");
        String choice = in.readString();
        if (choice.equals("1")) runDialogueINCLASS();
        else if (choice.equals("2")) runDialoguePSET();
        else if (choice.equals("3")) runDialogueMIDTERM();

        saveGrades();
        saveLectures();
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
    }

    private static void runDialoguePSET() {
        In in = new In();
        StdOut.print("PSET Number: ");
        int psetNumber = in.readInt();
        while (psets.containsKey(psetNumber)) {
            PsetInfo ps = psets.get(psetNumber);
            StdOut.print("Points earned on PSET: ");
            double earnedPoints = in.readDouble();
            ps.setPsetEarned(earnedPoints);
            totalEarnedPSET += earnedPoints;
            totalPossiblePSET += ps.getPsetPossible();
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
    }

    private static void runDialogueMIDTERM() {
        // Implement similarly if necessary
        StdOut.println("Midterm grading functionality not yet implemented.");
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
        out.println("0 0 0");   // Placeholder for midterm grades
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
        in.readLine(); // Skip header
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            int lectureNumber = Integer.parseInt(fields[0]);
            lectures.put(lectureNumber, new LectureInfo(
                    fields[1], fields[2],
                    Integer.parseInt(fields[3]),
                    Integer.parseInt(fields[4])));
        }
    }

    private static void initializePSETS() {
        In in = new In("psets.csv");
        in.readLine(); // Skip header
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
        in.readLine(); // Skip header
        String line = in.readLine();
        String[] fields = line.split(" ");
        totalEarnedPoints = Double.parseDouble(fields[0]);
        totalPossiblePoints = Double.parseDouble(fields[1]);
        inClassGrade = Double.parseDouble(fields[2]);

        in.readLine(); // Skip header
        line = in.readLine();
        fields = line.split(" ");
        totalEarnedPSET = Double.parseDouble(fields[0]);
        totalPossiblePSET = Double.parseDouble(fields[1]);
        psetGrade = Double.parseDouble(fields[2]);

        in.readLine(); // Skip header
        // Placeholder for midterm grades, we are not using them yet.
    }
}
