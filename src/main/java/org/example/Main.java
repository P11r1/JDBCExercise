package org.example;

import controllers.Grades;
import controllers.Students;
import controllers.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Students.createStudentsTable();
        Grades.createGradesTable();
        Menu.mainMenu();
    }
}