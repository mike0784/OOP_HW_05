package controllers;

import model.Repository;
import model.User;
import views.Validation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final Repository repository;
    private final Validation validator;

    public UserController(Repository repository, Validation validator) {
        this.validator = validator;
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validator.validateUser(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        User user = userSearch(userId, users);
        return user;
    }

    private static User userSearch(String userId, List<User> users) throws Exception {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        throw new Exception("User not found");
    }

    public List<User> readAllUsers() {
        try {
            return repository.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(String userId, User newUser) throws Exception {
        validator.validateUser(newUser);
        /*List<User> users = repository.getAllUsers();
        User user = userSearch(userId, users);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPhone(newUser.getPhone());*/
        User user = new User(userId, newUser.getFirstName(), newUser.getLastName(), newUser.getPhone());
        repository.updadeUser(user);
    }

    public void deleteUser(String readId) throws SQLException, ClassNotFoundException {
        repository.deleteUser(Integer.parseInt(readId));
    }

    public void closeDB() throws SQLException, ClassNotFoundException {
        repository.closeDB();
    }
}
