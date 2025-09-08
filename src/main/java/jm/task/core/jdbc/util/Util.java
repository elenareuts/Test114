package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;


public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/katabase";
    //строчку URL можно посмотреть в data sourse properties

    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    //root в обоих строках стандартный логин и пароль для подуключения к бд, у нас может быть свой

    public static Connection getConnection() {
        Connection connection = null;
        try {
            //создаем коннект с базой данных
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }
        catch (SQLException e) {
            //обработали ситуацию, когда подключение не удалось
            System.out.println("there is no connection... Exception!");
        }
        //если подключение успешно выполнено
        System.out.println("We are connected!");
        return connection;
    }


    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory () {
        if (sessionFactory==null) {
            try {
                Configuration configuration = new Configuration();
                //создаем новую конфигурацию
                Properties settings = new Properties();
                // создание объекта,  который будет хранить конфигурационные параметры Hibernate
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/katabase");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");
                // добавление в объект Properties settings конфигурационных параметров

                configuration.setProperties(settings);
                // добавляем в конфигурацию наши параметры
                configuration.addAnnotatedClass(User.class);
                // добавляем в конфигурацию класс, который мы пометили аннотациями

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


}