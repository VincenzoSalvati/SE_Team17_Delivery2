package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowMaintainerBrowseService;

public class ShowMaintainerJSPTester {
    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowMaintainerBrowseService service = new ShowMaintainerBrowseService();
        System.out.println(service.getShowMaintainerBrowseToJSONJSP(db, 2));
    }
}
