package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowEWOBrowseServiceJSP;

public class ShowActivitiesEWOBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        ShowEWOBrowseServiceJSP service = new ShowEWOBrowseServiceJSP();
        System.out.println(service.getShowEWOBrowseToJSONJSP(db, 1));
    }

}
