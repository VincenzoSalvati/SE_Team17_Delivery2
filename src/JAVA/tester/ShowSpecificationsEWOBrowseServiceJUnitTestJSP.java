package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsEWOBrowseServiceJSP;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ShowSpecificationsEWOBrowseServiceJUnitTestJSP extends TestCase {

    private ShowSpecificationsEWOBrowseServiceJSP service;
    private MySqlDbConnection db;

    protected void setUp() throws Exception {
        service = new ShowSpecificationsEWOBrowseServiceJSP();
        db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("5#bJiogahLzN");
        db.setDbName("Project");
    }

    protected void tearDown() throws Exception {
        service = null;
    }

    public final void testGetShowSpecificationsBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"4\",\"work_note\":\"Plant stopped from 12:23 p.m. pending intervention\",\"int_des\":\"Compressor replacement\",\"id_activity\":\"EWO 4 - Fisciano - Molding\",\"week_activity\":\"1\",\"ewo_activity\":\"true\",\"estimate_tr\":\"70\"}]";
        String jsonResultActual = service.getShowSpecificationsEWOBrowseToJSONJSP(db, 4, 1);
        assertEquals(jsonResultExpected, jsonResultActual);

        String jsonResultActual2 = service.getShowSpecificationsEWOBrowseToJSONJSP(db, 1, 1);
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

    public final void testGetSingleSkill() {
        String jsonResultExpected = "[{\"id\":\"1\",\"skill\":\"Electrical Maintainance\"},{\"id\":\"2\",\"skill\":\"Knowledge of cables types\"},{\"id\":\"4\",\"skill\":\"Knowledge of robot workstation 23\"},{\"id\":\"0\",\"skill\":\"PAV Certification\"},{\"id\":\"3\",\"skill\":\"XYZ-type robot knowledge\"}]";
        String jsonResultActual = service.getSingleSkill(db);
        assertEquals(jsonResultExpected, jsonResultActual);
    }

    public static void main(String args[]) {
        junit.swingui.TestRunner.run(ShowSpecificationsEWOBrowseServiceJUnitTestJSP.class);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ShowSpecificationsEWOBrowseServiceJUnitTestJSP.class);
        return suite;
    }

}
