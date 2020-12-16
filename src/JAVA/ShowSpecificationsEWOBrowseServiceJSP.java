package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings({"DuplicatedCode", "SqlResolve"})
public class ShowSpecificationsEWOBrowseServiceJSP {
    boolean removeComma = false;
    private Connection con;

    public String getShowSpecificationsEWOBrowseToJSONJSP(MySqlDbConnection db, int activity, int week) {
        String showSpecificationsEWOBrowseJSONFormat = "{\"id\":\"{ID}\",\"work_note\":\"{WORK_NOTE}\",\"int_des\":\"{INT_DES}\",\"id_activity\":\"{ID_ACTIVITY}\",\"week_activity\":\"{WEEK_ACTIVITY}\",\"ewo_activity\":\"{EWO_ACTIVITY}\",\"estimate_tr\":\"{ESTIMATE_TR}\"}";
        StringBuilder showSpecificationsEWOJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //fetch specifications of EWO
            rs = stmt.executeQuery("SELECT * FROM specifications WHERE id_activity=" + activity + " AND week_activity = " + week);
            int i = 0;
            //JSON realization
            while (rs.next()) {
                removeComma = true;
                JSONRow = showSpecificationsEWOBrowseJSONFormat.replace("{ID}", rs.getString(1));
                JSONRow = JSONRow.replace("{WORK_NOTE}", Util.utf8Encode(rs.getString(2)));
                JSONRow = JSONRow.replace("{INT_DES}", Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{WEEK_ACTIVITY}", Util.utf8Encode(rs.getString(5)));
                //fetch name of activity
                JSONRow = JSONRow.replace("{ID_ACTIVITY}", Util.utf8Encode(this.getActivityNameById(rs.getInt(4))));
                JSONRow = JSONRow.replace("{EWO_ACTIVITY}", Util.utf8Encode(String.valueOf(rs.getBoolean(6))));
                //fetch estimation time
                JSONRow = JSONRow.replace("{ESTIMATE_TR}", Util.utf8Encode(String.valueOf(this.getEstimate(rs.getInt(1), rs.getInt(5), rs.getBoolean(6)))));
                showSpecificationsEWOJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removeComma)
            return "[" + Util.removeLastChar(showSpecificationsEWOJSONResult.toString()) + "]";
        else
            return "[" + showSpecificationsEWOJSONResult + "]";
    }

    private String getActivityNameById(int activityId) {
        String activityName = "";
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //fetch name of activity
            rs = stmt.executeQuery("SELECT site FROM activity WHERE id =" + activityId);
            //set variabile
            if (rs.next())
                activityName = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "EWO " + activityId + " - " + activityName;
    }

    private int getEstimate(int id, int week, boolean ewo) {
        int estimatedTime = 0;
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //fetch estimated time
            rs = stmt.executeQuery("SELECT estim_time FROM activity WHERE id= " + id + " and week= " + week + " and ewo= " + ewo);
            //set variable
            if (rs.next())
                estimatedTime = rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return estimatedTime;
    }

    public String getSkills(MySqlDbConnection db) {
        String skillJSONFormat = "{\"id\":\"{ID}\",\"skill\":\"{SKILL}\"}";
        StringBuilder skillJSONResult = new StringBuilder();
        String JSONRow;
        boolean removeComma = false;
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //fetch skill
            rs = stmt.executeQuery("SELECT * FROM skill");
            //JSON realization
            while (rs.next()) {
                removeComma = true;
                JSONRow = skillJSONFormat.replace("{ID}", Util.utf8Encode(String.valueOf(rs.getInt(1))));
                JSONRow = JSONRow.replace("{SKILL}", Util.utf8Encode(rs.getString(2)));
                skillJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removeComma)
            return "[" + Util.removeLastChar(skillJSONResult.toString()) + "]";
        else
            return "[" + skillJSONResult + "]";
    }

    public void insertInNeed(MySqlDbConnection db, int specifications, String stringSkills) {
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            StringBuilder skillExtracted = new StringBuilder();
            ArrayList<String> allSkills = new ArrayList<>();
            for (int i = 0; i < stringSkills.length(); i++) {
                if (!String.valueOf(stringSkills.charAt(i)).equals("-"))
                    skillExtracted.append(stringSkills.charAt(i));
                else {
                    allSkills.add(skillExtracted.toString());
                    skillExtracted = new StringBuilder();
                }
                if (i == stringSkills.length() - 1)
                    allSkills.add(skillExtracted.toString());
            }
            //delete all need occurencies of specifc EWO
            stmt.executeUpdate("DELETE FROM need WHERE specifications= " + specifications);
            for (String skill : allSkills) {
                //insert new need occurencies of specifc EWO
                stmt.executeUpdate("INSERT INTO need (specifications, skill) VALUES (" + specifications + ", " + skill + ")");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
