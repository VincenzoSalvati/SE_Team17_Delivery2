package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesBrowseServiceJSP;

public class ShowActivitiesBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        ShowActivitiesBrowseServiceJSP service = new ShowActivitiesBrowseServiceJSP();
        System.out.println(service.getShowActivitiesBrowseToJSONJSP(db, 1));
    }

}

