package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsEWOBrowseServiceJSP;

public class ShowSpecificationsEWOBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowSpecificationsEWOBrowseServiceJSP service = new ShowSpecificationsEWOBrowseServiceJSP();
        System.out.println(service.getShowSpecificationsEWOBrowseToJSONJSP(db, 4, 1));
        System.out.println(service.getSkills(db));
    }

}
