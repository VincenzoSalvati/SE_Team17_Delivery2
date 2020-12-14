package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings("SqlResolve")
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
    private Connection con;

    public String getAssignEWOBrowseToJSONJSP(MySqlDbConnection db, int activity, int week, int specifications, String day) {

        String assignEWOBrowseJSONFormat = "{\"id\":\"{ID}\",\"maint\":\"{MAINT}\",\"skills\":\"{SKILLS}\",\"h8to9\":\"{h8to9}\",\"h9to10\":\"{h9to10}\",\"h10to11\":\"{h10to11}\",\"h11to12\":\"{h11to12}\",\"h14to15\":\"{h14to15}\",\"h15to16\":\"{h15to16}\",\"h16to17\":\"{h16to17}\"}";
        StringBuilder assignEWOJSONResult = new StringBuilder();
        String JSONRow;
        int skillTot;
        int i;
        con = db.connect();

        try {

            //raccolta manutentori
            getMaintainer(id_maint, name_maint);

            //conteggio skills manutentori
            getSkill(id_maint, skills, specifications);

            //conteggio skill totali
            skillTot = getSkillTot(specifications);
            if (skillTot == -1) return "";

            for (i = 0; i < id_maint.size(); i++) {
                h8to9.add(0);
                h9to10.add(0);
                h10to11.add(0);
                h11to12.add(0);
                h14to15.add(0);
                h15to16.add(0);
                h16to17.add(0);
            }

            getAvailability(id_maint, h8to9, h9to10, h10to11, h11to12, h14to15, h15to16, h16to17, day, week);

            for (i = 0; i < id_maint.size(); i++) {
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

        return "[" + Util.removeLastChar(assignEWOJSONResult.toString()) + "]";

    }


    //prelievo ore disponibili
    private void getAvailability(ArrayList<Integer> id_maint, ArrayList<Integer> h8to9, ArrayList<Integer> h9to10, ArrayList<Integer> h10to11, ArrayList<Integer> h11to12, ArrayList<Integer> h14to15, ArrayList<Integer> h15to16, ArrayList<Integer> h16to17, String day, int week) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            for (int i = 0; i < id_maint.size(); i++) {

                //conteggio availability manutentori 8to9
                rs = stmt.executeQuery("SELECT avail_8to9 FROM availability WHERE id_maint=" + id_maint.get(i) + " AND day=\"" + day + "\"" + " AND week=\"" + week + "\"");
                while (rs.next()) {
                    h8to9.set(i, rs.getInt(1));
                }

                //conteggio availability manutentori 9to10
                rs = stmt.executeQuery("SELECT avail_9to10 FROM availability WHERE id_maint=" + id_maint.get(i) + " AND day=\"" + day + "\"" + " AND week=\"" + week + "\"");
                while (rs.next()) {
                    h9to10.set(i, rs.getInt(1));
                }
                //conteggio availability manutentori 10to11
                rs = stmt.executeQuery("SELECT avail_10to11 FROM availability WHERE id_maint=" + id_maint.get(i) + " AND day=\"" + day + "\"" + " AND week=\"" + week + "\"");
                while (rs.next()) {
                    h10to11.set(i, rs.getInt(1));
                }

                //conteggio availability manutentori 11to12
                rs = stmt.executeQuery("SELECT avail_11to12 FROM availability WHERE id_maint=" + id_maint.get(i) + " AND day=\"" + day + "\"" + " AND week=\"" + week + "\"");
                while (rs.next()) {
                    h11to12.set(i, rs.getInt(1));
                }

                //conteggio availability manutentori 14to15
                rs = stmt.executeQuery("SELECT avail_14to15 FROM availability WHERE id_maint=" + id_maint.get(i) + " AND day=\"" + day + "\"" + " AND week=\"" + week + "\"");
                while (rs.next()) {
                    h14to15.set(i, rs.getInt(1));
                }

                //conteggio availability manutentori 15to16
                rs = stmt.executeQuery("SELECT avail_15to16 FROM availability WHERE id_maint=" + id_maint.get(i) + " AND day=\"" + day + "\"" + " AND week=\"" + week + "\"");
                while (rs.next()) {
                    h15to16.set(i, rs.getInt(1));
                }

                //conteggio availability manutentori 16to17
                rs = stmt.executeQuery("SELECT avail_16to17 FROM availability WHERE id_maint=" + id_maint.get(i) + " AND day=\"" + day + "\"" + " AND week=\"" + week + "\"");
                //AND a.day=\"" + day + "\"" + " AND a.week=\"" + week + "\"");
                while (rs.next()) {
                    h16to17.set(i, rs.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //conteggio skills manutentori
    private void getSkill(ArrayList<Integer> id_maint, ArrayList<Integer> skills, int specifications) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
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

    //conteggio skill totali
    private int getSkillTot(int specifications) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT COUNT(*) FROM need WHERE specifications=" + specifications);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    //raccolta manutentori
    private void getMaintainer(ArrayList<Integer> id_maint, ArrayList<String> name_maint) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT id, name FROM maintainer");
            while (rs.next()) {
                id_maint.add(rs.getInt(1));
                name_maint.add(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
