package JAVA.tester;

import JAVA.AssignEWOBrowseService;

public class AssignEWOBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        AssignEWOBrowseService service = new AssignEWOBrowseService();
        System.out.println(service.getAssignEWOBrowseToJSON(db));
    }

}
