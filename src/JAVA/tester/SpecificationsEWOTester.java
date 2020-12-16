package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.SpecificationsEWO;

public class SpecificationsEWOTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test update
        SpecificationsEWO service = new SpecificationsEWO(db, 4);
        service.setNew_estimated_rt(200);
        service.setWeek(1);
        service.setEwo(1);
        service.updateEstimateTr();
    }
}
