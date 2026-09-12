import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private List<Double> scores;

    public Student(int id, String name, List<Double> scores) {
        this.id = id;
        this.name = name;
        this.scores = new ArrayList<>(scores);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Double> getScores() {
        return scores;
    }

    public double getAverage() {
        return scores.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }

    public double getHighest() {
        return scores.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0);
    }

    public double getLowest() {
        return scores.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0);
    }

    public String getGrade() {
        double average = getAverage();

        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
    }
}