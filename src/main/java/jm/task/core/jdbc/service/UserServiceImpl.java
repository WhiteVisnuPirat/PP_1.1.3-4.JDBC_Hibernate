package jm.task.core.jdbc.service;

import jakarta.transaction.Transactional;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.dao.UserDao;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoHibernateImpl();
  // Добавьте эту строку!

    @Transactional
    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Transactional
    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Transactional
    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
