package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings("DuplicatedCode")
public class ShowSpecificationsEWOBrowseServiceJSP {

    private Connection con;

    public String getShowSpecificationsEWOBrowseToJSONJSP(MySqlDbConnection db, int activity, int week) {

        String specificationsBrowseEWOJSONFormat = "{\"id\":\"{ID}\",\"work_note\":\"{WORK_NOTE}\",\"int_des\":\"{INT_DES}\",\"id_activity\":\"{ID_ACTIVITY}\",\"week_activity\":\"{WEEK_ACTIVITY}\",\"ewo_activity\":\"{EWO_ACTIVITY}\",\"estimate_tr\":\"{ESTIMATE_TR}\"}";
        StringBuilder specificationsBrowseEWOJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();

        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM specifications WHERE id_activity=" + activity + " AND week_activity = " + week);
            int i = 0;
            while (rs.next()) {
                JSONRow = specificationsBrowseEWOJSONFormat.replace("{ID}", rs.getString(1));
                JSONRow = JSONRow.replace("{WORK_NOTE}", Util.utf8Encode(rs.getString(2)));
                JSONRow = JSONRow.replace("{INT_DES}", Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{WEEK_ACTIVITY}", Util.utf8Encode(rs.getString(5)));
                JSONRow = JSONRow.replace("{ID_ACTIVITY}", Util.utf8Encode(this.getActivityNameById(rs.getInt(4))));
                JSONRow = JSONRow.replace("{EWO_ACTIVITY}", Util.utf8Encode(String.valueOf(rs.getBoolean(6))));
                JSONRow = JSONRow.replace("{ESTIMATE_TR}", Util.utf8Encode(String.valueOf(this.getEstimate(rs.getInt(1), rs.getInt(5), rs.getBoolean(6)))));
                specificationsBrowseEWOJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "[" + Util.removeLastChar(specificationsBrowseEWOJSONResult.toString()) + "]";

    }

    private int getEstimate(int id, int week, boolean ewo) {

        int et = 0;
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT estim_time FROM activity WHERE id= " + id + " and week= " + week + " and ewo= " + ewo);
            if (rs.next())
                et = rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return et;

    }

    private String getActivityNameById(int activityId) {

        String activityName = "";
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT site FROM activity WHERE id =" + activityId);
            if (rs.next())
                activityName = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "EWO " + activityId + " - " + activityName;

    }

    public String getSingleSkill(MySqlDbConnection db) {

        String specificationsBrowseEWOJSONFormat = "{\"id\":\"{ID}\",\"skill\":\"{SKILL}\"}";
        StringBuilder specificationsBrowseEWOJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();

        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM skill");
            while (rs.next()) {
                JSONRow = specificationsBrowseEWOJSONFormat.replace("{ID}", Util.utf8Encode(String.valueOf(rs.getInt(1))));
                JSONRow = JSONRow.replace("{SKILL}", Util.utf8Encode(rs.getString(2)));
                specificationsBrowseEWOJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "[" + Util.removeLastChar(specificationsBrowseEWOJSONResult.toString()) + "]";

    }

    public void insertInNeed(MySqlDbConnection db, int ids, String ss) {

        this.con = db.connect();

        try {
            Statement stmt = this.con.createStatement();

            String prova = "";
            ArrayList<String> als = new ArrayList<>();

            for (int i = 0; i < ss.length(); i++) {
                if (!String.valueOf(ss.charAt(i)).equals("-"))
                    prova = prova + ss.charAt(i);
                else {
                    als.add(prova);
                    prova = "";
                }
                if (i == ss.length() - 1)
                    als.add(prova);
            }

            stmt.executeUpdate("DELETE FROM need WHERE specifications= " + ids);

            for (int i = 0; i < als.size(); i++) {
                stmt.executeUpdate("INSERT INTO need (specifications, skill) VALUES (" + ids + ", " + als.get(i) + ")");
            }

            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
