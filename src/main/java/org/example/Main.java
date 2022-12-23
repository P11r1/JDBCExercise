package org.example;

import controllers.Grades;
import controllers.Students;
import controllers.menu.Menu;

/**
 * Create a table called Students and another table called Grades
 * The columns from the student table: id, name, age
 * Complete the controllers for the student table by adding methods to handle
 * CRD operations on each record in the table.
 *
 * On the Grades table, store the students id, score and Grade where for each grade:
 * 0 - 40 F, 41 - 49 D, 50 - 59 C, 60 - 69 B, 70 - 100 A.
 * The columns should be id, Student id, score, grade
 * Complete the controller on the Grades table to have CRD OP.
 * For the read operation, you should see students info as well
 *
 * @author Marko
 */
public class Main {
    public static void main(String[] args) {
        //METHOD CALLS
        Students.createStudentsTable();
        Grades.createGradesTable();
        Menu.mainMenu();
    }
}