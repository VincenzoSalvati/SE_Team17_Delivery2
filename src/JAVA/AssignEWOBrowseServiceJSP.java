package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings({"SqlResolve", "SameParameterValue"})
public class AssignEWOBrowseServiceJSP {
    ArrayList<Integer> id_maint = new ArrayList<>();
    ArrayList<String> name_maint = new ArrayList<>();
    ArrayList<Integer> skills = new ArrayList<>();
    ArrayList<Integer> h8to9 = new ArrayList<>();
    ArrayList<Integer> h9to10 = new ArrayList<>();
    ArrayList<Integer> h10to11 = new ArrayList<>();
    ArrayList<Integer> h11to12 = new ArrayList<>();
    ArrayList<Integer> h14to15 = new ArrayList<>();
    ArrayList<Integer> h15to16 = new ArrayList<>();
    ArrayList<Integer> h16to17 = new ArrayList<>();
    boolean removeComma = false;
    private Connection con;

    public String getAssignEWOBrowseToJSONJSP(MySqlDbConnection db, int week, int specifications, String day) {
        String assignEWOBrowseJSONFormat = "{\"id\":\"{ID}\",\"maint\":\"{MAINT}\",\"skills\":\"{SKILLS}\",\"h8to9\":\"{h8to9}\",\"h9to10\":\"{h9to10}\",\"h10to11\":\"{h10to11}\",\"h11to12\":\"{h11to12}\",\"h14to15\":\"{h14to15}\",\"h15to16\":\"{h15to16}\",\"h16to17\":\"{h16to17}\"}";
        StringBuilder assignEWOJSONResult = new StringBuilder();
        String JSONRow;
        int skillTot;
        int i;
        con = db.connect();
        try {
            //fetch maintainers
            getMaintainer();
            //count maintainers' skills
            getSkill(specifications);
            //count skills of specification
            skillTot = getSkillTot(specifications);
            if (skillTot == -1) return "";
            //initialization array of hours
            for (i = 0; i < id_maint.size(); i++) {
                h8to9.add(0);
                h9to10.add(0);
                h10to11.add(0);
                h11to12.add(0);
                h14to15.add(0);
                h15to16.add(0);
                h16to17.add(0);
            }
            //fetch availability
            getAvailabilityHours(day, week);
            //JSON realization
            for (i = 0; i < id_maint.size(); i++) {
                removeComma = true;
                JSONRow = assignEWOBrowseJSONFormat.replace("{ID}", Integer.toString(id_maint.get(i)));
                JSONRow = JSONRow.replace("{MAINT}", name_maint.get(i));
                JSONRow = JSONRow.replace("{SKILLS}", skills.get(i) + "/" + skillTot);
                JSONRow = JSONRow.replace("{h8to9}", Integer.toString(h8to9.get(i)));
                JSONRow = JSONRow.replace("{h9to10}", Integer.toString(h9to10.get(i)));
                JSONRow = JSONRow.replace("{h10to11}", Integer.toString(h10to11.get(i)));
                JSONRow = JSONRow.replace("{h11to12}", Integer.toString(h11to12.get(i)));
                JSONRow = JSONRow.replace("{h14to15}", Integer.toString(h14to15.get(i)));
                JSONRow = JSONRow.replace("{h15to16}", Integer.toString(h15to16.get(i)));
                JSONRow = JSONRow.replace("{h16to17}", Integer.toString(h16to17.get(i)));
                assignEWOJSONResult.append(JSONRow).append(",");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removeComma)
            return "[" + Util.removeLastChar(assignEWOJSONResult.toString()) + "]";
        else
            return "[" + assignEWOJSONResult + "]";
    }

    private void getMaintainer() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            //fetch maintainers
            rs = stmt.executeQuery("SELECT id, name FROM maintainer");
            //set variabiles
            while (rs.next()) {
                id_maint.add(rs.getInt(1));
                name_maint.add(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void getAvailabilityHours(String day, int week) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            //fetch hours available
            for (int i = 0; i < id_maint.size(); i++) {
                rs = stmt.executeQuery("SELECT a.avail_8to9, a.avail_9to10, a.avail_10to11, a.avail_11to12, a.avail_14to15, a.avail_15to16, a.avail_16to17 FROM maintainer AS m, availability AS a WHERE a.id_maint=m.id AND a.id_maint=" + id_maint.get(i) + " AND a.day=\"" + day + "\"" + " AND a.week=\"" + week + "\"");
                //set variabiles
                while (rs.next()) {
                    h8to9.set(i, rs.getInt(1));
                    h9to10.set(i, rs.getInt(2));
                    h10to11.set(i, rs.getInt(3));
                    h11to12.set(i, rs.getInt(4));
                    h14to15.set(i, rs.getInt(5));
                    h15to16.set(i, rs.getInt(6));
                    h16to17.set(i, rs.getInt(7));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getSkill(int specifications) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            //count maintainers' skills
            for (int i : id_maint) {
                rs = stmt.executeQuery("SELECT COUNT(*) FROM skill_maintainer_view AS sm, skill_need_view AS sn WHERE sm.skill = sn.skill AND sm.id=" + i + " AND sn.specifications=" + specifications);
                //set variabile
                while (rs.next()) {
                    skills.add(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private int getSkillTot(int specifications) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            //count skills of specification
            rs = stmt.executeQuery("SELECT COUNT(*) FROM need WHERE specifications=" + specifications);
            //set variabile
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}