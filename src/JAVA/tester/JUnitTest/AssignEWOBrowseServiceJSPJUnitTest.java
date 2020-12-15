package JAVA.tester.JUnitTest;

import JAVA.AssignEWOBrowseServiceJSP;
import JAVA.MySqlDbConnection;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AssignEWOBrowseServiceJSPJUnitTest extends TestCase {
    private AssignEWOBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(AssignEWOBrowseServiceJSPJUnitTest.class);
    }

    public static Test suite() {
        return new TestSuite(AssignEWOBrowseServiceJSPJUnitTest.class);
    }

    protected void setUp() {
        //Database initialization
        db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new AssignEWOBrowseServiceJSP();
    }

    protected void tearDown() {
        service = null;
    }

    public final void testGetAssignEWOBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"0\",\"maint\":\"Pippo\",\"skills\":\"2/4\",\"h8to9\":\"30\",\"h9to10\":\"20\",\"h10to11\":\"40\",\"h11to12\":\"35\",\"h14to15\":\"10\",\"h15to16\":\"40\",\"h16to17\":\"40\"}," +
                "{\"id\":\"1\",\"maint\":\"Paperino\",\"skills\":\"3/4\",\"h8to9\":\"30\",\"h9to10\":\"30\",\"h10to11\":\"25\",\"h11to12\":\"10\",\"h14to15\":\"40\",\"h15to16\":\"10\",\"h16to17\":\"40\"}," +
                "{\"id\":\"2\",\"maint\":\"Topolino\",\"skills\":\"2/4\",\"h8to9\":\"30\",\"h9to10\":\"20\",\"h10to11\":\"40\",\"h11to12\":\"35\",\"h14to15\":\"25\",\"h15to16\":\"10\",\"h16to17\":\"40\"}]";
        String jsonResultActual = service.getAssignEWOBrowseToJSONJSP(db, 4, 1, 4, "Tuesday");
        assertEquals(jsonResultExpected, jsonResultActual);
        String jsonResultActual2 = service.getAssignEWOBrowseToJSONJSP(db, 5, 1, 5, "Tuesday");
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

}
