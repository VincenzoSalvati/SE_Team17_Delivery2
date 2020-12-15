package JAVA.tester;

import JAVA.AssignHoursBrowseService;
import JAVA.MySqlDbConnection;

public class AssignHoursBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        AssignHoursBrowseService service = new AssignHoursBrowseService();
        System.out.println(service.getAssignHoursBrowseToJSON(db));
    }

}
