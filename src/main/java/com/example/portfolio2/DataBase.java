package com.example.portfolio2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase {
    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS course (name TEXT PRIMARY KEY, ects INTEGER);";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertCourse() {
        String sql1 = "INSERT INTO course (name, ects) VALUES ('Software development', 10);";
        String sql2 = "INSERT INTO course (name, ects) VALUES ('Essential computing', 5);";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printCourses() {
        String sql = "SELECT * FROM course";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Course: " + rs.getString("name") + ", ECTS: " + rs.getInt("ects"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTable();
        {
            insertCourse();
            printCourses();
        }
    }
}


