package JAVA.tester.JUnitTest;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsEWOBrowseServiceJSP;
import JAVA.tester._0_SetDatabaseTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ShowSpecificationsEWOBrowseServiceJSPJUnitTest extends TestCase {

    private ShowSpecificationsEWOBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(ShowSpecificationsEWOBrowseServiceJSPJUnitTest.class);
    }

    public static Test suite() {
        return new TestSuite(ShowSpecificationsEWOBrowseServiceJSPJUnitTest.class);
    }

    protected void setUp() {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new ShowSpecificationsEWOBrowseServiceJSP();
    }

    protected void tearDown() {
        service = null;
    }

    public final void testGetShowSpecificationsBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"4\",\"work_note\":\"The plant is closed from 00/00/20 to 00/00/20; On the remaining days, it is possible to intervene only after 10:00\",\"int_des\":\"Replacement of robot 20 welding cables\",\"id_activity\":\"EWO 4 - Fisciano - Molding\",\"week_activity\":\"1\",\"ewo_activity\":\"true\",\"estimate_tr\":\"120\"}]";
        String jsonResultActual = service.getShowSpecificationsEWOBrowseToJSONJSP(db, 4, 1);
        assertEquals(jsonResultExpected, jsonResultActual);
        String jsonResultActual2 = service.getShowSpecificationsEWOBrowseToJSONJSP(db, 1, 1);
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

    public final void testGetSingleSkill() {
        String jsonResultExpected = "[{\"id\":\"1\",\"skill\":\"Electrical Maintainance\"},{\"id\":\"2\",\"skill\":\"Knowledge of cables types\"},{\"id\":\"4\",\"skill\":\"Knowledge of robot workstation 23\"},{\"id\":\"0\",\"skill\":\"PAV Certification\"},{\"id\":\"3\",\"skill\":\"XYZ-type robot knowledge\"}]";
        String jsonResultActual = service.getSkills(db);
        assertEquals(jsonResultExpected, jsonResultActual);
    }

}
