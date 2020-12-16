package JAVA.tester.JUnitTest;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesEWOCountBrowseServiceJSP;
import junit.framework.TestCase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShowActivitiesEWOCountBrowseServiceJSPJUnitTest extends TestCase {
    private ShowActivitiesEWOCountBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(ShowActivitiesEWOCountBrowseServiceJSPJUnitTest.class);
    }

    @Order(1)
    protected void setUp() {
        //Database initialization
        db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new ShowActivitiesEWOCountBrowseServiceJSP();
    }

    @Order(2)
    public final void testGetShowActivitiesEWOCountBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"ewo_count\":\"1\"}]";
        String jsonResultActual = service.getShowActivitiesEWOCountBrowseToJSONJSP(db, 1);
        assertEquals(jsonResultExpected, jsonResultActual);
    }

    @Order(3)
    protected void tearDown() {
        service = null;
    }
}
