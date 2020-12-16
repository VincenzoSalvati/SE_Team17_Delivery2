package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesEWOBrowseServiceJSP;

public class ShowActivitiesEWOBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowActivitiesEWOBrowseServiceJSP service = new ShowActivitiesEWOBrowseServiceJSP();
        System.out.println(service.getShowActivitiesEWOBrowseToJSONJSP(db, 1));
    }
}
