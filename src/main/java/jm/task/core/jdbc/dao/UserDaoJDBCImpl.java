package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
    private final String createSql = "create table if not exists mydbtest.user_table " +
                        "(id int auto_increment, " +
                        " userName varchar(40), " +
                        " userLastName varchar(40), " +
                        " userAge int not null, " +
                        " PRIMARY KEY ( id ))";

    private final String dropSql = "DROP TABLE if exists user_table";
    private final String saveSql = "insert into user_table (userName, userLastName, userAge) VALUES (?,?,?)";
    private final String removeSql = "DELETE FROM user_table where id=?";
    private final String getAllSql = "select * from user_table";
    private final String cleanSql = "DELETE FROM user_table;";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try(Statement statement = util.getConnection().createStatement()) {
                statement.executeUpdate(createSql);
//            System.out.println("Создание таблицы прошло успешно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = util.getConnection().createStatement()){
            statement.executeUpdate(dropSql);
//            System.out.println("Удаление таблицы прошло успешно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = util.getConnection().prepareStatement(saveSql)){
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.execute();
//            System.out.println("Добавление прошло успешно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = util.getConnection().prepareStatement(removeSql)){
            statement.setLong(1, id);
            statement.execute();
//            System.out.println("Удаление по id успешно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        try (Statement statement = util.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(getAllSql);
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("userName"));
                user.setLastName(resultSet.getString("userLastName"));
                user.setAge(resultSet.getByte("userAge"));
                System.out.println(user);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getConnection().createStatement()){
            statement.execute(cleanSql);
//            System.out.println("Очистка прошла успешно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
