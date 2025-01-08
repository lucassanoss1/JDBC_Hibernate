package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService servis = new UserServiceImpl();
        servis.createUsersTable();
        servis.saveUser("Паша", "Коваль", (byte) 21);
        servis.saveUser("Никита", "Горный", (byte) 21);
        servis.saveUser("Андрей", "ФСБ", (byte) 21);
        servis.saveUser("Маша", "Гор", (byte) 21);
        servis.getAllUsers();
        servis.cleanUsersTable();
        servis.dropUsersTable();
    }
}
