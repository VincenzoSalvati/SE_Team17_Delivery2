package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesBrowseServiceJSP;
import junit.framework.TestCase;

public class showActivitiesBrowseServiceJSPTester extends TestCase {
    String jsonResult_expected = "";
    String jsonResult_received = "";
    int week = 1;
    private ShowActivitiesBrowseServiceJSP act;
    private MySqlDbConnection db;

    public showActivitiesBrowseServiceJSPTester() {
        this.act = new ShowActivitiesBrowseServiceJSP();
        this.db = new MySqlDbConnection();
    }

    public void testShowActivitiesBrowseServiceJSP() {
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("project");
        jsonResult_received = act.getShowActivitiesBrowseToJSONJSP(db, week);

        jsonResult_expected = act.makeJSON_showActivities();

        assertEquals(jsonResult_expected, jsonResult_received);
    }
}
