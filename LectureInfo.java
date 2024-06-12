public class LectureInfo {
    private String date;
    private String dayOfWeek;
    private int possiblePoints;
    private int earnedPoints;

    public LectureInfo(String date, String dayOfWeek, int possiblePoints, int earnedPoints) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.possiblePoints = possiblePoints;
        this.earnedPoints = earnedPoints;
    }

    public int getPossiblePoints() {
        return possiblePoints;
    }

    public int getEarnedPoints() {
        return earnedPoints;
    }

    public static void main(String[] args) {

    }
}
