package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsEWOBrowseServiceJSP;

public class ShowSpecificationsEWOBrowseServiceTesterJSP {

    public static void main(String[] args) {

        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        ShowSpecificationsEWOBrowseServiceJSP service = new ShowSpecificationsEWOBrowseServiceJSP();
        jsonResult = service.getShowSpecificationsEWOBrowseToJSONJSP(db, 4, 1);
        String jsonResult2 = service.getSingleSkill(db, 4);

        System.out.println(jsonResult);
        System.out.println(jsonResult2);

    }

}
