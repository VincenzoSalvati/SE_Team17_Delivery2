package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowEWOBrowseServiceJSP;

public class ShowActivitiesEWOBrowseServiceJSPTester {

    public static void main(String[] args) {
        String jsonResult;

        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        ShowEWOBrowseServiceJSP service = new ShowEWOBrowseServiceJSP();
        jsonResult = service.getShowEWOBrowseToJSONJSP(db, 1);

        System.out.println(jsonResult);
    }

}
