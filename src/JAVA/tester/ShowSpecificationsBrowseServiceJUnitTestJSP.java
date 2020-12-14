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
        db.setDbPassword("5#bJiogahLzN");
        db.setDbName("Project");
    }

    protected void tearDown() throws Exception {
        service = null;
    }

    public final void testGetShowSpecificationsBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"2\",\"work_note\":\"The plant is closed from 00/00/22 to 00/00/22; On the remaining days, it is possible to intervene only after 12:00 \",\"int_des\":\"Replacement of robot 22 welding cables\",\"id_activity\":\"2 - Morra - Painting\",\"week_activity\":\"1\",\"skill\":\"- PAV Certification- Electrical Maintainance- Knowledge of cables types- Knowledge of robot workstation 23\"}]";
        String jsonResultActual = service.getShowSpecificationsBrowseToJSONJSP(db, 2, 1);
        assertEquals(jsonResultExpected, jsonResultActual);

        String jsonResultActual2 = service.getShowSpecificationsBrowseToJSONJSP(db, 1, 1);
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
