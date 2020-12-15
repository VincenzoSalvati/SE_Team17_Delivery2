package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesBrowseService;

public class ShowActivitiesBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        ShowActivitiesBrowseService service = new ShowActivitiesBrowseService();
        System.out.println(service.getShowActivitiesBrowseToJSON(db));
    }

}

