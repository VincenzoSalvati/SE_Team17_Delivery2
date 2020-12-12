package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowEWOBrowseService;

public class ShowEWOBrowseServiceTester {

    public static void main(String[] args) {
        String jsonResult;

        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        ShowEWOBrowseService service = new ShowEWOBrowseService();
        jsonResult = service.getShowEWOBrowseToJSON(db);

        System.out.println(jsonResult);
    }

}
