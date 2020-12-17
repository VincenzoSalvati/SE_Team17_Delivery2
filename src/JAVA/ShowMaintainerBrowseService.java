package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings({"SqlResolve", "FieldCanBeLocal"})
public class ShowMaintainerBrowseService {
    boolean removeComma = false;
    private Connection con;

    public String getShowMaintainerBrowseToJSONJSP(MySqlDbConnection db, int id_maint) {
        String showMaintainerBrowseJSONFormat = "{\"id_maintainer\":\"{ID_MAINT}\",\"week_activity\":\"{WEEK_ACTIVITY}\"," +
                                                "\"site\":\"{SITE}\",\"type\":\"{TYPE}\",\"estim_time\":\"{ESTIM_TIME}\",\"int_des\":\"{INT_DES}\"," +
                                                "\"work_note\":\"{WORK_NOTE}\",\"ewo_activity\":\"{EWO_ACTIVITY}\"}";
        StringBuilder showMaintainerJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //fetch activities
            rs = stmt.executeQuery("SELECT * FROM maintainer_activity_assignment where maintainer=" + id_maint);
            //JSON realization
            while (rs.next()) {
                removeComma = true;
                JSONRow = showMaintainerBrowseJSONFormat.replace("{ID_MAINT}", Integer.toString(rs.getInt(1)));
                JSONRow = JSONRow.replace("{WEEK_ACTIVITY}", Integer.toString(rs.getInt(2)));
                JSONRow = JSONRow.replace("{SITE}", Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{TYPE}", Util.utf8Encode(rs.getString(4)));
                JSONRow = JSONRow.replace("{ESTIM_TIME}", Integer.toString(rs.getInt(5)));
                JSONRow = JSONRow.replace("{INT_DES}", Util.utf8Encode(rs.getString(6)));
                JSONRow = JSONRow.replace("{WORK_NOTE}", Util.utf8Encode(rs.getString(7)));
                JSONRow = JSONRow.replace("{EWO_ACTIVITY}", Integer.toString(rs.getInt(8)));
                showMaintainerJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removeComma)
            return "[" + Util.removeLastChar(showMaintainerJSONResult.toString()) + "]";
        else
            return "[" + showMaintainerJSONResult + "]";
    }
}
