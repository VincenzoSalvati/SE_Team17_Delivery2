package JAVA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("SqlResolve")
public class InProgress {
    private final Connection con;
    public int dbOperationStatusCode;
    public String dbOperationMessage;
    private int specifications;
    private int maintainer;
    private String stateSpecif;
    private String stateDep;
    private String stateMaint;
    private boolean removeComma=false;


    public InProgress(MySqlDbConnection db) {
        this.con = db.connect();
    }

    public int getSpecifications() {
        return specifications;
    }

    public void setSpecifications(int specifications) {
        this.specifications = specifications;
    }

    public int getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(int maintainer) {
        this.maintainer = maintainer;
    }

    public String getStateSpecif() {
        return stateSpecif;
    }

    public void setStateSpecif(String stateSpecif) {
        this.stateSpecif = stateSpecif;
    }

    public String getStateDep() {
        return stateDep;
    }

    public void setStateDep(String stateDep) {
        this.stateDep = stateDep;
    }

    public String getStateMaint() {
        return stateMaint;
    }

    public void setStateMaint(String stateMaint) {
        this.stateMaint = stateMaint;
    }

    public int getDbOperationStatusCode() {
        return this.dbOperationStatusCode;
    }

    public String getDbOperationMessage() {
        return this.dbOperationMessage;
    }

    public String getResults() {
        String inProgressBrowseJSONFormat = "{\"result_code\":\"{code}\"}";
        String JSONRow = inProgressBrowseJSONFormat.replace("{code}", this.getDbOperationMessage());
        return "[" + JSONRow + "]";
    }

    public void insert() {
        try {
            String query = "INSERT INTO inprogress (specifications,maintainer,stateSpecif,stateMaint,stateDep)" +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement preparedStmt = this.con.prepareStatement(query);
            preparedStmt.setInt(1, this.getSpecifications());
            preparedStmt.setInt(2, this.getMaintainer());
            preparedStmt.setString(3, this.getStateSpecif());
            preparedStmt.setString(4, this.getStateDep());
            preparedStmt.setString(5, this.getStateMaint());
            preparedStmt.execute();
            this.dbOperationStatusCode = 0;
            this.dbOperationMessage = "Record inserted";
        } catch (Exception e) {
            this.dbOperationStatusCode = 1;
            this.dbOperationMessage = "Record not inserted: " + e.getMessage();
        }
    }
}





