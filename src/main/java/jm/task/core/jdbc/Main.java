package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Smit", (byte) 20);
        userService.saveUser("Alen", "Delon", (byte) 30);
        userService.saveUser("Alexander", "Smit", (byte) 40);
        userService.saveUser("Nika", "Tramp", (byte) 50);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
