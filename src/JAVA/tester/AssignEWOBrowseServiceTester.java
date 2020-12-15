package JAVA.tester;

import JAVA.AssignEWOBrowseService;
import JAVA.MySqlDbConnection;

public class AssignEWOBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        AssignEWOBrowseService service = new AssignEWOBrowseService();
        System.out.println(service.getAssignEWOBrowseToJSON(db));
    }

}
