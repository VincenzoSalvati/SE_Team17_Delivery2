package JAVA.tester;

import JAVA.ShowActivitiesBrowseServiceJSP;

public class ShowActivitiesBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowActivitiesBrowseServiceJSP service = new ShowActivitiesBrowseServiceJSP();
        System.out.println(service.getShowActivitiesBrowseToJSONJSP(db, 1));
    }

}

