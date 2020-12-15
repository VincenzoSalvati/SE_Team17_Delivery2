package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsEWOBrowseService;

public class ShowSpecificationsEWOBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        ShowSpecificationsEWOBrowseService service = new ShowSpecificationsEWOBrowseService();
        System.out.println(service.getShowSpecificationsEWOBrowseToJSON(db));
        System.out.println(service.getSkills(db));
    }

}
