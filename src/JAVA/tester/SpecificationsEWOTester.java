package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.SpecificationsEWO;

public class SpecificationsEWOTester {

    public static void main(String[] args) {

        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        SpecificationsEWO service = new SpecificationsEWO(db, 4);
        service.setNew_estimated_rt(444);
        service.setWeek(1);
        service.setEwo(1);
        service.updateEstimateTr();

    }

}