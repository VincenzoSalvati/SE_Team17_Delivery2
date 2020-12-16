package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings({"SqlResolve", "FieldCanBeLocal"})
public class ShowActivitiesEWOCountBrowseService {
    boolean removeComma = false;
    private Connection con;

    public String getShowActivitiesEWOCountBrowseToJSON(MySqlDbConnection db) {
        String showActivitiesEWOCountBrowseJSONFormat = "{\"ewo_count\":\"{EWO_COUNT}\"}";
        StringBuilder showActivitiesEWOCountJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //fetch EWO
            rs = stmt.executeQuery("SELECT count(*) FROM activity WHERE ewo=true AND week=1 AND id NOT IN (SELECT s.id_activity FROM specifications as s, inprogress as i WHERE s.id=i.specifications)");
            //JSON realization
            while (rs.next()) {
                removeComma = true;
                JSONRow = showActivitiesEWOCountBrowseJSONFormat.replace("{EWO_COUNT}", String.valueOf(rs.getInt(1)));
                showActivitiesEWOCountJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removeComma)
            return "[" + Util.removeLastChar(showActivitiesEWOCountJSONResult.toString()) + "]";
        else
            return "[" + showActivitiesEWOCountJSONResult + "]";
    }
}
