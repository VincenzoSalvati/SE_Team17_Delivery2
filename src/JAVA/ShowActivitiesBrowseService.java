package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings({"SqlResolve", "FieldCanBeLocal"})
public class ShowActivitiesBrowseService {
    boolean removeComma = false;
    private Connection con;

    public String getShowActivitiesBrowseToJSON(MySqlDbConnection db) {
        String showActivityBrowseJSONFormat = "{\"id\":\"{ID}\",\"area\":\"{AREA}\",\"type\":\"{TYPE}\",\"estim_time\":\"{ESTIM_TIME}\"}";
        StringBuilder showActivityJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //fetch activities
            rs = stmt.executeQuery("SELECT id, site, type, estim_time FROM Activity WHERE ewo=false AND week=" + 1);
            //JSON realization
            while (rs.next()) {
                removeComma = true;
                JSONRow = showActivityBrowseJSONFormat.replace("{ID}", Integer.toString(rs.getInt(1)));
                JSONRow = JSONRow.replace("{AREA}", Util.utf8Encode(rs.getString(2)));
                JSONRow = JSONRow.replace("{TYPE}", Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{ESTIM_TIME}", Integer.toString(rs.getInt(4)));
                showActivityJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removeComma)
            return "[" + Util.removeLastChar(showActivityJSONResult.toString()) + "]";
        else
            return "[" + showActivityJSONResult + "]";
    }
}
