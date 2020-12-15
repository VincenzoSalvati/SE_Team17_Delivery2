package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesBrowseService;

public class ShowActivitiesBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowActivitiesBrowseService service = new ShowActivitiesBrowseService();
        System.out.println(service.getShowActivitiesBrowseToJSON(db));
    }

}

