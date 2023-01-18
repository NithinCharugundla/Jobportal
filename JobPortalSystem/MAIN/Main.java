package MAIN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DATABASE.AdministratorDb;
import DATABASE.JobSeekerDb;
import DATABASE.RecruiterDb;
import DATABASE.UserDb;
import DATABASE.INFO.Info;
import USER.User;
import USER.ADMINISTRATOR.Administrator;
import USER.JOBSEEKER.Application;
import USER.JOBSEEKER.JobSeeker;
import USER.RECRUITER.Job;
import USER.RECRUITER.Recruiter;

public class Main{
   private static AdministratorDb administratorDb  = new AdministratorDb();
   private static RecruiterDb recruiterDb = new RecruiterDb();
   private static JobSeekerDb jobSeekerDb = new JobSeekerDb();
   private static User user;
   private static JobSeeker jobseeker;
   private static Recruiter recruiter = new Recruiter();
    private static Job job;
   private static Administrator  administrator;
   private static Info info = new Info();

    public static void main(String[] args) throws SQLException {
       if(args.length==0)userMenu();
       else userMenu(args);
    }


    public static void userMenu() throws SQLException{
            UserOutput.printUserLoginMenu();
            int choice = UserInput.scanChoice();
            switch(choice){
                case 1 : {
                   user= login();
                   if(user==null)return;
                   if(user.getUserType().toLowerCase().equals("jobseeker")){
                   jobseeker = (JobSeeker) administratorDb.getUser(user.getEmail());
                   jobseeker.setAppliedJobs();
                    jobSeekerMenu();
                }
                    else if(user.getUserType().toLowerCase().equals("recruiter")){
                        recruiter = (Recruiter) administratorDb.getUser(user.getEmail());
                        recruiterMenu();
                    }
                   else if(user.getUserType().toLowerCase().equals("administrator")){
                    administratorMenu();
                }
                   break;
                }
                case 2 : {
                   register();

                }
                
                case 0 : {
                     return;
                }
            }
        
    }

    public static void jobSeekerMenu() throws SQLException{
        UserOutput.printJobSeekerMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 : {
                profileMenu();
                break;
            }
            case 2 : {
                jobSeekerJobMenu();
                break;
            }
            case 3 : {
                logout();
                userMenu();
                break;
            }
        }
    }

    public static void profileMenu() throws SQLException{
        while(true){
        UserOutput.printProfileMenu();
        int choice = UserInput.scanChoice();
        
        switch(choice){
            case 1 : {
                
                viewUserProfile();
                break;
            }
            case 2 : {
                modifyUserProfile();
                break;
            }
            case 3 : {
               exitProfile();
            }
            default : System.out.println("Invalid choice! Try again");  
        }
        if(choice==3){
            break; 
         } 
    }
    }

    public static void viewUserProfile() {
         if(user.getUserType().equalsIgnoreCase("jobseeker")){
           jobseeker.getDetails(); 
         }
         if(user.getUserType().equalsIgnoreCase("recruiter")){
            recruiter.getDetails();
         }
         if(user.getUserType().equalsIgnoreCase("administrator")){
            administrator.getDetails();
         }
     }
    public static void modifyUserProfile() throws SQLException{
        if(user.getUserType().equalsIgnoreCase("jobseeker")){
            UserInput.modifyJobseeker(jobseeker); 
          }
        if(user.getUserType().equalsIgnoreCase("recruiter")){
            UserInput.modifyRecruiter(recruiter); 
          }
          if(user.getUserType().equalsIgnoreCase("administrator")){
            UserInput.modifyAdministrator(administrator);
        }  
    }
    public static void modifyUserProfile(User user) throws SQLException{
        if(user.getUserType().equalsIgnoreCase("jobseeker")){
            UserInput.modifyJobseeker(jobseeker); 
          }
        if(user.getUserType().equalsIgnoreCase("recruiter")){
            UserInput.modifyRecruiter(recruiter); 
          }
          if(user.getUserType().equalsIgnoreCase("administrator")){
            UserInput.modifyAdministrator(administrator);
        }  
    }
    public static void exitProfile() throws SQLException{
        if(user.getUserType().equalsIgnoreCase("jobseeker")){
            jobSeekerMenu(); 
          }
          if(user.getUserType().equalsIgnoreCase("recruiter")){
              recruiterMenu();        
        }
        if(user.getUserType().equalsIgnoreCase("administrator")){
            administratorMenu();
        }
    }

    public static void jobSeekerJobMenu() throws SQLException{
        while(true){
        UserOutput.printJobSeekerJobMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 : info.display_jobs();
            break;
            case 2 : UserInput.applyjobs(jobseeker);
            break;
            case 3 : 
            
            jobseeker.printAppliedJobs();
            break;
            case 4 : jobSeekerMenu();
            break;
            default : System.out.println("Invalid credential! try again");
        }
        if(choice==4){
           break; 
        }
        }
    }

    public static void recruiterMenu() throws SQLException{
        UserOutput.printRecruiterMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 : {
               profileMenu();
               break;
            }
            case 2 : {
                recruiterJobMenu();
                break;
            }
            case 3 : {
                applicationsMenu();
                break;
            }
            case 4 : {
                logout();
                userMenu();
                break;
            }
        }
    }

    public static void recruiterJobMenu() throws SQLException{
        while(true){
        UserOutput.printRecruiterJobMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 :{
                viewJobs(recruiter);
                break;
            }
            case 2 : {
                job = UserInput.scanJobDetails();
                recruiter.postJob(job);
                break;
            }
            case 3 : {
                UserInput.modifyJob(recruiter,job); 
                break;
            }
            case 4 : {
                UserInput.deleteJob(recruiter);
                break;
            }
            case 5 : {
                recruiterMenu();
                break;
            }
        }
        if(choice==5){
            break;
        }
    }
    }

    public static void applicationsMenu() throws SQLException{
    UserOutput.printApplicationsMenu();
    int choice=UserInput.scanChoice();
    switch(choice){
     case 1 : info.display_applicants(recruiter);
            UserInput.viewdetails(recruiter);     
     break;
     case 2 : UserInput.display_applicantViaID(recruiter);  
     break;
     case 3 : UserInput.display_applicantViaTitle(recruiter);
     break;
     case 4 : UserInput.selectApplicant(recruiter);
     break;
     case 5 : recruiterMenu();
     break;
    }
    
    }

    public static void administratorMenu() throws SQLException{
        UserOutput.printAdministratorMenu();
        int choice = UserInput.scanChoice();
        switch(choice){
            case 1 : profileMenu();
            break;
            case 2 : info.count(administrator);
                     info.display_users(administrator);
                     break;
            case 3 : logout();         
        }
    }

    public static void userMenu(String[] args) throws SQLException{
        String arg = commandArgs(args);
        if(arg==null){
            System.out.println("Invalid command line arguments!");
            return;
        }
        switch(arg){
            case "help" : {
                UserOutput.printHelp();
                break;
            }
            case "login" : {
              user = login(args);
                break;
            }
            case "login1" : {
               user = login1(args);
                break;
            }
            case "logout" : {
                user = administratorDb.getUser(args[1]);
                logout(user);
                break;
            }
            case "registeradministrator" : {
                registerAdministrator(args[2]);
                break;
            }
            case "registerrecruiter" : {
                registerRecruiter(args[2]);
                break;
            }
            case "registerjobseeker" : {
                registerJobSeeker(args[2]);
                break;
            }
            case "viewjobs" : {
                viewJobs();
                break;
            }
            case "viewprofile" : {
                viewProfile(args[1]);
                break;
            }
            case "updateprofile" : {
                user = administratorDb.getUser(args[1]);
                modifyUserProfile();
                break;
            }
            case "viewjobsjobseeker" : {
                user = administratorDb.getUser(args[1]);
                if(user==null){
                    System.out.println("Invalid user details");
                }
                else if(user.getUserType().equalsIgnoreCase("jobseeker")){
                    jobseeker = (JobSeeker) user;
                    jobseeker.setEligibleJobs();
                    jobseeker.printEligibleJobs();
                }
                else System.out.println("Invalid operation ");
                break;
            }
            case "viewapplications" : {
                viewApplications(args[1]);
                break;
            }
            case "viewpostedjobs" : {
                recruiter = (Recruiter) recruiterDb.getUser(args[1]);
                if(recruiter == null) {
                    System.out.println("Invalid Recruiter Credentials");
                }else viewJobs(recruiter);
                break;
            }
            case "searchprofile" : {
                user = administratorDb.getUser(args[1]);
                if(user==null){
                    System.out.println("No authorized person found with email = "+args[1]);
                }
                else if(user.getUserType().equalsIgnoreCase("recruiter") || user.getUserType().equalsIgnoreCase("administrator")){
                   viewProfile(args[2]);
                }
                else System.out.println("You are not authorized to view details");
                break;

            }
            case "searchjob" : {
                viewJob(args[1]);
                break;
            }
            case "searchjobs" : {
                viewJobs(args[1]);
                break;
            }
            case "searchjobsltminexperience" :{
                viewJobs(args[1], args[3]);
                break;
            }
            case "searchjobsgtnumberofvacancies" : {
                int numberOfVacancies = Integer.parseInt(args[3]);
                viewJobs(args[1], numberOfVacancies);
                break;
            }
            case "applyjob" : {
                applyJob(args[1], args[2]);
                break;
            }
            case "postjobs" : {
               postJobs(args);
               break;
            }
            case "updatejobs" : {
                postJobs(args);
                break;
            }
            case "deletejobs" : {
                deleteJobs(args);
                break;
            }
            case "viewapplicants" : {
                viewApplicants(args[1]);
                break;
            }
            case "viewapplicantsbyjobid" : {
                viewApplicants(args[1], args[2]);
                break;
            }
            case "selectapplicant" : {
                selectApplicant(args[1], args[2], args[3],args[4]);
                break;
            }
           
        }
    }

    public static String commandArgs(String[] args){
        if(args.length == 0)return null;
        else if(args.length == 1){
            if(args[0].toLowerCase().equals("help"))return "help";
            else if(args[0].toLowerCase().equals("viewjobs"))return "viewjobs";
        }
        else if(args.length == 2){
            if(args[0].toLowerCase().equals("login"))return "login1";
            else if(args[0].equalsIgnoreCase("logout"))return "logout";
            else if(args[0].equalsIgnoreCase("viewprofile"))return "viewprofile";
            else if(args[0].equalsIgnoreCase("updateprofile"))return "updateprofile";
            else if(args[0].toLowerCase().equals("viewjobs"))return "viewjobsjobseeker";
            else if(args[0].equalsIgnoreCase("viewapplications"))return "viewapplications";
            else if(args[0].equalsIgnoreCase("searchjob"))return "searchjob";
            else if(args[0].equalsIgnoreCase("searchjobs"))return "searchjobs";
            else if(args[0].equalsIgnoreCase("viewpostedjobs"))return "viewpostedjobs";
            else if(args[0].equalsIgnoreCase("viewapplicants"))return "viewapplicants";
            
        }
        else if(args.length==3){
            if(args[0].toLowerCase().equals("login"))return "login";
            else if(args[0].equalsIgnoreCase("register") && args[1].equalsIgnoreCase("administrator"))return "registeradministrator";
            else if(args[0].equalsIgnoreCase("register") && args[1].equalsIgnoreCase("recruiter"))return "registerrecruiter";
            else if(args[0].equalsIgnoreCase("register") && args[1].equalsIgnoreCase("jobseeker"))return "registerjobseeker";
            else if(args[0].equalsIgnoreCase("searchprofile"))return "searchprofile";
            else if(args[0].equalsIgnoreCase("applyjob"))return "applyjob";
            else if(args[0].equalsIgnoreCase("postjobs"))return "postjobs";
            else if(args[0].equalsIgnoreCase("updatejobs"))return "updatejobs";
            else if(args[0].equalsIgnoreCase("deletejobs"))return "deletejobs";
            else if(args[0].equalsIgnoreCase("viewapplicants"))return "viewapplicantsbyjobid";
        }
        else if(args.length==4){
            if(args[0].equalsIgnoreCase("searchjobs") && args[2].equalsIgnoreCase("lt"))return "searchjobsltminexperience";
            else if(args[0].equalsIgnoreCase("searchjobs") && args[2].equalsIgnoreCase("gt"))return "searchjobsgtnumberofvacancies";
            
        }
        else if(args.length==5){
            if(args[0].equalsIgnoreCase("selectapplicant"))return "selectapplicant";
        }
        return null;
    }

    public static void logout() throws SQLException{
        if(user.Logout())System.out.println("You are logged out");
    }
    public static void logout(User user) throws SQLException{
       if(user.Logout())System.out.println("You are logged out");
    }

    public static void registerRecruiter(String csvFilePath) throws SQLException{
        recruiter = new Recruiter();
        if(recruiter.Register(csvFilePath)){
            
            System.out.println("Registration Successfull!");
            recruiter.getDetails();        }
        else System.out.println("Registration failed!");
    }

    public static void registerJobSeeker(String csvFilePath) throws SQLException{
        jobseeker = new JobSeeker();
        if(jobseeker.Register(csvFilePath)){
            System.out.println("Registration Successfull!");
            jobseeker.getDetails();
        }
        else System.out.println("Registration failed!");
    }

    public static void registerAdministrator(String csvFilePath) throws SQLException{
        administrator = new Administrator();
        if(administrator.Register(csvFilePath)){
            System.out.println("Registration Successfull!");
           
        }
        else System.out.println("Registration failed!");
    }

    public static User login() throws SQLException{
        String email = UserInput.scanEmail();
        String password = UserInput.scanPassword();
        User user = administratorDb .getUser(email);
        if(user==null){
            System.out.println("Invalid Credentials");
           
        }
       else if(user.Login(email,password )){
            System.out.println("Login Successful!");
            
        }else{
            System.out.println("Wrong Password! Try Again!");
        }
        return user;
    }

    public static void postJobs(String[] args) throws SQLException{
        user = administratorDb.getUser(args[1]);
        if(user==null){
            System.out.println("Invalid Recruiter Credentials");
            return;
        }
        else if(user.isLoggedIn().equalsIgnoreCase("false")){
            System.out.println("Please login to continue");
            return;
        }
        else if(user.getUserType().equalsIgnoreCase("recruiter")){
            recruiter = (Recruiter) user;
            recruiter.postJobs(args[2]);
            System.out.println("Posted by : ");
            recruiter.getDetails();
        }
        else System.out.println("You are not authorized to perform this operation");
    }

    public static void deleteJobs(String[] args) throws SQLException{
        user = administratorDb.getUser(args[1]);
        if(user==null){
            System.out.println("Invalid Recruiter Credentials");
            return;
        }
        else if(user.isLoggedIn().equalsIgnoreCase("false")){
            System.out.println("Please login to continue");
            return;
        }
        else if(user.getUserType().equalsIgnoreCase("recruiter")){
            Recruiter.deleteJobs(args[2]);
        }
        else System.out.println("You are not authorized to perform this operation");
    }

    public static void selectApplicant(String recruiterEmail,String jobSeekerEmail,String jobID,String Message) throws SQLException{
        user = administratorDb.getUser(recruiterEmail);
        if(user==null){
            System.out.println("Invalid Credentials");
            return;
        }
        else if(user.isLoggedIn().equalsIgnoreCase("false")){
            System.out.println("Please Login to continue");
            return;
        }
        else if(user.getUserType().equalsIgnoreCase("recruiter")){
            recruiter = (Recruiter)user;
            Application application = recruiterDb.getApplicant(jobSeekerEmail, jobID);
            if(application==null){
                System.out.println("No application found with given details");
                return;
            }
            recruiter.selectApplicant(application, Message);

        }
    }

    public static void applyJob(String jobSeekerEmail,String jobID) throws SQLException{
        user = administratorDb.getUser(jobSeekerEmail);
        if(user==null){
            System.out.println("No jobseeker found with email = "+jobSeekerEmail);
            return;
        }
        else if(user.isLoggedIn()=="false"){
            System.out.println("Please Login to continue");
            return;
        }
        else if(user.getUserType().equalsIgnoreCase("jobseeker")){
            jobseeker = (JobSeeker)user;
            Job job = recruiterDb.getJobRecord(jobID);
            if(job==null){
                System.out.println("No Job Post is found with id = "+jobID);
                return;
            }
           if(jobseeker.applyForJob(job))System.out.println("Applied for Job successfully");
           else System.out.println("Application failed");
        }

    }


    

    public static User login(String[] args) throws SQLException{
        User user = administratorDb .getUser(args[1]);
        if(user==null){
            System.out.println("Invalid Credentials");
           
        }
        if(user.Login(args[1],args[2] )){
            System.out.println("Hello,"+user.getFirstName()+" "+user.getLastName()+" Login Successful!");
            
        }else{
            System.out.println("Try Again!");
        }
        return user;
    }
    
    public static void register() throws SQLException{
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("1.Jobseeker  2.Recruiter 3.Administrator");
               System.out.println("Choose the usertype :");
               int choice= in.nextInt();
               if(choice==1){
                
                jobseeker=UserInput.scanJobSeekerDetails();
                if(jobseeker.Register()){
                 user=administratorDb.getUser(jobseeker.getEmail());  
                System.out.println("Registration successful");
                jobSeekerMenu();
                }
               }
               if(choice==2){ 
                //recruiter= new Recruiter();
                recruiter =UserInput.scanRecruiterDetails();     
                if(recruiter.Register()){
                user=administratorDb.getUser(recruiter.getEmail());
                recruiterMenu();
                }
               }
               if(choice==3){
                administrator=UserInput.scanAdministratorDetails();
                if(administrator.Register()){
                 user=administratorDb.getUser(administrator.getEmail()); 
                administratorMenu();
               }
               }
               }
    }

    public static User login1(String[] args) throws SQLException{
        String password = UserInput.scanPassword();
        User user = administratorDb .getUser(args[1]);
        if(user==null){
            System.out.println("Invalid Credentials");
        
        }
        if(user.Login(args[1], password)){
            System.out.println("Login Successful!");
        }else{
            System.out.println("Try Again!");
        }
        return user;
    }

    public static void viewProfile(String email) throws SQLException{
        user = administratorDb.getUser(email);
        if(user==null){
            System.out.println("No profile found with email = "+email);
            return;
        }
        user.getDetails();
        
    }

    public static void viewApplications(String jobSeekerEmail) throws SQLException{
        for(Application application : jobSeekerDb.getApplications(jobSeekerEmail))application.printApplication();
    }

    public static void viewApplicants(String recruiterEmail) throws SQLException{
        user = administratorDb.getUser(recruiterEmail);
        if(user==null){
            System.out.println("no recruiter found with email = "+recruiterEmail);
            return;
        }
        else if(user.isLoggedIn()=="false"){
            System.out.println("Please Login to continue");
        }
        else if(user.getUserType().equalsIgnoreCase("recruiter")){
            recruiter = (Recruiter)user;
            for(Application application : recruiterDb.getApplicants(recruiter.getCompanyName()))application.printApplication();
        }
        
    }
    public static void viewApplicants(String recruiterEmail,String jobID) throws SQLException{
        user = administratorDb.getUser(recruiterEmail);
        if(user==null){
            System.out.println("no recruiter found with email = "+recruiterEmail);
            return;
        }
        else if(user.isLoggedIn()=="false"){
            System.out.println("Please Login to continue");
        }
        else if(user.getUserType().equalsIgnoreCase("recruiter")){
            recruiter = (Recruiter)user;
            for(Application application : recruiterDb.getApplicants(recruiter.getCompanyName())){
                if(application.getJobID().equalsIgnoreCase(jobID))
                application.printApplication();
            }
        }
        
    }

    public static void viewJobs() throws SQLException{
        ArrayList<Job> jobList = administratorDb.getAllJobs();
        for(Job job : jobList){
            job.printJobDetails();
            System.out.println("***********************************************************************************************************");
        }
    }

    public static void viewJobs(String jobTitle) throws SQLException{
        ArrayList<Job> jobList = administratorDb.getAllJobs(); 
        boolean var = true;
        for(Job job : jobList){
            String title = job.getJobTitle();
            if(title.equalsIgnoreCase(jobTitle) || title.matches(jobTitle) || title.contains(jobTitle) || title.contains(jobTitle) || jobTitle.matches(title)||jobTitle.contains(title) || checkStrings(jobTitle, title) || checkStrings(jobTitle, title)){
                job.printJobDetails();
                System.out.println("***********************************************************************************************************");  
                var = false;
            }
        }
        if(var)System.out.println("No Job Posts Found");
    }

    public static void viewJobs(String jobTitle,String minExperience) throws SQLException{
        int minExp = Integer.parseInt(minExperience);
        boolean var = true;
        ArrayList<Job> jobList = administratorDb.getAllJobs();
        for(Job job : jobList){
            String title = job.getJobTitle();
            if((title.equalsIgnoreCase(jobTitle) || title.matches(jobTitle) || title.contains(jobTitle) || jobTitle.contentEquals(title) || jobTitle.matches(title) || checkStrings(jobTitle, title) || checkStrings(title, jobTitle)) && job.getMinExperience()<=minExp){
                job.printJobDetails();
                System.out.println("***********************************************************************************************************");  
                var = false;
            }
        }
        if(var)System.out.println("No Job Posts Found!");
    }

    public static void viewJobs(String jobTitle,int numberOfVacancies) throws SQLException{
        boolean var = true;
        ArrayList<Job> jobList = administratorDb.getAllJobs();
        for(Job job : jobList){
            String title = job.getJobTitle();
            if((title.equalsIgnoreCase(jobTitle) || title.matches(jobTitle) || title.contains(jobTitle) || jobTitle.contentEquals(title) || jobTitle.matches(title) || checkStrings(title, jobTitle) || checkStrings(jobTitle, title)) && job.getNumberOfVacancies()>=numberOfVacancies){
                job.printJobDetails();
                System.out.println("***********************************************************************************************************");  
                var = false;
            }
        }
        if(var)System.out.println("No Job Posts Found!");
    }

    public static void viewJob(String jobID) throws SQLException{
        ArrayList<Job> jobList = administratorDb.getAllJobs();
        for(Job job : jobList){
           if(job.getId().equalsIgnoreCase(jobID)){
            job.printJobDetails();
            System.out.println("***********************************************************************************************************");
           }
        }
    }

    public static void viewJobs(Recruiter recruiter) throws SQLException{
        ArrayList<Job> jobList = recruiterDb.getJobsPosted(recruiter.getCompanyName());
        UserOutput.printJobTitles(jobList);
        int choice = UserInput.scanChoice();
        if(choice >=1 && choice <= jobList.size())jobList.get(choice-1).printJobDetails();
        else if(choice == jobList.size()+1){
            for(Job job : jobList){
                job.printJobDetails();
                System.out.println("***********************************************************************************************************");
            }
        }
        else{ System.out.println("Invalid Choice!");}
    }


public static String getString(char x)
{
 
    String s = String.valueOf(x);
    return s;
}
 

public static boolean checkStrings(String s1, String s2)
{
    String a = getString(s1.charAt(0)),
           b = getString(s2.charAt(0));

    for (int i = 1; i < s1.length(); i++)
        if (s1.charAt(i) != s1.charAt(i - 1))
        {
            a += getString(s1.charAt(i));
        }
 
   
    for (int i = 1; i < s2.length(); i++)
        if (s2.charAt(i) != s2.charAt(i - 1))
        {
            b += getString(s2.charAt(i));
        }
 
   
    if (a.equals(b))
        return true;
 
    return false;
}
   

}
