package JAVA;

import java.sql.Connection;
import java.sql.PreparedStatement;

@SuppressWarnings("SqlResolve")
public class SpecificationsEWO {

    private final Connection con;
    public int dbOperationStatusCode;
    public String dbOperationMessage;
    private int id;
    private int new_estimate_tr;
    private String new_int_des;
    private int week;
    private int ewo;

    public SpecificationsEWO(MySqlDbConnection db, int id) {
        this.con = db.connect();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getEwo() {
        return ewo;
    }

    public void setEwo(int ewo) {
        this.ewo = ewo;
    }

    public int getNew_estimate_tr() {
        return new_estimate_tr;
    }

    public void setNew_estimated_rt(int new_estimate_tr) {
        this.new_estimate_tr = new_estimate_tr;
    }

    public String getNew_int_des() {
        return new_int_des;
    }

    public void setNew_int_des(String new_int_des) {
        this.new_int_des = new_int_des;
    }

    public int getDbOperationStatusCode() {
        return dbOperationStatusCode;
    }

    public String getDbOperationMessageEstimateTr() {
        String specificationsEWOBrowseJSONFormat = "{\"id\":\"{ID}\",\"estimate_tr\":\"{ESTIMATE_TR}\"}";
        String JSONRow = specificationsEWOBrowseJSONFormat.replace("{ID}", Util.utf8Encode(String.valueOf(this.getId())));
        JSONRow = JSONRow.replace("{ESTIMATE_TR}", Util.utf8Encode(String.valueOf(this.getNew_estimate_tr())));
        return "[" + JSONRow + "]";
    }

    public String getDbOperationMessageIntDes() {
        String specificationsEWOBrowseJSONFormat = "{\"id\":\"{ID}\",\"int_des\":\"{INT_DES}\"}";
        String JSONRow = specificationsEWOBrowseJSONFormat.replace("{ID}", Util.utf8Encode(String.valueOf(this.getId())));
        JSONRow = JSONRow.replace("{INT_DES}", Util.utf8Encode(this.getNew_int_des()));
        return "[" + JSONRow + "]";
    }

    public void updateEstimateTr() {
        try {
            String query = "UPDATE activity SET " +
                    "estim_time = ? WHERE id= ? and week= ? and ewo= ?";
            PreparedStatement preparedStmt = this.con.prepareStatement(query);
            preparedStmt.setInt(1, this.new_estimate_tr);
            preparedStmt.setInt(2, this.id);
            preparedStmt.setInt(3, this.week);
            preparedStmt.setInt(4, this.ewo);
            preparedStmt.execute();
            this.dbOperationStatusCode = 0;
            this.dbOperationMessage = "Record updated";
        } catch (Exception e) {
            this.dbOperationStatusCode = 1;
            this.dbOperationMessage = "Record not updated: " + e.getMessage();
        }
    }

    public void updateIntDes() {
        try {
            String query = "UPDATE specifications SET " +
                    "int_des = ? WHERE id= ?";
            PreparedStatement preparedStmt = this.con.prepareStatement(query);
            preparedStmt.setString(1, this.new_int_des);
            preparedStmt.setInt(2, this.id);
            preparedStmt.execute();
            this.dbOperationStatusCode = 0;
            this.dbOperationMessage = "Record updated";
        } catch (Exception e) {
            this.dbOperationStatusCode = 1;
            this.dbOperationMessage = "Record not updated: " + e.getMessage();
        }
    }

}
