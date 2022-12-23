package controllers;

import db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Students {
    static Connection connection = Database.DbConn();
    static PreparedStatement ps;
    static ResultSet rs;
    static Scanner scanner = new Scanner(System.in);

    public static boolean createStudentsTable() {
        try {
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS students(" +
                    "    id serial PRIMARY KEY NOT NULL," +
                    "    name varchar(255) NOT NULL," +
                    "    age int NOT NULL)");
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createNewStudents() {
        // Add prompt to tell the user what data they need to enter next
        System.out.print("Enter the student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the student age: ");
        int age = scanner.nextInt();

        try {
            ps = connection.prepareStatement("INSERT INTO students(name, age)" +
                    "VALUES('" + name + "', " + age + ")");

            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void getAllStudents() {
        try {
            ps = connection.prepareStatement("SELECT * FROM students");
            rs = ps.executeQuery();


            // Loop through the result set
            while (rs.next()) { // rs.next() Loops until it reaches the end of table
                String id = "id: " + rs.getInt("id");
                String name = "name: " + rs.getString("name");
                String age = "age: " + rs.getInt("age");

                System.out.println(id + " " + name + " " + age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteStudents() {
        // Prompt the user for info

        System.out.print("Enter the student id you want to delete: ");
        int id = scanner.nextInt();

        try {
            ps = connection.prepareStatement("DELETE FROM students " +
                    "WHERE id =" + id);
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
