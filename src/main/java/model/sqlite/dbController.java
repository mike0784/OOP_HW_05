package model.sqlite;

import java.sql.*;
import java.util.*;

public class dbController {
    private String fileDB;
    public Statement statmt;
    public ResultSet resSet;
    private List<String> pattern = Arrays.asList("id", "name", "family", "phone");
    private String table = "Contact";

    // Объект, в котором будет храниться соединение с БД
    private static Connection connection;

    public dbController(String fileDB) throws SQLException, ClassNotFoundException {
        this.fileDB = fileDB;
        //DriverManager.registerDriver(new JDBC());
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.fileDB);
        this.createDB();
    }

    public void createDB()  throws SQLException, ClassNotFoundException
    {
        statmt = connection.createStatement();
        statmt.execute("CREATE TABLE if not exists 'Contact' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'family' text, 'phone' INT)");
    }

    public List<Dictionary> readDB() throws SQLException
    {
        List<Dictionary> result = new ArrayList<>();

        int count = 0;
        resSet = statmt.executeQuery("SELECT * FROM Contact");
        while(resSet.next())
        {
            Dictionary<String, String> line = new Hashtable();
            for(int i = 0; i < pattern.size(); i++)
            {
                line.put(pattern.get(i), resSet.getString(pattern.get(i)));
            }
            result.add(line);
            count++;
        }
        return result;
    }

    public Dictionary<String, String> readLineDB(int id) throws SQLException
    {
        Dictionary<String, String> result = new Hashtable<>();
        resSet = statmt.executeQuery(String.format("SELECT * FROM %s WHERE id=%d", this.table, id));
        for(int i = 0; i < pattern.size(); i++)
        {
            result.put(pattern.get(i), resSet.getString(pattern.get(i)));
        }
        return result;
    }

    public void updateDB(Dictionary<String, String> line) throws SQLException
    {
        statmt.executeQuery(String.format("UPDATE %s SET %s='%s', %s='%s', %s=%s WHERE id=%s",
                this.table,
                pattern.get(1),
                line.get(pattern.get(1)),
                pattern.get(2),
                line.get(pattern.get(2)),
                pattern.get(3),
                line.get(pattern.get(3)),
                line.get(pattern.get(0))));
    }

    public void insertDB(Dictionary<String, String> line) throws SQLException
    {
        String query = String.format("INSERT INTO '%s' ('%s', '%s', '%s') VALUES ('%s', '%s', %s)",
                this.table,
                pattern.get(1),
                pattern.get(2),
                pattern.get(3),
                line.get(pattern.get(1)),
                line.get(pattern.get(2)),
                line.get(pattern.get(3)));
        statmt.execute(query);
    }

    public void deleteDB(int id) throws ClassNotFoundException, SQLException
    {
        String query = String.format("DELETE FROM Contact WHERE id=%d", id);
        statmt.execute(query);
    }


    public void CloseDB() throws ClassNotFoundException, SQLException
    {
        connection.close();
        statmt.close();
        resSet.close();
        System.out.println("Соединения закрыты");
    }
}
