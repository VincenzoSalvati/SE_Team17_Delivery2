package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesEWOCountBrowseService;

public class ShowActivitiesEWOCountBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowActivitiesEWOCountBrowseService service = new ShowActivitiesEWOCountBrowseService();
        System.out.println(service.getShowActivitiesEWOCountBrowseToJSON(db));
    }
}
