import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentGradeTracker {

    static Scanner scanner = new Scanner(System.in);
    static List<Student> students = new ArrayList<>();
    static int nextId = 1;

    public static void main(String[] args) {

        boolean running = true;

        System.out.println("==================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("==================================");

        while (running) {

            System.out.println("\n1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Summary Report");
            System.out.println("6. Exit");

            int choice = readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 5:
                    summaryReport();
                    break;

                case 6:
                    running = false;
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    static void addStudent() {

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        List<Double> scores = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {

            double score;

            while (true) {

                score = readDouble(
                        "Enter score for subject " + i + " (0-100): "
                );

                if (score >= 0 && score <= 100) {
                    break;
                }

                System.out.println(
                        "Score must be between 0 and 100."
                );
            }

            scores.add(score);
        }

        students.add(
                new Student(nextId++, name, scores)
        );

        System.out.println(
                "Student added successfully!"
        );
    }

    static void displayStudents() {

        if (students.isEmpty()) {

            System.out.println(
                    "No students found."
            );

            return;
        }

        System.out.println(
                "\n-------------------------------------------------------------"
        );

        System.out.printf(
                "%-5s %-20s %-10s %-10s %-10s %-5s%n",
                "ID",
                "Name",
                "Average",
                "Highest",
                "Lowest",
                "Grade"
        );

        System.out.println(
                "-------------------------------------------------------------"
        );

        for (Student s : students) {

            System.out.printf(
                    "%-5d %-20s %-10.2f %-10.2f %-10.2f %-5s%n",
                    s.getId(),
                    s.getName(),
                    s.getAverage(),
                    s.getHighest(),
                    s.getLowest(),
                    s.getGrade()
            );
        }
    }

    static void searchStudent() {

        System.out.print(
                "Enter student name or ID: "
        );

        String search = scanner.nextLine();

        boolean found = false;

        for (Student s : students) {

            if (String.valueOf(s.getId()).equals(search)
                    || s.getName()
                    .toLowerCase()
                    .contains(search.toLowerCase())) {

                System.out.println(
                        "\nStudent ID: " + s.getId()
                );

                System.out.println(
                        "Name: " + s.getName()
                );

                System.out.println(
                        "Scores: " + s.getScores()
                );

                System.out.printf(
                        "Average: %.2f%n",
                        s.getAverage()
                );

                System.out.println(
                        "Highest: " + s.getHighest()
                );

                System.out.println(
                        "Lowest: " + s.getLowest()
                );

                System.out.println(
                        "Grade: " + s.getGrade()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "Student not found."
            );
        }
    }

    static void deleteStudent() {

        int id = readInt(
                "Enter student ID to delete: "
        );

        boolean removed = students.removeIf(
                student -> student.getId() == id
        );

        if (removed) {

            System.out.println(
                    "Student deleted successfully."
            );

        } else {

            System.out.println(
                    "Student not found."
            );
        }
    }

    static void summaryReport() {

        if (students.isEmpty()) {

            System.out.println(
                    "No student data available."
            );

            return;
        }

        double classAverage = students.stream()
                .mapToDouble(Student::getAverage)
                .average()
                .orElse(0);

        Student highest = students.stream()
                .max((a, b) ->
                        Double.compare(
                                a.getAverage(),
                                b.getAverage()
                        ))
                .orElse(null);

        Student lowest = students.stream()
                .min((a, b) ->
                        Double.compare(
                                a.getAverage(),
                                b.getAverage()
                        ))
                .orElse(null);

        System.out.println(
                "\n========== SUMMARY REPORT =========="
        );

        System.out.println(
                "Total Students: " + students.size()
        );

        System.out.printf(
                "Class Average: %.2f%n",
                classAverage
        );

        System.out.printf(
                "Highest Average: %s (%.2f)%n",
                highest.getName(),
                highest.getAverage()
        );

        System.out.printf(
                "Lowest Average: %s (%.2f)%n",
                lowest.getName(),
                lowest.getAverage()
        );

        displayStudents();
    }

    static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}