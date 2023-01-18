package DATABASE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import USER.JOBSEEKER.Application;
import USER.RECRUITER.Job;
import USER.RECRUITER.Recruiter;

public class RecruiterDb extends UserDb {
    public  boolean addRecruiterRecord(Recruiter recruiter) throws SQLException{
        String Query = "insert into recruiter values('"+recruiter.getEmail()+"','"+recruiter.getCompanyName()+"','"+recruiter.getDesignation()+"')";
        return !statement.execute(Query);
    }

    public  boolean deleteRecruiterRecord(Recruiter recruiter) throws SQLException{
        String Query = "delete from recruiter where email='"+recruiter.getEmail()+"'";
        return !statement.execute(Query);
    }

    public  boolean addJobRecord(Job job) throws SQLException{
        String Query = "insert into job values ('"+job.getId()+"','"+job.getJobTitle()+"','"+job.getLocation()+"','"+job.getCompanyName()+"','"+job.getDeadline()+"','"+job.getNumberOfVacancies()+"','"+job.getSkillRequired()+"','"+job.getMaxAge()+"','"+job.getMinExperience()+"','"+job.getDescription()+"')";
        return !statement.execute(Query);
    }
    public boolean deleteJobRecord(String jobID) throws SQLException{
        String Query = "delete from job where id = '"+jobID+"'";
        return statement.execute(Query);
}
    public  boolean updateUserLoginStatus(String email,String status) throws SQLException{
        String Query = "update user set loginstatus ="+status;
        return !statement.execute(Query);
    }

    public Job getJobRecord(String jobID) throws SQLException{
        String Query = "select * from job where id = '"+jobID+"'";
       ResultSet resultSet = statement.executeQuery(Query);
        if(resultSet.next()){
            Job job = new Job(resultSet.getString("id"), resultSet.getString("jobtitle"), resultSet.getString("location"), resultSet.getString("companyname"), resultSet.getString("deadline"), resultSet.getInt("numberofvacancies"), resultSet.getString("skillrequired"), resultSet.getInt("maxage"), resultSet.getInt("minexperience"), resultSet.getString("description"));
            return job;
        }
        return null;
    }
    
    public boolean updateJobRecord(Job job) throws SQLException{
        String Query = "update job set jobtitle = '"+job.getJobTitle()+"',location = '"+job.getLocation()+"',companyname = '"+job.getCompanyName()+"',deadline = '"+job.getDeadline()+"',numberofvacancies = "+job.getNumberOfVacancies()+",skillrequired = '"+job.getSkillRequired()+"',maxage = "+job.getMaxAge()+",minexperience = "+job.getMinExperience()+",description ='"+job.getDescription()+"' where id = '"+job.getId()+"'";
        return !statement.execute(Query);

    }

    public ArrayList<Job> getJobsPosted(String companyName) throws SQLException{
        String Query = "select * from job where companyname = '"+companyName+"';";
        ResultSet rs = statement.executeQuery(Query);
        ArrayList<Job> jobList = new ArrayList<>();
        while(rs.next()){
            jobList.add(new Job(rs.getString("id"),rs.getString("jobtitle"),rs.getString("location"),rs.getString("companyName"),rs.getString("deadLine"),rs.getInt("numberOfVacancies"),rs.getString("skillRequired"),rs.getInt("maxAge"),rs.getInt("minExperience"),rs.getString("description")));
        }
        return jobList;
    }
       public boolean deleteRecruiterJob(Recruiter recruiter, Job job) {
        return false;
    }
    public boolean insertRecruiterJob(Recruiter recruiter, Job job) {
        return false;
    }

    public boolean selectApplicant(Application application,String description) throws SQLException {
        String Query = "update applicant set status='selected',message='"+description+"' where email ='"+application.getApplicantEmail()+"' and id = '"+application.getJobID()+"'";
        return statement.execute(Query);
    }

    public boolean postJob(Recruiter recruiter, Job job) throws SQLException {
        String Query="insert into job values('"+job.getId()+"','"+job.getJobTitle()+"','"+job.getLocation()+"','"+job.getCompanyName()+"','"+job.getDeadline()+"','"+job.getNumberOfVacancies()+"','"+job.getSkillRequired()+"','"+job.getMaxAge()+"','"+job.getMinExperience()+"','"+job.getDescription()+"');";

        return statement.execute(Query);
    }

    public ArrayList<Application> getApplicants(String companyName) throws SQLException{
        String Query = "select * from applicants where companyname = '"+companyName+"'";
       ResultSet rs = statement.executeQuery(Query);
       ArrayList<Application> applications = new ArrayList<>();
       while(rs.next()){
        applications.add(new Application(rs.getString("email"), rs.getString("id"), rs.getString("jobtitle"), rs.getString("companyname"), rs.getString("status"), rs.getString("message")));
       }
       return applications;
    }

    public  Application getApplicant(String email,String jobID)throws SQLException{
        String Query = "select * from applicants where email = '"+email+"' and id = '"+jobID+"'";
        ResultSet rs = statement.executeQuery(Query);
        
        if(rs.next()){
        Application application= new Application(rs.getString("email"), rs.getString("id"), rs.getString("jobtitle"), rs.getString("companyname"), rs.getString("status"), rs.getString("message"));
        return application;
        }
        return null;

    }

    
  
  
}
