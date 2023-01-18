package USER.JOBSEEKER;

public class Application {
    private String applicantEmail;
    private String jobID;
    private String jobTitle;
    private String companyName;
    private String applicationStatus;
    private String message;
    
    public Application() {
    }
    public Application(String applicantEmail, String jobID, String jobTitle, String companyName,
            String applicationStatus, String message) {
        this.applicantEmail = applicantEmail;
        this.jobID = jobID;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.applicationStatus = applicationStatus;
        this.message = message;
    }
    public String getApplicantEmail() {
        return applicantEmail;
    }
    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }
    public String getJobID() {
        return jobID;
    }
    public void setJobID(String jobID) {
        this.jobID = jobID;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getApplicationStatus() {
        return applicationStatus;
    }
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void printApplication(){
        System.out.println("Applicant Email     : "+getApplicantEmail());
        System.out.println("Job ID              : "+getJobID());
        System.out.println("Job Title           : "+getJobTitle());
        System.out.println("Company Name        : "+getCompanyName());
        System.out.println("Application Status  : "+getApplicationStatus());
        System.out.println("Message             : "+getMessage());
    }
}
