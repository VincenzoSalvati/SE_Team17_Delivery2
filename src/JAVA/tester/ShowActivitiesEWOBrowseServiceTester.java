package JAVA.tester;

import JAVA.ShowEWOBrowseService;

public class ShowActivitiesEWOBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowEWOBrowseService service = new ShowEWOBrowseService();
        System.out.println(service.getShowEWOBrowseToJSON(db));
    }

}
