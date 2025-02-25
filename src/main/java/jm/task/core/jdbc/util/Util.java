package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import java.util.*;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

public class Util {
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
//    private static final String USER = "roott";
//    private static final String PASS = "roott";
//
//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            Class.forName(JDBC_DRIVER);
//            connection = DriverManager.getConnection(DB_URL, USER, PASS);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/test_db?useSSL=false");
                settings.put(Environment.USER, "roott");
                settings.put(Environment.PASS, "roott");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
