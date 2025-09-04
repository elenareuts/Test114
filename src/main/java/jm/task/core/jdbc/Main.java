package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable(); // 1) создание таблицы

        userService.saveUser("Ivan", "Petrov", (byte) 21);
        userService.saveUser("Elena", "Ivanova", (byte) 25);
        userService.saveUser("Artem", "Kolosov", (byte) 40);
        userService.saveUser("Inna", "Rogova", (byte) 22); // 2) добавление 4-х юзеров

         System.out.println(userService.getAllUsers()); //3) вывод всех юзеров в консоль

        userService.cleanUsersTable(); //4) очистка таблицы

       // userService.removeUserById(1); метод удаления юзера по Id
        //закомментирован, т.к. по условию его в main нет

        userService.dropUsersTable(); //5) удаление таблицы
    }
}