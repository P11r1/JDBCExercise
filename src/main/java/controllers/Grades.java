package controllers;

import db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Grades {
    static Connection connection = Database.DbConn();
    static PreparedStatement ps;
    static ResultSet rs;
    static Scanner scanner = new Scanner(System.in);

    public static boolean createGradesTable() {
        try {
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS grades(" +
                    "grade_id serial PRIMARY KEY NOT NULL," +
                    "student_id int NOT NULL," +
                    "score int NOT NULL," +
                    "grade varchar(50) NOT NULL," +
                    "FOREIGN KEY (student_id) REFERENCES students(id))");
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void getAllGrades() {
        try {
            ps = connection.prepareStatement("SELECT grades.*, students.name, students.age FROM grades LEFT JOIN students ON grades.student_id = students.id");
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) { // rs.next() Loops until it reaches the end of table
                String id = "id: " + rs.getInt("grade_id");
                String score = "score: " + rs.getInt("score");
                String grade = "grade: " + rs.getString("grade");
                String studentId = "student_id: " + rs.getInt("student_id");
                String name = "name: " + rs.getString("name");
                String age = "age: " + rs.getInt("age");

                System.out.println(id + ", " + score + ", '" + grade + "', " + studentId + ", '" + name + "', " + age + "");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void askScoreAndAssignGrade() {
        System.out.println("Enter the student id: ");
        int studentId = scanner.nextInt();

        System.out.println("Enter student score: ");
        int score = scanner.nextInt();

        String grade;

        if (score < 40) {
            grade = "F";
        } else if (score < 49) {
            grade = "D";
        } else if (score < 59) {
            grade = "C";
        } else if (score < 69) {
            grade = "B";
        } else {
            grade = "A";
        }

        try {
            ps = connection.prepareStatement("INSERT INTO grades(student_id, score, grade)"
                    + "VALUES(" + studentId + ", " + score + ", '" + grade + "')");
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean deleteGrades() {
        System.out.print("Enter the grade id you want to delete: ");
        int id = scanner.nextInt();

        try {
            ps = connection.prepareStatement("DELETE FROM grades " +
                    "WHERE grade_id =" + id);
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
