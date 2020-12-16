package JAVA.tester;

import JAVA.AssignMaintainerBrowseService;
import JAVA.MySqlDbConnection;

public class AssignMaintainerBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        AssignMaintainerBrowseService service = new AssignMaintainerBrowseService();
        System.out.println(service.getAssignMaintainerBrowseToJSON(db));
    }
}
