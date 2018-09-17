import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SingletonDB {
	private static SingletonDB sdb=null;
    private Connection con = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
    private static final String URL="jdbc:mysql://localhost:3306/usersdb";
    private static final String USR="root";
    private static final String PWD="ramones17";

	
	//The constructor is private in order to avoid outside instantiation.
	private SingletonDB() {} 
	
	public static SingletonDB getInstance() {
		if(sdb==null) {
			sdb = new SingletonDB();
		}
		return sdb;
	}
	
	public void connect() throws Exception {
		try {
            // This will load the MySQL driver.
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            con = DriverManager.getConnection(URL,USR,PWD);
		}catch(Exception e) {
			throw e;
		}		
	}
	public boolean isConnected() {
		return con!=null;		
	}
	
	public void printUsers() {

		try {
			statement = con.createStatement();
			resultSet = statement.executeQuery("select * from users");
			writeResultSet(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
        	
            String user = resultSet.getString(1);
            String pwd = resultSet.getString(2);
            System.out.println("User: " + user);
            System.out.println("Password: " + pwd);
        }
    }
	
}
