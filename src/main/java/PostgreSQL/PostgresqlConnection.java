package PostgreSQL;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresqlConnection {
    public static void main(String args[]) {
        try {
            databaseConnection databaseConnection =
                    new databaseConnection(
                            "postgres",
                            "1999",
                            "localhost",
                            "5432",
                            "db_pizzeria");
            String query ="select * from users where id=1";
            PreparedStatement ps = databaseConnection.connection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.print("res: "+rs);
        } catch (SQLException ex) {
            System.err.print("error en main, postgresql connection");
        }

    }
}
