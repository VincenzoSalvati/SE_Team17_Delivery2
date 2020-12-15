package JAVA.tester;

import JAVA.AssignMaintainerBrowseService;
import JAVA.MySqlDbConnection;

public class AssignMaintainerBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        AssignMaintainerBrowseService service = new AssignMaintainerBrowseService();
        System.out.println(service.getAssignMaintainerBrowseToJSON(db));
    }

}
