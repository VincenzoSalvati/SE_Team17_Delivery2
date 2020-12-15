package JAVA.tester.JUnitTest;

import JAVA.tester.MySqlDbConnection;
import JAVA.ShowActivitiesEWOCountBrowseServiceJSP;
import JAVA.tester._0_SetDatabaseTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class showActivitiesEWOCountBrowseServiceJSPJUnitTest extends TestCase {

    private ShowActivitiesEWOCountBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(showActivitiesEWOCountBrowseServiceJSPJUnitTest.class);
    }

    public static Test suite() {
        return new TestSuite(showActivitiesEWOCountBrowseServiceJSPJUnitTest.class);
    }

    protected void setUp() {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new ShowActivitiesEWOCountBrowseServiceJSP();
    }

    protected void tearDown() {
        service = null;
    }

    public final void testGetShowActivitiesEWOCountBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"ewo_count\":\"3\"}]";
        String jsonResultActual = service.getShowActivitiesEWOCountBrowseToJSONJSP(db, 1);
        assertEquals(jsonResultExpected, jsonResultActual);
        String jsonResultActual2 = service.getShowActivitiesEWOCountBrowseToJSONJSP(db, 2);
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

}
