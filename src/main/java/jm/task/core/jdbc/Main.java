package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("tom", "tomcat",(byte)112);
        userService.saveUser("maks", "makscat",(byte)43);
        userService.saveUser("pet", "petcat",(byte)2);
        userService.saveUser("los", "loscat",(byte)3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        try {
            Util.getConnection().isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
