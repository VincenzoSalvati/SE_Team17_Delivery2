package JAVA.tester.JUnitTest;

import JAVA.AssignHoursBrowseServiceJSP;
import JAVA.MySqlDbConnection;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AssignHoursBrowseServiceJSPJUnitTest extends TestCase {

    private AssignHoursBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(AssignHoursBrowseServiceJSPJUnitTest.class);
    }

    public static Test suite() {
        return new TestSuite(AssignHoursBrowseServiceJSPJUnitTest.class);
    }

    protected void setUp() {
        //Database initialization
        db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new AssignHoursBrowseServiceJSP();
    }

    protected void tearDown() {
        service = null;
    }

    public final void testGetAssignHoursBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"0\",\"maint\":\"Pippo\",\"skills\":\"2\",\"h8to9\":\"30\",\"h9to10\":\"30\",\"h10to11\":\"25\",\"h11to12\":\"55\",\"h14to15\":\"10\",\"h15to16\":\"20\",\"h16to17\":\"40\"}]";
        String jsonResultActual = service.getAssignHoursBrowseToJSONJSP(db, 2, 1, 2, 0, "Monday");
        assertEquals(jsonResultExpected, jsonResultActual);
        String jsonResultActual2 = service.getAssignHoursBrowseToJSONJSP(db, 1, 1, 1, 0, "Monday");
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

}
