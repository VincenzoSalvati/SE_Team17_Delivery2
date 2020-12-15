package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsEWOBrowseService;

public class ShowSpecificationsEWOBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowSpecificationsEWOBrowseService service = new ShowSpecificationsEWOBrowseService();
        System.out.println(service.getShowSpecificationsEWOBrowseToJSON(db));
        System.out.println(service.getSkills(db));
    }

}
