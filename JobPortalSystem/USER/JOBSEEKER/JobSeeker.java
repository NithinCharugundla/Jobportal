package USER.JOBSEEKER;

import java.io.FileReader;  
import java.io.IOException;
import java.sql.SQLException;

import com.opencsv.CSVReader;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



import DATABASE.JobSeekerDb;
import USER.User;
import USER.RECRUITER.Job;

public class JobSeeker extends User{
    private int age;
    private String college;
    private String qualification;
    private Double percentage;
    private String skill1;
    private String skill2;
    private String skill3;
    private int experience;
   private static JobSeekerDb jobSeekerDb = new JobSeekerDb();

    ArrayList<Job> eligibleJobs = new ArrayList<Job>();
    ArrayList<Job> appliedJobs = new ArrayList<Job>();  

    public JobSeeker(String firstName, String lastName, String email, String password, String gender,
    String mobileNumber, String dateOfBirth, String userType, String isLoggedIn) {
super(firstName, lastName, email, password, gender, mobileNumber, dateOfBirth, userType, isLoggedIn);
}

     public JobSeeker(String firstName, String lastName, String email, String password, String gender,
    String mobileNumber, String dateOfBirth) {
super(firstName, lastName, email, password, gender, mobileNumber, dateOfBirth);
super.setUserType("jobseeker");
} 

    public JobSeeker(){
        super.setUserType("jobseeker");
    }
    
    
    
    public JobSeeker(String firstName, String lastName, String email, String password, String gender,
            String mobileNumber, String dateOfBirth, String userType, String isLoggedIn, int age, String college,
            String qualification, Double percentage, String skill1, String skill2, String skill3, int experience) {
        super(firstName, lastName, email, password, gender, mobileNumber, dateOfBirth, userType, isLoggedIn);
        this.age = age;
        this.college = college;
        this.qualification = qualification;
        this.percentage = percentage;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.experience = experience;
        
        super.setUserType("jobseeker");
    }

    public int getAge() {
        return age;
    }
     public void setAge(int age){
      this.age=age;
     }
    public void setAge() {
        LocalDate curDate=LocalDate.now();
        DateTimeFormatter jkl=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = curDate.format(jkl);
        String n=formattedDate;
        String n1=n.substring(6,10);
        int i=Integer.parseInt(n1);
        String n2=this.getDateOfBirth().substring(6,10);
        int j=Integer.parseInt(n2);
        this.age =(i-j);
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public ArrayList<Job> getEligibleJobs() {
        return eligibleJobs;
    }

    public void setEligibleJobs() throws SQLException {
        ResultSet rs = jobSeekerDb.getAllJobs();
        while(rs.next())
        {
            //get details of the job from job database
            Job obj = new Job(rs.getString("id"),rs.getString("jobtitle"),rs.getString("location"),rs.getString("companyname"),rs.getString("deadline"),rs.getInt("numberofvacancies"),rs.getString("skillrequired"),rs.getInt("maxage"),rs.getInt("minexperience"),rs.getString("description"));
            if(obj.isEligible(this))
                this.eligibleJobs.add(obj);
              
        }
    }

    public void printAppliedJobs() {

     for(Job i : appliedJobs){
        System.out.printf("| %7s | %20s | %20s |\n",i.getId(),i.getJobTitle(),i.getCompanyName());
     }
    }

    public void printEligibleJobs() {
        System.out.printf("| %7s | %20s | %20s |\n","jobID","jobTitle","CompanyName");
        for(Job i : eligibleJobs){
           System.out.printf("| %7s | %20s | %20s |\n",i.getId(),i.getJobTitle(),i.getCompanyName());
        }
       }

    public void setAppliedJobs(ArrayList<Job> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }
   public void setAppliedJobs() throws SQLException{
    ResultSet rs = jobSeekerDb.appliedJobs(this);
    while(rs.next()){
     Job job= new Job(rs.getString("id"),rs.getString("jobtitle"),rs.getString("location"),rs.getString("companyName"),rs.getString("deadLine"),rs.getInt("numberOfVacancies"),rs.getString("skillRequired"),rs.getInt("maxAge"),rs.getInt("minExperience"),rs.getString("description"));
     appliedJobs.add(job);   
    }
   }
    public void setEligibleJobs(ArrayList<Job> eligibleJobs) {
        this.eligibleJobs = eligibleJobs;
    }

    public ArrayList<Job> getAppliedJobs() {
        return appliedJobs;
    }

    public boolean applyForJob(Job job) throws SQLException{
           
         if(jobSeekerDb.insertJobApplicant(this, job)){
            appliedJobs.add(job); 
            return true;
        }
        return false;
    }
   
    @Override
    public boolean Register() throws SQLException {
        
           return( jobSeekerDb.addUserRecord(this) & jobSeekerDb.addJobSeekerRecord(this));
       
       
    }

    
    public boolean Register(String csvFilePath) throws SQLException {
        CSVReader reader = null; 
        try  
        {  
        reader = new CSVReader(new FileReader(csvFilePath));    
        String [] nL;  
        nL = reader.readNext();
        this.setFirstName(nL[0]);  
        this.setLastName(nL[1]);
        this.setEmail(nL[2]);
        this.setPassword(nL[3]);
        this.setGender(nL[4]);
        this.setMobileNumber(nL[5]);
        this.setDateOfBirth(nL[6]);
        this.setUserType("jobseeker");
        this.setLoggedIn("true");
        this.setAge(Integer.parseInt(nL[7]));
        this.setCollege(nL[8]);
        this.setQualification(nL[9]);
        this.setPercentage(Double.parseDouble(nL[10]));
        this.setSkill1(nL[11]);
        this.setSkill2(nL[12]);
        this.setSkill3(nL[13]);
        this.setExperience(Integer.parseInt(nL[14]));
           
            return(jobSeekerDb.addUserRecord(this) &&
            jobSeekerDb.addJobSeekerRecord(this)); 
        }  
        catch (Exception e)   
        {  
        e.printStackTrace(); 
        return false ;
        }  
        
        
    }
    

    

    @Override
    public boolean Logout() throws SQLException {
       if(jobSeekerDb.updateUserLoginStatus(getEmail(), "false"))return true;
       return false;
        
    }

    @Override
    public boolean deleteUser() throws SQLException {
      
            return(jobSeekerDb.deleteUserRecord(this) & jobSeekerDb.deleteJobSeekerRecord(this));
       
    }

    @Override
    public boolean Login(String email, String password) throws SQLException {
       String userPassword = jobSeekerDb.getUserPassword(email);
       if(userPassword.equals(password)){
        this.setLoggedIn("true");
        jobSeekerDb.updateUserLoginStatus(getEmail(), isLoggedIn());
        return true;
    }
       else return false;
        
    }
    public void getDetails(){   
      System.out.println("Name          : "+getFirstName()+" "+getLastName());
      System.out.println("Date of birth : "+getDateOfBirth());
      System.out.println("Age           : "+getAge());
      System.out.println("Gender        : "+getGender());
      System.out.println("Experience    : "+getExperience());
      System.out.println("skills        : "+getSkill1()+","+getSkill2()+","+getSkill3());
      System.out.println("Qualification : "+getQualification());
      System.out.println("College       : "+getCollege());
      System.out.println("percentage    : "+getPercentage()); 
    }
}
