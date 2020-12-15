package JAVA.tester;

import JAVA.AssignEWOBrowseServiceJSP;

public class AssignEWOBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        AssignEWOBrowseServiceJSP service = new AssignEWOBrowseServiceJSP();
        System.out.println(service.getAssignEWOBrowseToJSONJSP(db, 4, 1, 4, "Tuesday"));
    }

}
