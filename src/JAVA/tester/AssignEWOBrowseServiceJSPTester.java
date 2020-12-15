package JAVA.tester;

import JAVA.AssignEWOBrowseServiceJSP;
import JAVA.MySqlDbConnection;

public class AssignEWOBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        AssignEWOBrowseServiceJSP service = new AssignEWOBrowseServiceJSP();
        System.out.println(service.getAssignEWOBrowseToJSONJSP(db, 4, 1, 4, "Tuesday"));
    }

}
