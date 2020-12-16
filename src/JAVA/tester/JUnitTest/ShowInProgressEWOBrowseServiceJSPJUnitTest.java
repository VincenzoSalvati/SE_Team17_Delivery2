package JAVA.tester.JUnitTest;

import JAVA.MySqlDbConnection;
import JAVA.ShowInProgressEWOBrowseServiceJSP;
import junit.framework.TestCase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShowInProgressEWOBrowseServiceJSPJUnitTest extends TestCase {
    private ShowInProgressEWOBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(ShowInProgressEWOBrowseServiceJSPJUnitTest.class);
    }

    @Order(1)
    protected void setUp() {
        //Database initialization
        db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new ShowInProgressEWOBrowseServiceJSP();
    }

    @Order(2)
    public final void testGetShowActivitiesBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"4\",\"area\":\"Fisciano - Molding\",\"type\":\"Mechanical\",\"estim_time\":\"120\",\"department\":\"Received\",\"maintainer\":\"Sent\",\"state\":\"Closed\"}," +
                "{\"id\":\"5\",\"area\":\"Nusco - Carpentery\",\"type\":\"Electric\",\"estim_time\":\"30\",\"department\":\"Sent\",\"maintainer\":\"Received\",\"state\":\"NotStarted\"}]";
        String jsonResultActual = service.getShowInProgressEWOBrowseToJSONJSP(db, 1);
        assertEquals(jsonResultExpected, jsonResultActual);
    }

    @Order(3)
    protected void tearDown() {
        service = null;
    }
}

