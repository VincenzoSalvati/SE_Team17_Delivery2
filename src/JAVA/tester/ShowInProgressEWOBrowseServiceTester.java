package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowInProgressEWOBrowseService;

public class ShowInProgressEWOBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowInProgressEWOBrowseService service = new ShowInProgressEWOBrowseService();
        System.out.println(service.getShowInProgressEWOBrowseToJSON(db));
    }
}
