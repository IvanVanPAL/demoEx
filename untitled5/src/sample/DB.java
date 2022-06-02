package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DB {

    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DB_NAME = "service";
//    private final String LOGIN = "root";
//    private final String PASS = "";
//    private final String USEUNICODE = "true";
//    private final String CHARENCODING = "utf8";


    private Connection dbConn = null;

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();

        properties.setProperty("user","root");
        properties.setProperty("password","");
        properties.setProperty("useUnicode","true");
        properties.setProperty("characterEncoding","utf8");

        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, properties);
        return dbConn;
    }

//    public void insertTask(String task) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO `todo` (task) VALUES (?)";
//
//        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
//        prSt.setString(1, task);
//
//        prSt.executeUpdate();
//
//    }

    public ArrayList<String> getTasks(String a) throws SQLException, ClassNotFoundException {
        String sql = "SELECT "+a+" FROM service ORDER BY `id` DESC";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> tasks = new ArrayList<>();
        while(res.next()) {
            //tasks.add(res.getString("Title"));
            tasks.add(res.getString(a));
//            if (res.getFloat("Discount") > 0) {
//                double a = res.getFloat("Cost") - (res.getFloat("Cost") * res.getFloat("Discount") / 100);
//                double c = res.getFloat("Cost");
//                tasks.add(c + " рублей");
//                tasks.add(a + " рублей");
//                tasks.add("* скидка " + res.getString("Discount") + "%");
//            } else if (res.getFloat("Discount") == 0) {
//                tasks.add(res.getFloat("Cost") + " рублей");
//            }
        }
        return tasks;
    }

}

