package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesEWOCountBrowseServiceJSP;
import junit.framework.TestCase;

public class showActivitiesEWOCountBrowseServiceJSPTester extends TestCase{
    String jsonResult_expected = "";
    String jsonResult_received = "";
    int week = 1;
    ShowActivitiesEWOCountBrowseServiceJSP act;
    MySqlDbConnection db;

    public showActivitiesEWOCountBrowseServiceJSPTester() {
        this.act = new ShowActivitiesEWOCountBrowseServiceJSP();
        this.db = new MySqlDbConnection();
    }

    public void testShowActivitiesEWOCountBrowseServiceJSPTester() {
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("project");
        jsonResult_received = act.getShowActivitiesEWOCountBrowseToJSONJSP(db, week);

        jsonResult_expected = act.makeJSON_showActivitiesEWOCount();


        assertEquals(jsonResult_expected, jsonResult_received);
    }
}
