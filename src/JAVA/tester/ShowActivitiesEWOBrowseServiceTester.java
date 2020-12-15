package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowEWOBrowseService;

public class ShowActivitiesEWOBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        ShowEWOBrowseService service = new ShowEWOBrowseService();
        System.out.println(service.getShowEWOBrowseToJSON(db));
    }

}
