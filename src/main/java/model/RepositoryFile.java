package model;

import model.sqlite.dbController;

import java.sql.SQLException;
import java.util.*;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private dbController fileOperation;

    private List<String> pattern = Arrays.asList("id", "name", "family", "phone");

    public RepositoryFile(dbController fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<Dictionary> lines = fileOperation.readDB();
        List<User> users = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++)
        {
            Dictionary<String, String> line = new Hashtable<>();
            line = lines.get(i);
            users.add(new User(line.get(pattern.get(0)), line.get(pattern.get(1)), line.get(pattern.get(2)), line.get(pattern.get(3))));
        }
        return users;
    }

    @Override
    public void CreateUser(User user) throws SQLException {
        Dictionary<String, String> line = new Hashtable<>();
        line.put(pattern.get(1), user.getFirstName());
        line.put(pattern.get(2), user.getLastName());
        line.put(pattern.get(3), user.getPhone());
        fileOperation.insertDB(line);
    }

    @Override
    public void deleteUser(int id) throws SQLException, ClassNotFoundException {
        fileOperation.deleteDB(id);
    }

    @Override
    public void updadeUser(User user) throws SQLException {
        Dictionary<String, String> result = new Hashtable<>();
        result.put(pattern.get(0), user.getId());
        result.put(pattern.get(1), user.getFirstName());
        result.put(pattern.get(2), user.getLastName());
        result.put(pattern.get(3), user.getPhone());
        fileOperation.updateDB(result);
    }

    @Override
    public void closeDB() throws SQLException, ClassNotFoundException {
        fileOperation.CloseDB();
    }
}
