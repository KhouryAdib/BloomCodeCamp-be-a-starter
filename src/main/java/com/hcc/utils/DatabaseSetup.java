package com.hcc.utils;

import java.sql.*;

public class DatabaseSetup {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5550/api-starter-0121";
        String user = "docker";
        String password = "docker";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            // create users table
            if (!tableExists(conn, stmt, "users")) {
                String sql = "CREATE TABLE users " +
                        "(id SERIAL PRIMARY KEY, " +
                        " cohort_start_date DATE, " +
                        " username VARCHAR(255) not null, " +
                        " password VARCHAR(255) not null)";
                stmt.executeUpdate(sql);
                System.out.println("Table 'users' created successfully");
            }

            // create authorities table
            if (!tableExists(conn, stmt, "authorities")) {
                String sql = "CREATE TABLE authorities " +
                        "(id SERIAL PRIMARY KEY, " +
                        " authority VARCHAR(255), " +
                        " user_id INTEGER REFERENCES users(id))";
                stmt.executeUpdate(sql);
                System.out.println("Table 'authorities' created successfully");
            }

            // create assignments table
            if (!tableExists(conn,stmt, "assignments")) {
                String sql = "CREATE TABLE assignments " +
                        "(id SERIAL PRIMARY KEY, " +
                        " branch VARCHAR(255), " +
                        " code_review_video_url VARCHAR(255), " +
                        " github_url VARCHAR(255), " +
                        " number INTEGER, " +
                        " user_id INTEGER REFERENCES users(id), " +
                        " code_reviewer_id INTEGER REFERENCES users(id))";
                stmt.executeUpdate(sql);
                System.out.println("Table 'assignments' created successfully");
            }

            if (stmt != null) stmt.close();
            if (conn != null) conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean tableExists(Connection conn, Statement stmt, String tableName){
        ResultSet rs = null;

        try{
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Execute a SELECT query to check if the table exists
            rs = stmt.executeQuery("SELECT EXISTS(SELECT 1 FROM information_schema.tables WHERE table_name = '"+tableName+ "')");

            // Retrieve the result of the query
            rs.next();
            boolean tableExists = rs.getBoolean(1);

            // Print the result
            System.out.println("Table exists: " + tableExists);
            return tableExists;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("database creation failed: "+tableName);
    }
}