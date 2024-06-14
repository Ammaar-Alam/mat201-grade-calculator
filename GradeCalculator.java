import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class GradeCalculator {

    private static Map<Integer, LectureInfo> lectures
            = new HashMap<>();                          // hashmap of all indexed lectures
    private static Map<Integer, Double> grades
            = new HashMap<>();                          // for now, only implementing participation grade
    private static Map<Integer, PsetInfo> psets =
            new HashMap<>();                            // hashmap of all indexed psets
    private static double totalPossiblePoints;          // sum of all points up to the current date
    private static double totalEarnedPoints;            // sum of all points earned up to current date
    private static int dailyEarnedPoints = 0;           // points earned on current day
    private static double inClassGrade;
    private static double totalEarnedPSET;
    private static double totalPossiblePSET;
    private static double psetGrade;
    private static double totalEarnedMidterm;
    private static double totalPossibleMidterm;
    private static double midtermGrade;
    private static LectureInfo li;                      // lecture object containing all the lecture info
    private static PsetInfo ps;                         // ps object containing all the ps info




    public static void main(String[] args) {
        initializeLectures();
        intializePSETS();
        initializeGrades();
        StdOut.println("Current In-Class Work Grade: " + inClassGrade + "%");
        StdOut.println("Current PSET Grade: " + psetGrade + "%");
        StdOut.println("Current Midterms Grade: " + midtermGrade + "%");

        In in = new In();
        StdOut.println("Please select which category e you would like to update:");
        StdOut.println("1: In-Class Grade\n2: PSET Grade\n3: Midterm Grade");
        if (in.readString().equalsIgnoreCase("1")) runDialogueINCLASS();
        else if(in.readString().equalsIgnoreCase("2")) runDialoguePSET();
        else if(in.readString().equalsIgnoreCase("2")) runDialogueMIDTERM();


        saveLectures();
    }

    // runs the dialogue options and logic for in-class grades
    private static void runDialogueINCLASS() {
        In in = new In();
        StdOut.print("Lecture Number: ");

        while (!in.isEmpty()) {
            int currentDay = in.readInt();
            li = lectures.get(currentDay); // gets the lecture on the inserted day

            StdOut.println("\nLECTURE ENTERED: " + li.getDate() + ", " + li.getDayOfWeek());
            StdOut.print("Points earned for the day: ");
            dailyEarnedPoints = in.readInt();

            li.updateDailyEarnedPoints(dailyEarnedPoints);
            updateTotals(dailyEarnedPoints, li.getPossiblePoints());

            inClassGrade = ((double) totalEarnedPoints / totalPossiblePoints) * 100;
            grades.put(1, inClassGrade); // update grade hashmap

            StdOut.println("\nUpdated In-Class Work Grade: " + inClassGrade + "%");

            StdOut.print("Enter another day? (y or n): ");
            if (in.readString().equalsIgnoreCase("y")) {
                StdOut.println("-------------\n\n");
                runDialogueINCLASS();
            }
            break;
        }
    }

    private static void runDialoguePSET() {
        In in = new In();
        StdOut.print("PSET Number: ");

        while (!in.isEmpty()) {
            int psetNumber = in.readInt();
            ps = psets.get(psetNumber); // gets the lecture on the inserted day

            StdOut.print("PSET Grade: ");
            psets.put(psetNumber, in.readDouble());

            inClassGrade = ((double) totalEarnedPoints / totalPossiblePoints) * 100;
            grades.put(1, inClassGrade); // update grade hashmap

            StdOut.println("\nUpdated In-Class Work Grade: " + inClassGrade + "%");

            StdOut.print("Enter another PSET? (y or n): ");
            if (in.readString().equalsIgnoreCase("y")) {
                StdOut.println("-------------\n\n");
                runDialoguePSET();
            }
            break;
        }
    }

    private static void runDialogueMIDTERM() {

    }

    private static void updateTotals(int dailyEarnedPoints, int possiblePoints) {
        totalEarnedPoints += dailyEarnedPoints;
        totalPossiblePoints += possiblePoints;
    }

    // saves the updated lectures files to a new csv (overwriting existing one)
    // this allows for the program to be exited while saving the updated information
    private static void saveLectures() {
        Out out = new Out("lectures.csv");

        out.println("totalEarnedPoints,totalPossiblePoints,inClassGrade");
        out.println(totalEarnedPoints + " " + totalPossiblePoints + " " + grades.get(1));

        out.println("LectureNumber,date,dayOfWeek,possiblePoints,earnedPoints"); // header
        for (Map.Entry<Integer, LectureInfo> entry : lectures.entrySet()) {
            int lectureNumber = entry.getKey();
            LectureInfo li = entry.getValue();

            out.printf("%d,%s,%s,%d,%d\n", lectureNumber, li.getDate(), li.getDayOfWeek(), li.getPossiblePoints(), li.getEarnedPoints());
        }
        out.close();
    }

    // reads the csv file in the project folder to make the hashmap with all the lecture info
    private static void initializeLectures() {
        In in = new In("lectures.csv");
        in.readLine(); // lecture info header
        in.readLine();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            lectures.put(Integer.parseInt(fields[0]), new LectureInfo(fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4])));
        }

    }

    private static void intializePSETS() {
        In in = new In("psets.csv");
        in.readLine(); // pset info header
        for (int i = 1; i < 13; i++) {
            psets.put(i, new PsetInfo(in.readDouble(), in.readDouble()));
        }

    }

    private static void initializeGrades() {
        In in = new In("grades.csv");
        in.readLine(); // grades header
        grades.put(1, in.readDouble());
        grades.put(2, in.readDouble());
        grades.put(3, in.readDouble());

        in.readLine(); // pset header
        grades.put(4, in.readDouble()); // total earned pset points
        grades.put(5, in.readDouble()); // total possible pset points
        grades.put(6, in.readDouble()); // pset grade

        totalEarnedPoints = grades.get(1);
        totalPossiblePoints = grades.get(2);
        inClassGrade = grades.get(3);

        totalEarnedPSET = grades.get(4);
        totalPossiblePSET = grades.get(5);
        psetGrade = grades.get(6);
    }

         /**
         * BELOW IS ALL THE OLD CODE SNIPPETS I NO LONGER USED
         * (i'm keeping them because i'm a hoarder even though they're in my git repo)
         */

        //        lectures.put(1, new LectureInfo("June 3", "Monday", 2, 0));
        //        lectures.put(2, new LectureInfo("June 4", "Tuesday", 2, 0));
        //        lectures.put(3, new LectureInfo("June 5", "Wednesday", 2, 0));
        //        lectures.put(4, new LectureInfo("June 6", "Thursday", 2, 0));
        //        lectures.put(5, new LectureInfo("June 7", "Friday", 8, 0)); // worksheet 1
        //        lectures.put(6, new LectureInfo("June 10", "Monday", 2, 0));
        //        lectures.put(7, new LectureInfo("June 11", "Tuesday", 2, 0));
        //        lectures.put(8, new LectureInfo("June 12", "Wednesday", 2, 0));
        //        lectures.put(9, new LectureInfo("June 13", "Thursday", 2, 0));
        //        lectures.put(10, new LectureInfo("June 14", "Friday", 0, 0)); // midterm 1
        //        lectures.put(11, new LectureInfo("June 17", "Monday", 2, 0));
        //        lectures.put(12, new LectureInfo("June 18", "Tuesday", 2, 0));
        //        lectures.put(13, new LectureInfo("June 19", "Wednesday", 2, 0));
        //        lectures.put(14, new LectureInfo("June 20", "Thursday", 2, 0));
        //        lectures.put(15, new LectureInfo("June 21", "Friday", 8, 0)); // worksheet 2
        //        lectures.put(16, new LectureInfo("June 24", "Monday", 2, 0));
        //        lectures.put(17, new LectureInfo("June 25", "Tuesday", 2, 0));
        //        lectures.put(18, new LectureInfo("June 26", "Wednesday", 2, 0));
        //        lectures.put(19, new LectureInfo("June 27", "Thursday", 2, 0));
        //        lectures.put(20, new LectureInfo("June 28", "Friday", 0, 0)); // midterm 2
        //        lectures.put(21, new LectureInfo("July 1", "Monday", 2, 0));
        //        lectures.put(22, new LectureInfo("July 2", "Tuesday", 2, 0));
        //        lectures.put(23, new LectureInfo("July 3", "Wednesday", 2, 0));
        //        lectures.put(24, new LectureInfo("July 4", "Thursday", 0, 0)); // no class
        //        lectures.put(25, new LectureInfo("July 5", "Friday", 0, 0)); // no class
        //        lectures.put(26, new LectureInfo("July 8", "Monday", 2, 0));
        //        lectures.put(27, new LectureInfo("July 9", "Tuesday", 2, 0));
        //        lectures.put(28, new LectureInfo("July 10", "Wednesday", 2, 0));
        //        lectures.put(29, new LectureInfo("July 11", "Thursday", 2, 0));
        //        lectures.put(30, new LectureInfo("July 12", "Friday", 8, 0)); // worksheet 3

        //        // retrieves points possible in entirety
        //        // (fridays may have 8, whereas other days only have 2)
        //        private static void updateTotalPossiblePoints(int currentDay) {
        //            for (Map.Entry<Integer, LectureInfo> entry : lectures.entrySet()) {
        //                LectureInfo li = entry.getValue();
        //                totalPossiblePoints += li.getPossiblePoints();
        //            }
        //        }
        //
        //        // update total earned points
        //        private static void updateTotalEarnedPoints(int currentDay) {
        //            for (Map.Entry<Integer, LectureInfo> entry : lectures.entrySet()) {
        //                LectureInfo li = entry.getValue();
        //                totalEarnedPoints += li.getEarnedPoints();
        //            }
        //        }
}
