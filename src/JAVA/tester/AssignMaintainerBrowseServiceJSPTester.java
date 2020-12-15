package JAVA.tester;

import JAVA.AssignMaintainerBrowseServiceJSP;
import JAVA.MySqlDbConnection;

public class AssignMaintainerBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        AssignMaintainerBrowseServiceJSP service = new AssignMaintainerBrowseServiceJSP();
        System.out.println(service.getAssignMaintainerBrowseToJSONJSP(db, 2, 1, 0));
    }

}
