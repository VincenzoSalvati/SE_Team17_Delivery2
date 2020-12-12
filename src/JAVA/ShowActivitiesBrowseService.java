package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("SqlResolve")
public class ShowActivitiesBrowseService {

    private Connection con;

    public String getShowActivitiesBrowseToJSON(MySqlDbConnection db) {

        String activityJSONFormat = "{\"id\":\"{ID}\",\"week\":\"{WEEK}\",\"area\":\"{AREA}\",\"type\":\"{TYPE}\",\"estim_time\":\"{ESTIM_TIME}\"}";
        StringBuilder activityJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Activity WHERE ewo=false");
            while (rs.next()) {
                JSONRow = activityJSONFormat.replace("{ID}", Integer.toString(rs.getInt(1)));
                JSONRow = JSONRow.replace("{WEEK}", Integer.toString(rs.getInt(2)));
                JSONRow = JSONRow.replace("{AREA}", Util.utf8Encode(rs.getString(4)));
                JSONRow = JSONRow.replace("{TYPE}", Util.utf8Encode(rs.getString(5)));
                JSONRow = JSONRow.replace("{ESTIM_TIME}", Integer.toString(rs.getInt(6)));
                activityJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "[" + Util.removeLastChar(activityJSONResult.toString()) + "]";
    }

}
