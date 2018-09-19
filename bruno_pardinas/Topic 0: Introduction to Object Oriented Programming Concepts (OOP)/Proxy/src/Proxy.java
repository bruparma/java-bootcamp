
public class Proxy {
	
	private SingletonDB sdb = null;
	
	
	public void printUsers() {
		if (sdb == null) {
			sdb = SingletonDB.getInstance();
		}
		try {
			if (!sdb.isConnected()) 
				sdb.connect();
			sdb.printUsers();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


