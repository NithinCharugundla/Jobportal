package DATABASE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import USER.ADMINISTRATOR.Administrator;
import USER.JOBSEEKER.JobSeeker;
import USER.RECRUITER.Job;

public class AdministratorDb extends UserDb {
    public   boolean addAdministratorRecord(Administrator administrator) throws SQLException{
        String Query = "insert into administrator values('"+administrator.getEmail()+"','"+administrator.getAdministratorKey()+"')";
        return !statement.execute(Query);
    }

    public  boolean deleteAdministratorRecord(Administrator administrator) throws SQLException{
        String Query = "delete from administrator where email='"+administrator.getEmail()+"'";
        return !statement.execute(Query);
    }

    public  ArrayList<JobSeeker> getAllJobSeekers() throws SQLException{
        String Query = "select * from user where type = 'jobseeker'";
        ResultSet rs = statement.executeQuery(Query);
        ArrayList<JobSeeker> userList  = new ArrayList<>();
        while(resultSet.next()){
            userList.add(new JobSeeker(rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"),rs.getString("password"),rs.getString("gender"),rs.getString("mobilenumber"),rs.getString("dateofbirth")));
        }
        return userList;
    }

    public ArrayList<Job> getAllJobs() throws SQLException{
        String Query = "select * from job";
        ResultSet rs = statement.executeQuery(Query);
        ArrayList<Job> jobList = new ArrayList<>();
        while(rs.next()){
            jobList.add(new Job(rs.getString("id"),rs.getString("jobtitle"),rs.getString("location"),rs.getString("companyname"),rs.getString("deadline"),rs.getInt("numberofvacancies"),rs.getString("skillrequired"),rs.getInt("maxage"),rs.getInt("minexperience"),rs.getString("description")));
        }
        return jobList;
    }
   
}
