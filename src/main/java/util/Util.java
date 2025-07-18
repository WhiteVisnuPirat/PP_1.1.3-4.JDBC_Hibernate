package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/my_users_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "020684Dkfl!";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Загружаем драйвер MySQL при старте класса
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Не удалось загрузить драйвер MySQL", e);
        }
    }

    // Метод для подключения к БД
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных", e);
        }
    }
}