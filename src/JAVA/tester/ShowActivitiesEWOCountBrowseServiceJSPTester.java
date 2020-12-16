package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesEWOCountBrowseServiceJSP;

public class ShowActivitiesEWOCountBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowActivitiesEWOCountBrowseServiceJSP service = new ShowActivitiesEWOCountBrowseServiceJSP();
        System.out.println(service.getShowActivitiesEWOCountBrowseToJSONJSP(db, 1));
    }
}
