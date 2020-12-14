package JAVA.tester;

import JAVA.AssignEWOBrowseServiceJSP;
import JAVA.MySqlDbConnection;

public class AssignEWOBrowseServiceTesterJSP {
    public static void main(String[] args) {
        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("project");

        AssignEWOBrowseServiceJSP service = new AssignEWOBrowseServiceJSP();
        jsonResult = service.getAssignEWOBrowseToJSONJSP(db, 4, 1, 4, "Tuesday");
        System.out.println(jsonResult);
    }
}
