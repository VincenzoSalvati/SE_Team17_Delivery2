package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings({"SqlResolve", "FieldCanBeLocal"})
public class ShowActivitiesEWOBrowseService {
    boolean removeComma = false;
    private Connection con;

    public String getShowActivitiesEWOBrowseToJSON(MySqlDbConnection db) {
        String showActivitiesEWOBrowseJSONFormat = "{\"id\":\"{ID}\",\"area\":\"{AREA}\",\"type\":\"{TYPE}\",\"estim_time\":\"{ESTIM_TIME}\"}";
        StringBuilder showActivitiesEWOJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //fetch EWO
            rs = stmt.executeQuery("SELECT id, site, type, estim_time FROM activity WHERE ewo=true AND week=1 AND id NOT IN (SELECT s.id_activity FROM specifications as s, inprogress as i WHERE s.id=i.specifications)");
            //JSON realization
            while (rs.next()) {
                removeComma = true;
                JSONRow = showActivitiesEWOBrowseJSONFormat.replace("{ID}", Util.utf8Encode(String.valueOf(rs.getInt(1))));
                JSONRow = JSONRow.replace("{AREA}", Util.utf8Encode(rs.getString(2)));
                JSONRow = JSONRow.replace("{TYPE}", Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{ESTIM_TIME}", Util.utf8Encode(String.valueOf(rs.getInt(4))));
                showActivitiesEWOJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removeComma)
            return "[" + Util.removeLastChar(showActivitiesEWOJSONResult.toString()) + "]";
        else
            return "[" + showActivitiesEWOJSONResult + "]";
    }
}
