package JAVA.tester;

import JAVA.MySqlDbConnection;

public class _0_SetDatabaseTest {
    String user = "root";
    String password = "admin";
    String dbName = "project";

    public void setDatabase(MySqlDbConnection db) {
        db.setDbUser(user);
        db.setDbPassword(password);
        db.setDbName(dbName);
    }
}
