package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesEWOBrowseService;

public class ShowActivitiesEWOBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowActivitiesEWOBrowseService service = new ShowActivitiesEWOBrowseService();
        System.out.println(service.getShowActivitiesEWOBrowseToJSON(db));
    }
}
