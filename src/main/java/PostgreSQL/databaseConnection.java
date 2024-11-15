package PostgreSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    private static final String DRIVER = "jdbc:postgresql://";
    private Connection connection;
    private String user;
    private String password;
    private String host;
    private String port;
    private String db_name;
    private String url;

    public databaseConnection(String user, String password, String host, String port, String db_name) {
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
        this.db_name = db_name;

        this.url = DRIVER + host + ":" + port + "/" + db_name;
    }

    public Connection connection() {
        try {
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {
            System.out.print("error en la conexion a la base de datos, connection databaseConnection.java ");
        }
        return connection;
    }

    public void closeConnection(){
        try{
            connection.close();
        }catch (SQLException ex){
            System.err.print("Error al cerrar la conexion a la base de datos, CloseConnection, databaseConnection.java");
        }
    }

}
