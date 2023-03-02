package model;

import java.sql.SQLException;
import java.util.List;

public interface Repository {
    List<User> getAllUsers() throws SQLException;

    void CreateUser(User user) throws SQLException;

    void deleteUser(int id) throws SQLException, ClassNotFoundException;

    void updadeUser(User user) throws SQLException;
    void closeDB() throws SQLException, ClassNotFoundException;

    //void saveUsers(List<User> users);
}
