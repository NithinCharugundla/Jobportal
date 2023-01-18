package USER.RECRUITER;

import java.util.Date;

import USER.JOBSEEKER.JobSeeker;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Job {
    private String id;
    private String jobTitle;
    private String location;
    private String companyName;
    private String deadline;
    private int numberOfVacancies;
    private String skillRequired;
    private int maxAge;
    private int minExperience;
    private String description;
    private boolean active;
    
     public Job()
    {

    }
    public Job(String id, String jobTitle, String location, String companyName, String deadline, int numberOfVacancies,
            String skillRequired, int maxAge, int minExperience, String description) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.location = location;
        this.companyName = companyName;
        this.deadline = deadline;
        this.numberOfVacancies = numberOfVacancies;
        this.skillRequired = skillRequired;
        this.maxAge = maxAge;
        this.minExperience = minExperience;
        this.description = description;
       // this.active= this.isActive();
    }

   

    @Override
    public String toString() {
        return "Job [id=" + id + ", jobTitle=" + jobTitle + ", location=" + location + ", companyName=" + companyName
                + ", deadline=" + deadline + ", numberOfVacancies=" + numberOfVacancies + ", skillRequired="
                + skillRequired + ", maxAge=" + maxAge + ", minExperience=" + minExperience + ", description="
                + description + ", active=" + active + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getNumberOfVacancies() {
        return numberOfVacancies;
    }

    public void setNumberOfVacancies(int numberOfVacancies) {
        this.numberOfVacancies = numberOfVacancies;
    }

    public String getSkillRequired() {
        return skillRequired;
    }

    public void setSkillRequired(String skillRequired) {
        this.skillRequired = skillRequired;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(int minExperience) {
        this.minExperience = minExperience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive(){
        try 
        {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sdformat.parse(this.deadline);
            Date d2 = new Date();
            if(d1.compareTo(d2) >= 0) {
                this.active=true;
                return this.active;
            } 
            else {
                this.active=false;
                return this.active;
            }
        }
        catch (ParseException e) 
        {
            e.printStackTrace();                    
            this.active=true;
            return this.active;
        }
    }

    public void apply(JobSeeker obj){
        //add user in the database of applicants of this job 
    }

    public void updateJob(){
        //update details of the job in the database
    }

    public boolean isEligible(JobSeeker obj){
       
           
                    if (obj.getSkill1().toLowerCase().equals(this.skillRequired.toLowerCase()) ||
                        obj.getSkill2().toLowerCase().equals(this.skillRequired.toLowerCase()) ||
                        obj.getSkill3().toLowerCase().equals(this.skillRequired.toLowerCase()) 
                        )
                        return true;
           
        
        return false;
    }

    public  void printJobDetails(){
        System.out.println("Job Title       : "+this.getJobTitle());
        System.out.println("Job ID          : "+this.getId());
        System.out.println("Company Name    : "+this.getCompanyName());
        System.out.println("Location        : "+this.getLocation());
        System.out.println("Job Description : "+this.getDescription());
        System.out.println("Skills          : "+this.getSkillRequired());
        System.out.println("Vacancies       : "+this.getNumberOfVacancies());
        System.out.println("Experience      : "+this.getMinExperience()+" years");
        System.out.println("Last Date       : "+this.getDeadline());
    }
}
