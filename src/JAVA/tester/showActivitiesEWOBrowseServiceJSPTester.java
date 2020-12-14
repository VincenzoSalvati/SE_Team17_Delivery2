package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesBrowseServiceJSP;
import JAVA.ShowActivitiesEWOBrowseServiceJSP;
import junit.framework.TestCase;

public class showActivitiesEWOBrowseServiceJSPTester extends TestCase {
    String jsonResult_expected = "";
    String jsonResult_received = "";
    int week = 1;
    ShowActivitiesEWOBrowseServiceJSP act;
    MySqlDbConnection db;

    public showActivitiesEWOBrowseServiceJSPTester() {
        this.act = new ShowActivitiesEWOBrowseServiceJSP();
        this.db = new MySqlDbConnection();
    }

    public void testShowActivitiesEWOBrowseServiceJSPTester() {
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("project");

        jsonResult_received = act.getShowActivitiesEWOBrowseToJSONJSP(db, week);
        jsonResult_expected = act.makeJSON_showActivitiesEWO();


        assertEquals(jsonResult_expected, jsonResult_received);
    }
    
}
