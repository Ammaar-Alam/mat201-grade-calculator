import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class GradeCalculator {
    private static Map<Integer, LectureInfo> lectures = new HashMap<>();
    private static int totalPossiblePoints = 0;
    private static int currentPoints = 0;

   public static void main(String[] args) {
       LectureInfo li;
       initializeLectures();
       In in = new In();

       while (!in.isEmpty()) {
           StdOut.println("Enter lecture number: ");
           int currentDay = in.readInt();

           StdOut.println("Enter points earned for the day:");
           int earnedPoints = in.readInt();
           currentPoints += earnedPoints;
           li = lectures.get(currentDay);

           totalPossiblePoints += li.getPossiblePoints();


           StdOut.println("test 1 | points on day: " + lectures.get(currentDay));
       }
   }

   private static void initializeLectures() {
       lectures.put(1, new LectureInfo("June 3", "Monday", 2, 0));
       lectures.put(2, new LectureInfo("June 4", "Tuesday", 2, 0));
       lectures.put(3, new LectureInfo("June 5", "Wednesday", 2, 0));
       lectures.put(4, new LectureInfo("June 6", "Thursday", 2, 0));
       lectures.put(5, new LectureInfo("June 7", "Friday", 8, 0));         // worksheet 1
       lectures.put(6, new LectureInfo("June 10", "Monday", 2, 0));
       lectures.put(7, new LectureInfo("June 11", "Tuesday", 2, 0));
       lectures.put(8, new LectureInfo("June 12", "Wednesday", 2, 0));
       lectures.put(9, new LectureInfo("June 13", "Thursday", 2, 0));
       lectures.put(10, new LectureInfo("June 14", "Friday", 0, 0));       // midterm 1
       lectures.put(11, new LectureInfo("June 17", "Monday", 2, 0));
       lectures.put(12, new LectureInfo("June 18", "Tuesday", 2, 0));
       lectures.put(13, new LectureInfo("June 19", "Wednesday", 2, 0));
       lectures.put(14, new LectureInfo("June 20", "Thursday", 2, 0));
       lectures.put(15, new LectureInfo("June 21", "Friday", 8, 0));       // worksheet 2
       lectures.put(16, new LectureInfo("June 24", "Monday", 2, 0));
       lectures.put(17, new LectureInfo("June 25", "Tuesday", 2, 0));
       lectures.put(18, new LectureInfo("June 26", "Wednesday", 2, 0));
       lectures.put(19, new LectureInfo("June 27", "Thursday", 2, 0));
       lectures.put(20, new LectureInfo("June 28", "Friday", 0, 0));       // midterm 2
       lectures.put(21, new LectureInfo("July 1", "Monday", 2, 0));
       lectures.put(22, new LectureInfo("July 2", "Tuesday", 2, 0));
       lectures.put(23, new LectureInfo("July 3", "Wednesday", 2, 0));
       lectures.put(24, new LectureInfo("July 4", "Thursday", 0, 0));      // no class
       lectures.put(25, new LectureInfo("July 5", "Friday", 0, 0));        // no class
       lectures.put(26, new LectureInfo("July 8", "Monday", 2, 0));
       lectures.put(27, new LectureInfo("July 9", "Tuesday", 2, 0));
       lectures.put(28, new LectureInfo("July 10", "Wednesday", 2, 0));
       lectures.put(29, new LectureInfo("July 11", "Thursday", 2, 0));
       lectures.put(30, new LectureInfo("July 12", "Friday", 8, 0));       // worksheet 3
   }
}
