package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings({"DuplicatedCode", "SqlResolve", "ConstantConditions"})
public class AssignMaintainerBrowseServiceJSP {
    ArrayList<Integer> id_maint = new ArrayList<>();
    ArrayList<String> name_maint = new ArrayList<>();
    ArrayList<Integer> skills = new ArrayList<>();
    ArrayList<Integer> monday = new ArrayList<>();
    ArrayList<Integer> tuesday = new ArrayList<>();
    ArrayList<Integer> wednesday = new ArrayList<>();
    ArrayList<Integer> thursday = new ArrayList<>();
    ArrayList<Integer> friday = new ArrayList<>();
    ArrayList<Integer> saturday = new ArrayList<>();
    ArrayList<Integer> sunday = new ArrayList<>();
    boolean removeComma = false;
    private Connection con;

    @SuppressWarnings("DuplicatedCode")
    public String getAssignMaintainerBrowseToJSONJSP(MySqlDbConnection db, int specifications) {
        String assignMaintainerBrowseJSONFormat = "{\"id\":\"{ID}\",\"maint\":\"{MAINT}\",\"skills\":\"{SKILLS}\",\"mon\":\"{MON}\",\"tue\":\"{TUE}\",\"wed\":\"{WED}\",\"thu\":\"{THU}\",\"fri\":\"{FRI}\",\"sat\":\"{SAT}\",\"sun\":\"{SUN}\"}";
        StringBuilder assignMaintainerJSONResult = new StringBuilder();
        String JSONRow;
        int skillTot;
        int i;
        con = db.connect();
        try {
            //fetch maintainers
            getMaintainer(id_maint, name_maint);
            //count maintainers' skills
            getSkill(id_maint, skills, specifications);
            //count skills of specification
            skillTot = getSkillTot(specifications);
            if (skillTot == -1) return "";
            //initialization array of hours
            for (i = 0; i < id_maint.size(); i++) {
                monday.add(0);
                tuesday.add(0);
                wednesday.add(0);
                thursday.add(0);
                friday.add(0);
                saturday.add(0);
                sunday.add(0);
            }
            //fetch availability
            getAvailability(id_maint, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
            //JSON realization
            for (i = 0; i < id_maint.size(); i++) {
                removeComma = true;
                JSONRow = assignMaintainerBrowseJSONFormat.replace("{ID}", Integer.toString(id_maint.get(i)));
                JSONRow = JSONRow.replace("{MAINT}", name_maint.get(i));
                JSONRow = JSONRow.replace("{SKILLS}", skills.get(i) + "/" + skillTot);
                JSONRow = JSONRow.replace("{MON}", Integer.toString(monday.get(i) * 100 / 420));
                JSONRow = JSONRow.replace("{TUE}", Integer.toString(tuesday.get(i) * 100 / 420));
                JSONRow = JSONRow.replace("{WED}", Integer.toString(wednesday.get(i) * 100 / 420));
                JSONRow = JSONRow.replace("{THU}", Integer.toString(thursday.get(i) * 100 / 420));
                JSONRow = JSONRow.replace("{FRI}", Integer.toString(friday.get(i) * 100 / 420));
                JSONRow = JSONRow.replace("{SAT}", Integer.toString(saturday.get(i) * 100 / 420));
                JSONRow = JSONRow.replace("{SUN}", Integer.toString(sunday.get(i) * 100 / 420));
                assignMaintainerJSONResult.append(JSONRow).append(",");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removeComma)
            return "[" + Util.removeLastChar(assignMaintainerJSONResult.toString()) + "]";
        else
            return "[" + assignMaintainerJSONResult + "]";
    }

    private void getMaintainer(ArrayList<Integer> id_maint, ArrayList<String> name_maint) {
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

    private void getSkill(ArrayList<Integer> id_maint, ArrayList<Integer> skills, int specifications) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            //fetch maintainers' skill and set variabile
            for (int i : id_maint) {
                rs = stmt.executeQuery("SELECT COUNT(*) FROM skill_maintainer_view AS sm, skill_need_view AS sn WHERE sm.skill = sn.skill AND sm.id=" + i + " AND sn.specifications=" + specifications);
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

    private void getAvailability(ArrayList<Integer> id_maint, ArrayList<Integer> monday, ArrayList<Integer> tuesday, ArrayList<Integer> wednesday, ArrayList<Integer> thursday, ArrayList<Integer> friday, ArrayList<Integer> saturday, ArrayList<Integer> sunday) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            //fetch availability and set variabiles
            for (int i = 0; i < id_maint.size(); i++) {
                rs = stmt.executeQuery("SELECT avail_8to9, avail_9to10,avail_10to11,avail_11to12,avail_14to15,avail_15to16,avail_16to17 FROM availability WHERE day=\"Monday\" and id_maint=" + id_maint.get(i));
                while (rs.next()) {
                    monday.set(i, rs.getInt(1) + rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5) + rs.getInt(6) + rs.getInt(7));
                }
                rs = stmt.executeQuery("SELECT avail_8to9, avail_9to10,avail_10to11,avail_11to12,avail_14to15,avail_15to16,avail_16to17 FROM availability WHERE day=\"Tuesday\"and id_maint=" + id_maint.get(i));
                while (rs.next()) {
                    tuesday.set(i, rs.getInt(1) + rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5) + rs.getInt(6) + rs.getInt(7));
                }
                rs = stmt.executeQuery("SELECT avail_8to9, avail_9to10,avail_10to11,avail_11to12,avail_14to15,avail_15to16,avail_16to17 FROM availability WHERE day=\"Wednesday\"and id_maint=" + id_maint.get(i));
                while (rs.next()) {
                    wednesday.set(i, rs.getInt(1) + rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5) + rs.getInt(6) + rs.getInt(7));
                }
                rs = stmt.executeQuery("SELECT avail_8to9, avail_9to10,avail_10to11,avail_11to12,avail_14to15,avail_15to16,avail_16to17 FROM availability WHERE day=\"Thursday\"and id_maint=" + id_maint.get(i));
                while (rs.next()) {
                    thursday.set(i, rs.getInt(1) + rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5) + rs.getInt(6) + rs.getInt(7));
                }
                rs = stmt.executeQuery("SELECT avail_8to9, avail_9to10,avail_10to11,avail_11to12,avail_14to15,avail_15to16,avail_16to17 FROM availability WHERE day=\"Friday\"and id_maint=" + id_maint.get(i));
                while (rs.next()) {
                    friday.set(i, rs.getInt(1) + rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5) + rs.getInt(6) + rs.getInt(7));
                }
                rs = stmt.executeQuery("SELECT avail_8to9, avail_9to10,avail_10to11,avail_11to12,avail_14to15,avail_15to16,avail_16to17 FROM availability WHERE day=\"Saturday\"and id_maint=" + id_maint.get(i));
                while (rs.next()) {
                    saturday.set(i, rs.getInt(1) + rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5) + rs.getInt(6) + rs.getInt(7));
                }
                rs = stmt.executeQuery("SELECT avail_8to9, avail_9to10,avail_10to11,avail_11to12,avail_14to15,avail_15to16,avail_16to17 FROM availability WHERE day=\"Sunday\"and id_maint=" + id_maint.get(i));
                while (rs.next()) {
                    sunday.set(i, rs.getInt(1) + rs.getInt(2) + rs.getInt(3) + rs.getInt(4) + rs.getInt(5) + rs.getInt(6) + rs.getInt(7));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}