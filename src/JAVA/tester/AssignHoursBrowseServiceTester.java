package JAVA.tester;

import JAVA.AssignHoursBrowseService;
import JAVA.MySqlDbConnection;

public class AssignHoursBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        AssignHoursBrowseService service = new AssignHoursBrowseService();
        System.out.println(service.getAssignHoursBrowseToJSON(db));
    }

}
