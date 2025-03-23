package com.GradingSystem;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;

public class GradingSystem {

    // Method to determine the grade based on the score
    public static String getGrade(double score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Method to store student data in the file
    public static void storeStudentData(String studentName, String rollNumber, double[] scores, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(studentName + "," + rollNumber + ",");
            for (double score : scores) {
                writer.write(score + ",");
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("An error occurred while storing student data: " + e.getMessage());
        }
    }

    // Method to retrieve all student data from the file
    public static void retrieveStudentData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("\nStudent Data Retrieved:");
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split(",");
                System.out.println("Name: " + studentData[0] + ", Roll Number: " + studentData[1]);
                System.out.println("Scores: ");
                for (int i = 2; i < studentData.length; i++) {
                    System.out.println("Subject " + (i - 1) + ": " + studentData[i]);
                }
                System.out.println("--------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving student data: " + e.getMessage());
        }
    }

    // Method to validate if the roll number is exactly 8 digits
    public static String getValidRollNumber(Scanner scanner) {
        String rollNumber;
        while (true) {
            System.out.print("Enter roll number (exactly 8 digits): ");
            rollNumber = scanner.nextLine();
            if (rollNumber.length() == 8 && rollNumber.matches("[0-9]{8}")) {
                break;
            } else {
                System.out.println("Invalid roll number! Please enter exactly 8 digits.");
            }
        }
        return rollNumber;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "student_data.txt"; // File to store data

        // Subjects
        String[] subjects = {"Telugu", "Hindi", "English", "Maths", "Science", "Social"};

        // Loop to keep accepting student scores until the user decides to exit
        while (true) {
            // Get student's name and roll number
            System.out.print("Enter student's name: ");
            String studentName = scanner.nextLine();

            // Validate roll number
            String rollNumber = getValidRollNumber(scanner);

            double[] scores = new double[subjects.length];
            double totalScore = 0;
            int validSubjects = 0;

            // Loop through all subjects and get scores
            for (int i = 0; i < subjects.length; i++) {
                try {
                    System.out.print("Enter the marks for " + subjects[i] + " (0-100): ");
                    double score = scanner.nextDouble();

                    // Validate score input
                    if (score < 0 || score > 100) {
                        System.out.println("Invalid score. Please enter a score between 0 and 100.");
                        i--; // Decrement to re-enter the score for the current subject
                        continue;
                    }

                    // Calculate and display grade
                    String grade = getGrade(score);
                    System.out.println("The grade for the score in " + subjects[i] + " : " + grade);

                    // Store score for later use and accumulate the total score
                    scores[i] = score;
                    totalScore += score;
                    validSubjects++;

                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a numeric value for " + subjects[i] + " marks.");
                    scanner.nextLine(); // Clear the buffer to prevent an infinite loop
                }
            }

            // Calculate and display average score and overall grade
            if (validSubjects > 0) {
                double averageScore = totalScore / validSubjects;
                String overallGrade = getGrade(averageScore);

                System.out.println("\nStudent Name: " + studentName);
                System.out.println("Roll Number: " + rollNumber);
                System.out.println("Average score: " + averageScore);
                System.out.println("Overall grade based on average score: " + overallGrade);

                // Store student data in the file
                storeStudentData(studentName, rollNumber, scores, filename);
            }

            // Ask the user if they want to input another set of scores
            System.out.print("Do you want to enter marks for another student? (y/n): ");
            String continueInput = scanner.next();
            scanner.nextLine(); // Consume the remaining newline
            if (!continueInput.equalsIgnoreCase("y")) {
                break;
            }
        }

        // Retrieve and display all stored student data
        System.out.print("Do you want to retrieve all stored student data? (y/n): ");
        String retrieveInput = scanner.next();
        if (retrieveInput.equalsIgnoreCase("y")) {
            retrieveStudentData(filename);
        }

        scanner.close();
    }
}


