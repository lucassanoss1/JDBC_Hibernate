package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getInstance().getConnect();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Statement stat = connection.createStatement()) {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS users" + "(id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(100)," +
                    " lastname VARCHAR(100)," +
                    " AGE INT)");

        } catch (SQLException e) {
            System.out.println("Таблица не созданна");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement stat = connection.createStatement()) {
            stat.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("Такой таблицы нету");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prstat = connection.prepareStatement("INSERT INTO users (name, lastName, age) " +
                "VALUES (?, ?, ?)")) {

            prstat.setString(1, name);
            prstat.setString(2, lastName);
            prstat.setByte(3, age);
            prstat.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось добавить пользователя, но я пытался");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement prstat = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            prstat.setLong(1, id);
            prstat.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось удалить по id, потому что id, это конструкт навешанный обществом");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось получить пользователей");
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement stat = connection.createStatement()) {
            stat.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            System.out.println("Не удалось очистить таблицу");
        }
    }
}
