package JAVA;

import JAVA.tester.MySqlDbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("SqlResolve")
public class ShowEWOBrowseServiceJSP {
    int id_EWO;
    String area;
    String type;
    int estim_time;
    private Connection con;

    public String getShowEWOBrowseToJSONJSP(MySqlDbConnection db, int week) {

        String EWOJSONFormat = "{\"id\":\"{Id_EWO}\",\"area\":\"{Area}\",\"type\":\"{Type}\",\"estim_time\":\"{Estim_Time}\",\"department\":\"{Department}\",\"maintainer\":\"{Maintainer}\",\"state\":\"{State}\"}";
        StringBuilder EWOJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT specifications, stateDep, stateMaint, stateSpecif FROM InProgress WHERE specifications IN (SELECT s.id FROM specifications as s, activity as a WHERE s.id_activity = a.id AND s.week_activity=a.week AND a.ewo=true AND a.week=" + week + ")");
            while (rs.next()) {
                this.getEWO(rs.getInt(1), week);
                JSONRow = EWOJSONFormat.replace("{Id_EWO}", Integer.toString(id_EWO));
                JSONRow = JSONRow.replace("{Area}", Util.utf8Encode(area));
                JSONRow = JSONRow.replace("{Type}", Util.utf8Encode(type));
                JSONRow = JSONRow.replace("{Estim_Time}", Integer.toString(estim_time));
                JSONRow = JSONRow.replace("{Department}", Util.utf8Encode(rs.getString(2)));
                JSONRow = JSONRow.replace("{Maintainer}", Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{State}", Util.utf8Encode(rs.getString(4)));
                EWOJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "[" + Util.removeLastChar(EWOJSONResult.toString()) + "]";
    }

    //prelievo EWO
    private void getEWO(int specifications, int week) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT a.id, a.site, a.type, a.estim_time FROM specifications as s, activity as a WHERE s.id_activity = a.id AND s.week_activity=a.week AND a.ewo=true AND a.week=" + week + " AND s.id=" + specifications);
            while (rs.next()) {
                id_EWO = rs.getInt(1);
                area = rs.getString(2);
                type = rs.getString(3);
                estim_time = rs.getInt(4);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
