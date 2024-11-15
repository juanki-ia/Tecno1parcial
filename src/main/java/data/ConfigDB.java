package data;

public class ConfigDB {
    private String user;
    private String password;
    private String host;
    private String port;
    private String dbName;

    // Constructor
    ConfigDB() {
        this.user = "grupo20sa";
        this.password = "grup020grup020";
        this.host = "mail.tecnoweb.org.bo";
        this.port = "5432";
        this.dbName = "db_grupo20sa";
    }

    // Getters
    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }
}
