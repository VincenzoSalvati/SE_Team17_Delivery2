package JAVA.tester;

import JAVA.AssignEWOBrowseService;
import JAVA.MySqlDbConnection;

public class AssignEWOBrowseServiceTester {
    public static void main(String[] args) {
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("project");

        AssignEWOBrowseService service = new AssignEWOBrowseService();
        String jsonResult = service.getAssignEWOBrowseToJSON(db);
        System.out.println(jsonResult);

    }
}
