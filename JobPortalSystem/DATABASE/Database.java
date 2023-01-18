package DATABASE;


public class Database {
    	private  String dbURL="jdbc:mysql://localhost:3306/jobportalsystem";
    	private String dbUserName="root";
    	private String dbPassword="chandu@mysql";   // change this details according to your local mysql credentials
        public Database(){}
        public Database(String dbURL, String dbUserName, String dbPassword) {
            this.dbURL = dbURL;
            this.dbUserName = dbUserName;
            this.dbPassword = dbPassword;
        }
        public String getDbURL() {
            return dbURL;
        }
        public void setDbURL(String dbURL) {
            this.dbURL = dbURL;
        }
        public String getDbUserName() {
            return dbUserName;
        }
        public void setDbUserName(String dbUserName) {
            this.dbUserName = dbUserName;
        }
        public String getDbPassword() {
            return dbPassword;
        }
        public void setDbPassword(String dbPassword) {
            this.dbPassword = dbPassword;
        }
    	
}

