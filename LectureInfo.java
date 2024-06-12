public class LectureInfo {
    private String date;                        // date lecture was on
    private String dayOfWeek;                   // day lecture was on
    private int possiblePoints;                 // total possible points
    private int earnedPoints;                   // total points earned


    public LectureInfo(String date, String dayOfWeek, int possiblePoints, int earnedPoints) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.possiblePoints = possiblePoints;
        this.earnedPoints = earnedPoints;
    }

    public int getPossiblePoints() {

        return possiblePoints;
    }

    public String getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getEarnedPoints() {
        return earnedPoints;
    }

    public void updatedEarnedPoints(int earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public static void main(String[] args) {

    }
}
