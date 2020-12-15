package JAVA.tester.JUnitTest;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesBrowseServiceJSP;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class showActivitiesBrowseServiceJSPJUnitTest extends TestCase {

    private ShowActivitiesBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(showActivitiesBrowseServiceJSPJUnitTest.class);
    }

    public static Test suite() {
        return new TestSuite(showActivitiesBrowseServiceJSPJUnitTest.class);
    }

    protected void setUp() {
        service = new ShowActivitiesBrowseServiceJSP();
        db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");
    }

    protected void tearDown() {
        service = null;
    }

    public final void testGetShowActivitiesBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"0\",\"area\":\"Fisciano - Molding\",\"type\":\"Mechanical\",\"estim_time\":\"120\"}," +
                "{\"id\":\"1\",\"area\":\"Nusco - Carpentery\",\"type\":\"Electric\",\"estim_time\":\"30\"}," +
                "{\"id\":\"2\",\"area\":\"Morra - Painting\",\"type\":\"Hydraulic\",\"estim_time\":\"250\"}," +
                "{\"id\":\"3\",\"area\":\"Fisciano - Molding\",\"type\":\"Electronics\",\"estim_time\":\"90\"}]";

        String jsonResultActual = service.getShowActivitiesBrowseToJSONJSP(db, 1);
        assertEquals(jsonResultExpected, jsonResultActual);

        String jsonResultActual2 = service.getShowActivitiesBrowseToJSONJSP(db, 1);
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

}

