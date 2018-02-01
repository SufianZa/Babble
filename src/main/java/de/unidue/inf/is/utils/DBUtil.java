package de.unidue.inf.is.utils;

import com.ibm.db2.jcc.DB2Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;





public final class DBUtil {

    private DBUtil() {
    }


    static {
        com.ibm.db2.jcc.DB2Driver driver = new DB2Driver();
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            DriverManager.registerDriver(driver);
        }
        catch (SQLException e) {
            throw new Error("Laden des Datenbanktreiber nicht möglich");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(String database) throws SQLException {
        final String url = "jdbc:db2:" + database;
        return DriverManager.getConnection(url);
    }


    public static Connection getExternalConnection(String database) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("securityMechanism", Integer.toString(com.ibm.db2.jcc.DB2BaseDataSource.USER_ONLY_SECURITY));
        properties.setProperty("user", "DBP66");
        properties.setProperty("password", "vohr9uaw");

        final String url = "jdbc:db2://dione.is.inf.uni-due.de:50066/" + database + ":currentSchema=dbp66;";

        Connection connection = DriverManager.getConnection(url, properties);
        return connection;
    }

}
