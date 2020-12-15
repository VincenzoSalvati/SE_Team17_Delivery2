package JAVA;

import JAVA.tester.MySqlDbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("SqlResolve")
public class ShowActivitiesEWOCountBrowseServiceJSP {
    private Connection con;

    public String getShowActivitiesEWOCountBrowseToJSONJSP(MySqlDbConnection db, int week) {

        String activityEWOBrowseJSONFormat = "{\"ewo_count\":\"{EWO_COUNT}\"}";
        StringBuilder specificationsBrowseEWOJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();

        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT count(*) FROM activity WHERE ewo=true AND week=" + week);
            while (rs.next()) {
                JSONRow = activityEWOBrowseJSONFormat.replace("{EWO_COUNT}", String.valueOf(rs.getInt(1)));
                specificationsBrowseEWOJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "[" + Util.removeLastChar(specificationsBrowseEWOJSONResult.toString()) + "]";

    }
}
