package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesEWOBrowseServiceJSP;

public class ShowActivitiesEWOBrowseServiceTesterJSP {

    public static void main(String[] args) {

        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        ShowActivitiesEWOBrowseServiceJSP service = new ShowActivitiesEWOBrowseServiceJSP();
        jsonResult = service.getShowActivitiesEWOBrowseToJSONJSP(db, 1);
        System.out.println(jsonResult);

    }

}
