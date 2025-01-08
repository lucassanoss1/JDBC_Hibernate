package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        dao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        dao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        dao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =  dao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}
