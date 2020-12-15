package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsBrowseServiceJSP;

public class ShowSpecificationsBrowseServiceJSPTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        ShowSpecificationsBrowseServiceJSP service = new ShowSpecificationsBrowseServiceJSP();
        System.out.println(service.getShowSpecificationsBrowseToJSONJSP(db, 2, 1));
    }

}
