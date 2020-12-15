package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsBrowseServiceJSP;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ShowSpecificationsBrowseServiceJUnitTestJSP extends TestCase {

    private ShowSpecificationsBrowseServiceJSP service;
    private MySqlDbConnection db;

    protected void setUp() throws Exception {
        service = new ShowSpecificationsBrowseServiceJSP();
        db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");
    }

    protected void tearDown() throws Exception {
        service = null;
    }

    public final void testGetShowSpecificationsBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"0\",\"work_note\":\"The plant is closed from 00/00/20 to 00/00/20; On the remaining days, it is possible to intervene only after 10:00 \",\"int_des\":\"Replacement of robot 20 welding cables\",\"id_activity\":\"0 - Fisciano - Molding\",\"week_activity\":\"1\",\"skill\":\"- PAV Certification<br>- Electrical Maintainance<br>- Knowledge of cables types<br>- XYZ-type robot knowledge<br>\"}]";
        String jsonResultActual = service.getShowSpecificationsBrowseToJSONJSP(db, 0, 1);
        assertEquals(jsonResultExpected, jsonResultActual);

        String jsonResultActual2 = service.getShowSpecificationsBrowseToJSONJSP(db, 0, 1);
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

    public static void main(String args[]) {
        junit.swingui.TestRunner.run(ShowSpecificationsBrowseServiceJUnitTestJSP.class);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ShowSpecificationsBrowseServiceJUnitTestJSP.class);
        return suite;
    }

}
