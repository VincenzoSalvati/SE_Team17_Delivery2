package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowInProgressEWOBrowseServiceJSP;

public class ShowInProgressEWOBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowInProgressEWOBrowseServiceJSP service = new ShowInProgressEWOBrowseServiceJSP();
        System.out.println(service.getShowInProgressEWOBrowseToJSONJSP(db, 1));
    }
}
