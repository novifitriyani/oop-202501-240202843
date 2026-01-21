package com.upb.agripos.config;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    // Constructor private → tidak bisa new dari luar
    private DatabaseConnection() {
        System.out.println("Database connected");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
