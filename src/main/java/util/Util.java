package util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


import java.util.Properties;

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

    private static final SessionFactory sessionFactory;

    static {
        try {
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
            settings.put(Environment.USER, "postgres");
            settings.put(Environment.PASS, "root");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "update");

            sessionFactory = new Configuration()
                    .setProperties(settings)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}