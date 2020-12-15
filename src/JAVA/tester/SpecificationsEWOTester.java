package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.SpecificationsEWO;

public class SpecificationsEWOTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test update
        SpecificationsEWO service = new SpecificationsEWO(db, 4);
        service.setNew_estimated_rt(444);
        service.setWeek(1);
        service.setEwo(1);
        service.updateEstimateTr();
    }

}