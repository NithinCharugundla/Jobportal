package DATABASE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import USER.JOBSEEKER.Application;
import USER.JOBSEEKER.JobSeeker;
import USER.RECRUITER.Job;

public class JobSeekerDb extends UserDb {
    public  boolean addJobSeekerRecord(JobSeeker jobSeeker) throws SQLException{
        String Query = "insert into jobseeker values('"+jobSeeker.getEmail()+"',"+jobSeeker.getExperience()+",'"+jobSeeker.getQualification()+"',"+jobSeeker.getAge()+",'"+jobSeeker.getCollege()+"',"+jobSeeker.getPercentage()+",'"+jobSeeker.getSkill1()+"','"+jobSeeker.getSkill2()+"','"+jobSeeker.getSkill3()+"')";
        return !statement.execute(Query);
    }

    public  boolean deleteJobSeekerRecord(JobSeeker jobSeeker) throws SQLException{
        String Query = "delete from jobseeker where email= '"+jobSeeker.getEmail()+"';";
        return !statement.execute(Query);
    }

    public  ResultSet getAllJobs() throws SQLException{
        String Query = "select * from job";
        ResultSet rs = statement.executeQuery(Query);
        return rs;
    }

    public  boolean insertJobApplicant(JobSeeker jobSeeker,Job job) throws SQLException{
        String Query = "insert into applicants values('"+jobSeeker.getEmail()+"','"+job.getId()+"','"+job.getJobTitle()+"','"+job.getCompanyName()+"','applied','NULL')";
        return !statement.execute(Query);
    }

    public ArrayList<Application> getApplications(String jobSeekerEmail) throws SQLException{
        String Query = "select * from applicants where email = '"+jobSeekerEmail+"'";
       ResultSet rs = statement.executeQuery(Query);
       ArrayList<Application> applications = new ArrayList<>();
       while(rs.next()){
        applications.add(new Application(rs.getString("email"), rs.getString("id"), rs.getString("jobtitle"), rs.getString("companyname"), rs.getString("status"), rs.getString("message")));
       }
       return applications;
    }

    public ResultSet appliedJobs(JobSeeker jobSeeker) throws SQLException {
        String Query ="Select a.*,j.* from applicants a,job j where a.id=j.id and a.email='"+jobSeeker.getEmail()+"';";
        return statement.executeQuery(Query);
    }

   


    //more methods here
}
