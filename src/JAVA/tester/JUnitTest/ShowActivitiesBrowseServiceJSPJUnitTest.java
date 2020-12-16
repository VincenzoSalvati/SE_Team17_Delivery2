package JAVA.tester.JUnitTest;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesBrowseServiceJSP;
import junit.framework.TestCase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShowActivitiesBrowseServiceJSPJUnitTest extends TestCase {
    private ShowActivitiesBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(ShowActivitiesBrowseServiceJSPJUnitTest.class);
    }

    @Order(1)
    protected void setUp() {
        //Database initialization
        db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new ShowActivitiesBrowseServiceJSP();
    }

    @Order(2)
    public final void testGetShowActivitiesBrowseToJSONJSP() {
        String jsonResultExpected = "[{\"id\":\"0\",\"area\":\"Fisciano - Molding\",\"type\":\"Mechanical\",\"estim_time\":\"120\"}," +
                "{\"id\":\"1\",\"area\":\"Nusco - Carpentery\",\"type\":\"Electric\",\"estim_time\":\"30\"}," +
                "{\"id\":\"2\",\"area\":\"Morra - Painting\",\"type\":\"Hydraulic\",\"estim_time\":\"250\"}," +
                "{\"id\":\"3\",\"area\":\"Fisciano - Molding\",\"type\":\"Electronics\",\"estim_time\":\"90\"}]";
        String jsonResultActual = service.getShowActivitiesBrowseToJSONJSP(db, 1);
        assertEquals(jsonResultExpected, jsonResultActual);
    }

    @Order(3)
    protected void tearDown() {
        service = null;
    }
}

