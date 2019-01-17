package lk.ijs.studentregistration.db;


import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection  {
    private static Connection connection=null;




    public static Connection getInstance() throws Exception {

        File file = new File("resource/application.properties");
        FileReader fileReader = new FileReader(file);
        Properties dbProp = new Properties();
        dbProp.load(fileReader);
        String ip = dbProp.getProperty("ip");
        String port = dbProp.getProperty("port");
        String db = dbProp.getProperty("database");
        String username = dbProp.getProperty("username");
        String password = dbProp.getProperty("password");
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + db;

        System.out.println(url+username+password);

        if(connection==null){
          //  connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/ijse","root","1234");
            connection= DriverManager.getConnection(url,username,password);
        }
        return connection;
    }
}
