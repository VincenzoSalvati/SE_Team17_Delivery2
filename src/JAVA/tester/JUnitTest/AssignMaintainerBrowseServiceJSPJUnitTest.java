package JAVA.tester.JUnitTest;

import JAVA.AssignMaintainerBrowseServiceJSP;
import JAVA.MySqlDbConnection;
import junit.framework.TestCase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AssignMaintainerBrowseServiceJSPJUnitTest extends TestCase {

    private AssignMaintainerBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(AssignMaintainerBrowseServiceJSPJUnitTest.class);
    }

    @Order(1)
    protected void setUp() {
        //Database initialization
        db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new AssignMaintainerBrowseServiceJSP();
    }

    @Order(3)
    protected void tearDown() {
        service = null;
    }

    @Order(2)
    public final void testGetAssignMaintainerBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"0\",\"maint\":\"Pippo\",\"skills\":\"2/4\",\"mon\":\"50\",\"tue\":\"51\",\"wed\":\"53\",\"thu\":\"42\",\"fri\":\"50\",\"sat\":\"46\",\"sun\":\"48\"}," +
                "{\"id\":\"1\",\"maint\":\"Paperino\",\"skills\":\"3/4\",\"mon\":\"45\",\"tue\":\"44\",\"wed\":\"47\",\"thu\":\"32\",\"fri\":\"50\",\"sat\":\"44\",\"sun\":\"39\"}," +
                "{\"id\":\"2\",\"maint\":\"Topolino\",\"skills\":\"3/4\",\"mon\":\"48\",\"tue\":\"47\",\"wed\":\"52\",\"thu\":\"47\",\"fri\":\"46\",\"sat\":\"47\",\"sun\":\"38\"}]";
        String jsonResultActual = service.getAssignMaintainerBrowseToJSONJSP(db, 2, 1, 2);
        assertEquals(jsonResultExpected, jsonResultActual);
        String jsonResultActual2 = service.getAssignMaintainerBrowseToJSONJSP(db, 2, 1, 2);
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

}
