package jm.task.core.jdbc.util;

import java.sql.*;

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
}