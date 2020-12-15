package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowEWOBrowseServiceJSP;

public class ShowActivitiesEWOBrowseServiceTesterJSP {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowEWOBrowseServiceJSP service = new ShowEWOBrowseServiceJSP();
        System.out.println(service.getShowEWOBrowseToJSONJSP(db,1));
    }

}
