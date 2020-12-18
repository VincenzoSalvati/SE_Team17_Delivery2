package JAVA.tester.JUnitTest;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsEWOBrowseServiceJSP;
import junit.framework.TestCase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShowSpecificationsEWOBrowseServiceJSPJUnitTest extends TestCase {
    private ShowSpecificationsEWOBrowseServiceJSP service;
    private MySqlDbConnection db;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(ShowSpecificationsEWOBrowseServiceJSPJUnitTest.class);
    }

    @Order(1)
    protected void setUp() {
        //Database initialization
        db = MySqlDbConnection.getInstance();
        //Service initialization
        service = new ShowSpecificationsEWOBrowseServiceJSP();
    }

    @Order(2)
    public final void testGetShowSpecificationsBrowseToJSONJSP() {
        String jsonResultExpected="[{\"id\":\"4\",\"work_note\":\"Plant stopped from 12:23 p.m. pending intervention. Smoke from the XX4 compressor as a result of loud noise.\",\"int_des\":\"Replacement of robot 20 welding cables\",\"id_activity\":\"EWO 4 - Fisciano - Molding\",\"week_activity\":\"1\",\"ewo_activity\":\"true\",\"estimate_tr\":\"120\"}]";
        String jsonResultActual = service.getShowSpecificationsEWOBrowseToJSONJSP(db, 4, 1);
        assertEquals(jsonResultExpected, jsonResultActual);
        String jsonResultActual2 = service.getShowSpecificationsEWOBrowseToJSONJSP(db, 5, 1);
        assertNotSame(jsonResultExpected, jsonResultActual2);
    }

    @Order(3)
    public final void testGetSingleSkill() {
        String jsonResultExpected = "[{\"id\":\"1\",\"skill\":\"Electrical Maintainance\"}," +
                "{\"id\":\"2\",\"skill\":\"Knowledge of cables types\"}," +
                "{\"id\":\"4\",\"skill\":\"Knowledge of robot workstation 23\"},{\"id\":\"0\",\"skill\":\"PAV Certification\"}," +
                "{\"id\":\"3\",\"skill\":\"XYZ-type robot knowledge\"}]";
        String jsonResultActual = service.getSkills(db);
        assertEquals(jsonResultExpected, jsonResultActual);
    }

    @Order(4)
    protected void tearDown() {
        service = null;
    }
}
