package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesBrowseServiceJSP;


public class ShowActivitiesBrowseServiceJSPTester {

    public static void main(String[] args) {
        String jsonResult;

        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        ShowActivitiesBrowseServiceJSP service = new ShowActivitiesBrowseServiceJSP();
        jsonResult = service.getShowActivitiesBrowseToJSONJSP(db, 1);

        System.out.println(jsonResult);
    }

}

