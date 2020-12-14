package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesEWOCountBrowseServiceJSP;

public class ShowActivitiesEWOCountBrowseServiceTesterJSP {

    public static void main(String[] args) {

        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        ShowActivitiesEWOCountBrowseServiceJSP service = new ShowActivitiesEWOCountBrowseServiceJSP();
        jsonResult = service.getShowActivitiesEWOCountBrowseToJSONJSP(db, 1);
        System.out.println(jsonResult);

    }
}
