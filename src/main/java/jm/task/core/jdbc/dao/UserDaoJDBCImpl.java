package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Statement statement =  null;
    private String sql = null;
    //private поля по условию

    public UserDaoJDBCImpl() {

    }


 // Необходимые операции (6 операций по условию):

    public void createUsersTable() {
        //1)создание таблицы юзеров

        sql = "CREATE TABLE KATATABLE " +
                "(id BIGINT AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastname VARCHAR(255), " +
                " age TINYINT , " +
                " PRIMARY KEY (id))";
        try {
            statement = (Statement) Util.getConnection().createStatement();
        } catch (SQLException e) {
            e.getMessage();
        }
        try {
            statement.executeUpdate(sql);
            System.out.println("table created");
        } catch (SQLException e) {
            System.out.println("table not created: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        //2)удаление таблицы юзеров

        sql= "DROP TABLE KATATABLE";
        try {
            statement = (Statement) Util.getConnection().createStatement();
        } catch (SQLException e) {
            e.getMessage();
        }
        try {
            statement.executeUpdate(sql);
            System.out.println("table dropped");
        } catch (SQLException e) {
            System.out.println("table not dropped: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        // 4) Добавление User в таблицу с выводом в консоль

        PreparedStatement preparedStatement=null;

        sql = "INSERT INTO KATATABLE (name,lastname,age) VALUES (?, ?, ?)";
        try {
            preparedStatement = Util.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            preparedStatement.setString(1, name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            preparedStatement.setString(2, lastName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            preparedStatement.setByte(3, age);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            preparedStatement.executeUpdate();

            System.out.println("User с именем - " + name + " добавлен в базу данных" );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeUserById(long id) {
        // 5)Удаление User из таблицы (по id)

        PreparedStatement preparedStatement=null;

        sql = "DELETE FROM KATATABLE WHERE id=?";
        try {
            preparedStatement = Util.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("user removed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        // 6)Получение всех User(ов) из таблицы

        List<User> users = new ArrayList<User>();
        sql = "SELECT id,name,lastname,age FROM KATATABLE";
        try {
            statement = (Statement) Util.getConnection().createStatement();
        } catch (SQLException e) {
            e.getMessage();
        }
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public void cleanUsersTable() {
        // 3) очистка содержимого таблицы

        sql = "TRUNCATE TABLE KATATABLE";
        try {
            statement = (Statement) Util.getConnection().createStatement();
        } catch (SQLException e) {
            e.getMessage();
        }
        try {
            statement.executeUpdate(sql);
            System.out.println("table cleaned");
        } catch (SQLException e) {
            System.out.println("table not cleaned: " + e.getMessage());
        }


    }
}