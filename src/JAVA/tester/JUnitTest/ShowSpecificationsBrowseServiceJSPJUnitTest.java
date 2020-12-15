package JAVA.tester.JUnitTest;

import JAVA.tester.MySqlDbConnection;
import JAVA.ShowSpecificationsBrowseServiceJSP;
import JAVA.tester._0_SetDatabaseTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ShowSpecificationsBrowseServiceJSPJUnitTest extends TestCase {

    private ShowSpecificationsBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(ShowSpecificationsBrowseServiceJSPJUnitTest.class);
    }

    public static Test suite() {
        return new TestSuite(ShowSpecificationsBrowseServiceJSPJUnitTest.class);
    }

    protected void setUp() {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new ShowSpecificationsBrowseServiceJSP();
    }

    protected void tearDown() {
        service = null;
    }

    public final void testGetShowSpecificationsBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"0\",\"work_note\":\"The plant is closed from 00/00/20 to 00/00/20; On the remaining days, it is possible to intervene only after 10:00\",\"int_des\":\"Replacement of robot 20 welding cables\",\"id_activity\":\"0 - Fisciano - Molding\",\"week_activity\":\"1\",\"skill\":\"- PAV Certification<br>- Electrical Maintainance<br>- Knowledge of cables types<br>- XYZ-type robot knowledge<br>\"}]";
        String jsonResultActual = service.getShowSpecificationsBrowseToJSONJSP(db, 0, 1);
        assertEquals(jsonResultExpected, jsonResultActual);
        String jsonResultActual2 = service.getShowSpecificationsBrowseToJSONJSP(db, 0, 1);
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

}
