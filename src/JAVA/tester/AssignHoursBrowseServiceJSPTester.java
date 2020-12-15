package JAVA.tester;

import JAVA.AssignHoursBrowseServiceJSP;
import JAVA.MySqlDbConnection;

public class AssignHoursBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        AssignHoursBrowseServiceJSP service = new AssignHoursBrowseServiceJSP();
        System.out.println(service.getAssignHoursBrowseToJSONJSP(db, 2, 1, 2, 0, "Monday"));
    }

}
