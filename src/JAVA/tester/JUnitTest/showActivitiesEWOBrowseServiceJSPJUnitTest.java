package JAVA.tester.JUnitTest;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesEWOBrowseServiceJSP;
import JAVA.tester._0_SetDatabaseTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class showActivitiesEWOBrowseServiceJSPJUnitTest extends TestCase {

    private ShowActivitiesEWOBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(showActivitiesEWOBrowseServiceJSPJUnitTest.class);
    }

    public static Test suite() {
        return new TestSuite(showActivitiesEWOBrowseServiceJSPJUnitTest.class);
    }

    protected void setUp() {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new ShowActivitiesEWOBrowseServiceJSP();
    }

    protected void tearDown() {
        service = null;
    }

    public final void testGetShowSpecificationsBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"4\",\"area\":\"Fisciano - Molding\",\"type\":\"Mechanical\",\"estim_time\":\"120\"}," +
                "{\"id\":\"5\",\"area\":\"Nusco - Carpentery\",\"type\":\"Electric\",\"estim_time\":\"30\"}," +
                "{\"id\":\"6\",\"area\":\"Morra - Painting\",\"type\":\"Hydraulic\",\"estim_time\":\"250\"}]";
        String jsonResultActual = service.getShowActivitiesEWOBrowseToJSONJSP(db, 1);
        assertEquals(jsonResultExpected, jsonResultActual);
        String jsonResultActual2 = service.getShowActivitiesEWOBrowseToJSONJSP(db, 1);
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

}
