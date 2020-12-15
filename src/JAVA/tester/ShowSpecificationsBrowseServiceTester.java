package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsBrowseService;

public class ShowSpecificationsBrowseServiceTester {

    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowSpecificationsBrowseService service = new ShowSpecificationsBrowseService();
        System.out.println(service.getShowSpecificationsBrowseToJSON(db));
    }

}
