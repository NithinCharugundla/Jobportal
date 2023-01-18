package DATABASE;
import java.sql.*;

import com.mysql.cj.protocol.Resultset;

import USER.User;
import USER.ADMINISTRATOR.Administrator;
import USER.JOBSEEKER.JobSeeker;
import USER.RECRUITER.Recruiter;

public class UserDb  {
    private  Connection connection;
    private  Database database;
    protected  Statement statement;
    protected   ResultSet resultSet;

    public UserDb() {
        database = new Database();
        
       try {
       
        connection = DriverManager.getConnection(database.getDbURL(),database.getDbUserName(),database.getDbPassword());
         statement = connection.createStatement();
        } catch (SQLException e) {
       
            e.printStackTrace();
        }
    }

    public   boolean addUserRecord(User user) throws SQLException{
        String Query = "insert into user values('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getEmail()+"','"+user.getPassword()+"','"+user.getGender()+"','"+user.getMobileNumber()+"','"+user.getDateOfBirth()+"','"+user.getUserType()+"','"+user.isLoggedIn()+"')";
        return !statement.execute(Query);
    }
     
    public  boolean deleteUserRecord(User user) throws SQLException{
        String Query = "delete from user where email= '"+user.getEmail()+"'";
        return !statement.execute(Query);
    }

    public  String getUserPassword(String email) throws SQLException{
        String Query = "select password from user where email = '"+email+"'";
        resultSet = statement.executeQuery(Query);
        if(resultSet.next())
        return(resultSet.getString("password"));
        else return null;
    }

    public  boolean updateUserLoginStatus(String email,String status) throws SQLException{
        String Query = "update user set loginstatus ='"+status+"' where email = '"+email+"';";
        return !statement.execute(Query);
    }

    public  User getUser(String email) throws SQLException{
        User user;

        String Query = "select * from user where email = '"+email+"'";

        ResultSet rs = statement.executeQuery(Query);
      
        if(!rs.next())return null;
        String userType = rs.getString("usertype");
        if(userType.toLowerCase().equals("jobseeker")){
            rs.close();
            rs = statement.executeQuery("Select user.*,j.* from user,jobseeker j where user.email=j.email and user.email='"+email+"';");
           if (!rs.next())return null;
            user = new JobSeeker(rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"),rs.getString("password"),rs.getString("gender"),rs.getString("mobilenumber"),rs.getString("dateofbirth"),rs.getString("usertype"),rs.getString("loginstatus"),rs.getInt("age"),rs.getString("college"),rs.getString("qualification"),rs.getDouble("percentage"),rs.getString("skill1"),rs.getString("skill2"),rs.getString("skill3"),rs.getInt("experience"));
            return user;
        }
        else if(userType.toLowerCase().equals("recruiter")){
            rs.close();
            rs = statement.executeQuery("Select user.*,r.* from user,recruiter r where user.email=r.email and user.email='"+email+"';");
                     if(!rs.next()) return null;
                        user = new Recruiter(rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"),rs.getString("password"),rs.getString("gender"),rs.getString("mobilenumber"),rs.getString("dateofbirth"),rs.getString("usertype"),rs.getString("loginstatus"),rs.getString("companyname"),rs.getString("designation"));
            
            return user;
        }
        else if(userType.toLowerCase().equals("administrator")){
            user = new Administrator(rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"),rs.getString("password"),rs.getString("gender"),rs.getString("mobilenumber"),rs.getString("dateofbirth"),rs.getString("usertype"),rs.getString("loginstatus"));
            return user;
        }
        
        return null;
    }
}
