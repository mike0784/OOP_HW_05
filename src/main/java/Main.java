import controllers.UserController;
import model.FileOperation;
import model.FileOperationImpl;
import model.Repository;
import model.RepositoryFile;
import views.Validation;
import views.ViewUser;
import model.sqlite.dbController;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        dbController db = new dbController("USER.db");
        Repository repository = new RepositoryFile(db);
        UserController controller = new UserController(repository,new Validation());
        ViewUser view = new ViewUser(controller);
        view.run();
    }
}
