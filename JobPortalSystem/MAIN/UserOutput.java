package MAIN;

import java.util.ArrayList;

import USER.JOBSEEKER.JobSeeker;
import USER.RECRUITER.Recruiter;
import USER.RECRUITER.Job;

public class UserOutput {
    public static void printHelp(){
        System.out.println("***********************************************************************************************************************");
        System.out.println("There can be multiple command line arguments, be sure first argument always refers to the operation you want to perform");
        System.out.println("For Example : login 'useremail' 'password'");
        System.out.println("Below are some command line arguments you can use : ");
        System.out.println("help                                                        --- prints all command line arguments one can use to perform operations ");
        System.out.println("login 'useremail' 'password'                                --- user login using email and password");
        System.out.println("login 'useremail'");
        System.out.println("logout 'useremail'                                          --- to logout user with email 'useremail'");
        System.out.println("register recruiter 'path to csv file containing recruiter details' --- recruiter can register account using this arguments");
        System.out.println("register administrator 'path to csv file containing administrator details' --- administrator can register account using this arguments");
        System.out.println("register jobseeker 'path to csv file containing jobseeker details' --- jobseeker can register account using this arguments");
        System.out.println("viewprofile 'useremail'                                     --- user can view his profile using this arguments");
        System.out.println("updateprofile 'useremail'                                   --- arguments to update any type of user");
        System.out.println("searchprofile 'useremail1' 'useremail2' [useremail1 - administrator/recruiter , useremail2 - user] --- recruiter or administrator with email 'useremail' can search and view profile of any other user with email 'useremail2'");
        System.out.println("viewjobs                                                    --- prints all job posts available on the job4u platform");
        System.out.println("viewapplications 'jobseekeremail'                            --- to view jobs applied by a jobseeker with email 'useremail'");
        System.out.println("viewpostedjobs 'recruiteremail'                             --- to view jobs posted by a recruiter");
        System.out.println("searchjob 'jobid'                                           --- to search details of a job post with job id 'jobid'");
        System.out.println("searchjobs 'jobtitle'                                       --- search jobs using job title string");
        System.out.println("searchjobs 'jobtitle' lt 'min experience'                   --- search jobs using job title string and required experience less than 'min experience'");
        System.out.println("searchjobs 'jobtitle' gt 'number of vacancies'              --- search jobs using job title string and number of vacancies greater than 'no of vacancies'");
        System.out.println("applyjob 'jobseekeremail' 'jobid'                                --- to apply for a job post with id = 'jobid'");
        System.out.println("postjobs 'recruiteremail' 'jobs csv file path'              --- recruiter can post multiple jobs using csv file");
        System.out.println("updatejobs 'recruiteremail' 'jobs csv file path'            --- recruiter can update multiple job posts using csv file");
        System.out.println("deletejobs 'recruiteremail' 'job ids csv file path          --- recruiter can delete multiple job posts with csv file containing job ids");
        System.out.println("viewapplicants 'recruiteremail'                             --- view all job applications applied for this recruiter");
        System.out.println("viewapplicants 'recruiteremail' 'jobid'                     --- view job applications for a job with id  = jobid");
        System.out.println("selectapplicant 'recruiteremail' 'jobseekeremail' ,jobid'   --- to select a application by recruiter");
         // add more...
        System.out.println("***********************************************************************************************************************");

       
    }

    public static void printUserLoginMenu(){
        System.out.println("Enter 1 to login");
        System.out.println("Enter 2 to Register");
        System.out.println("Enter 0 to exit");
    }

    public static void printJobSeekerMenu(){
        System.out.println("1 : Profile");
        System.out.println("2 : Job");
        System.out.println("3 : Logout");
    }

    public static void printProfileMenu(){
        System.out.println("1 : View Profile");
        System.out.println("2 : Modify Profile");
        System.out.println("3 : Back");
    }

    public static void printJobSeekerJobMenu(){
        System.out.println("1 : View Jobs");
        System.out.println("2 : Apply for Job");
        System.out.println("3 : View Applied Jobs");
        System.out.println("4 : view profile");
    }
    
    public static void printRecruiterMenu(){
        System.out.println("1 : Profile");
        System.out.println("2 : Job");
        System.out.println("3 : Applications");
        System.out.println("4 : Logout");
    }

    public static void printRecruiterJobMenu(){
        System.out.println("1 : View Jobs posted by You");
        System.out.println("2 : Post a Job");
        System.out.println("3 : Modify a Job Posting");
        System.out.println("4 : Delete A Job Posting");
        System.out.println("5 : Back");
    }

    public static void printApplicationsMenu(){
     System.out.println("1 : view applicants");
     System.out.println("2 :View applicants via job ID");
     System.out.println("3 :View applicants via Job title");
     System.out.println("4 .Select Applicant");
     System.out.println("5 : Back");   
    }

    public static void printAdministratorMenu(){
        System.out.println("1 : Profile");
        System.out.println("2 : Users");
        System.out.println("3 : logout");
    }

    public static void printJobTitles(ArrayList<Job> jobList){
        int variable = 1;
        for(Job job : jobList){
            System.out.println(variable+" : "+job.getJobTitle()+" ID"+job.getId());
            variable++;
        }
        System.out.println(variable+" : View All");
    }

    
    public static void printUpdatesRequirement(JobSeeker user){
       System.out.println("1. Update  firstname");
       System.out.println("2. Update  lastname");
       System.out.println("3. Update email");
       System.out.println("4. update password");
       System.out.println("5. Update date of birth");
       System.out.println("6. Update age");
       System.out.println("7. Update experience");
       System.out.println("8. Update mobilenumber");
       System.out.println("9. Back");
       System.out.print("Choose your option :");
    }
     public static void printUpdatesRequirement()
    {
        System.out.println("1. Update firstname");
        System.out.println("2. Update lastname");
        System.out.println("3. Update email");
        System.out.println("4. update password");
        System.out.println("5. Update date of birth");
        System.out.println("6. Update mobilenumber");
        System.out.println("7. Back");
        System.out.print("Choose your option :");
    }
        public static void printUpdatesRequirement(Job job)
    { 
       System.out.println("1. Update Job Title");
       System.out.println("2. Update location");
       System.out.println("3. Update company name");
       System.out.println("4. update deadline");
       System.out.println("5. Update Number of vacancies");
       System.out.println("6. Update skills required");
       System.out.println("7. Update maximum age limit");
       System.out.println("8. Update minimum experience");
       System.out.println("9. Update Description");
       System.out.println("10. Update Job Id");
       System.out.println("11. Back");
       System.out.print("Choose your option :");

    }
    public static void printApplicantDetails(){
     System.out.println("1 : Details of the applicant using email ");
     System.out.println("2 : Back");
     System.out.println("Choose your choice");   
    }
}
