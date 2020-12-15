package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("SqlResolve")
public class ShowActivitiesEWOBrowseServiceJSP {
    private Connection con;

    public String getShowActivitiesEWOBrowseToJSONJSP(MySqlDbConnection db, int week) {

        String activityEWOBrowseJSONFormat = "{\"id\":\"{ID}\",\"area\":\"{AREA}\",\"type\":\"{TYPE}\",\"estim_time\":\"{ESTIM_TIME}\"}";
        StringBuilder specificationsBrowseEWOJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();

        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT id, site, type, estim_time FROM activity WHERE ewo=true AND week=" + week);
            while (rs.next()) {
                JSONRow = activityEWOBrowseJSONFormat.replace("{ID}", Util.utf8Encode(String.valueOf(rs.getInt(1))));
                JSONRow = JSONRow.replace("{AREA}", Util.utf8Encode(rs.getString(2)));
                JSONRow = JSONRow.replace("{TYPE}", Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{ESTIM_TIME}", Util.utf8Encode(String.valueOf(rs.getInt(4))));
                specificationsBrowseEWOJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "[" + Util.removeLastChar(specificationsBrowseEWOJSONResult.toString()) + "]";

    }

}
