package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();//1)создание таблицы

       userService.saveUser("Anna", "Dash", (byte) 19);
        userService.saveUser("Dan", "Jones", (byte) 21);
        userService.saveUser("Mark", "Jones", (byte) 41);
        userService.saveUser("Jane", "Do", (byte) 23);
        //2)добавление 4-х пользователей

        System.out.println(userService.getAllUsers()); //3)вывод списка пользователей

         userService.cleanUsersTable();//4) очистка таблицы

        userService.dropUsersTable(); // 5) удаление таблицы

        //userService.removeUserById(2); метод удаления юзера по Id
       //закомментирован, т.к. по условию его в main нет

    }
}