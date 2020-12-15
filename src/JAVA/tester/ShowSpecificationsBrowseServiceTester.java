package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsBrowseService;

public class ShowSpecificationsBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = new MySqlDbConnection();
        _0_SetDatabaseTest set = new _0_SetDatabaseTest();
        set.setDatabase(db);
        //Test JSON
        ShowSpecificationsBrowseService service = new ShowSpecificationsBrowseService();
        System.out.println(service.getShowSpecificationsBrowseToJSON(db));
    }

}
