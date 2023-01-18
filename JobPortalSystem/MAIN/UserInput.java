package MAIN;

import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

import USER.User;
import USER.ADMINISTRATOR.Administrator;
import USER.JOBSEEKER.JobSeeker;
import USER.RECRUITER.Job;
import USER.RECRUITER.Recruiter;
import DATABASE.RecruiterDb;
import DATABASE.INFO.Info;

public  class UserInput {
    private static Scanner scanner = new Scanner(System.in);
   private static RecruiterDb recruiterDb = new RecruiterDb();

    public static JobSeeker scanJobSeekerDetails(){
        JobSeeker jobSeeker = new JobSeeker();
        System.out.print("Enter First Name : ");
        jobSeeker.setFirstName(scanner.next());
        System.out.print("Enter Last Name : ");
        jobSeeker.setLastName(scanner.next());
        System.out.print("Enter Email : ");
        jobSeeker.setEmail(scanner.next());
        System.out.print("Enter Password : ");
        jobSeeker.setPassword(scanner.next());
        System.out.print("Enter Date Of Birth : ");
        jobSeeker.setDateOfBirth(scanner.next());
        System.out.print("Enter age : ");
        jobSeeker.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter gender : ");
        jobSeeker.setGender(scanner.next());
        System.out.print("Enter mobile number : ");
        jobSeeker.setMobileNumber(scanner.next());
        scanner.nextLine();
        System.out.print("Enter qualification : ");
        jobSeeker.setQualification(scanner.nextLine());
        System.out.print("Enter college : ");
        jobSeeker.setCollege(scanner.nextLine());
        System.out.print("Enter percentage : ");
        jobSeeker.setPercentage(scanner.nextDouble());
        scanner.nextLine();
        System.out.print("Enter skill1 : ");
        jobSeeker.setSkill1(scanner.nextLine());
        System.out.print("Enter skill2 : ");
        jobSeeker.setSkill2(scanner.nextLine());
        System.out.print("Enter skill3 : ");
        jobSeeker.setSkill3(scanner.nextLine());
        System.out.print("Enter experience : ");
        jobSeeker.setExperience(scanner.nextInt());
        jobSeeker.setLoggedIn("true");
        return jobSeeker;
    }

    public static Recruiter scanRecruiterDetails(){
        Recruiter recruiter = new Recruiter();
        System.out.print("Enter First Name : ");
        recruiter.setFirstName(scanner.next());
        System.out.print("Enter Last Name : ");
        recruiter.setLastName(scanner.next());
        System.out.print("Enter Email : ");
        recruiter.setEmail(scanner.next());
        System.out.print("Enter Password : ");
        recruiter.setPassword(scanner.next());
        System.out.print("Enter Date Of Birth : ");
        recruiter.setDateOfBirth(scanner.next());
        System.out.print("Enter gender : ");
        recruiter.setGender(scanner.next());
        System.out.print("Enter mobile number: ");
        recruiter.setMobileNumber(scanner.next());
        System.out.print("Enter Company name : ");
        recruiter.setCompanyName(scanner.next());
        System.out.print("Enter Designation : ");
        recruiter.setDesignation(scanner.next());
        recruiter.setLoggedIn("true");
        return recruiter;
    }
    public static Job scanJobDetails(){
        Job job = new Job();
        System.out.print("Enter Id :");
        job.setId(scanner.next());
        System.out.print("Enter Job Title :");
        job.setJobTitle(scanner.next());
        System.err.print("Enter Company Name :");
        job.setCompanyName(scanner.next());
        System.out.print("Enter Company Location :");
        job.setLocation(scanner.next());
        System.out.print("Enter DeadLine :");
        job.setDeadline(scanner.next());
        System.out.print("Enter Number of Vacancies :");
        job.setNumberOfVacancies(scanner.nextInt());
        System.out.print("Enter Skills Required :");
        job.setSkillRequired(scanner.next());
        System.out.print("Enter Maximum Age Limit :");
        job.setMaxAge(scanner.nextInt());
        System.out.print("Enter Min Experience Required : ");
        job.setMinExperience(scanner.nextInt());
        System.out.print("Enter Description :");
        job.setDescription(scanner.next());
        return job;
    }
    public static Administrator scanAdministratorDetails() {
        Administrator administrator=new Administrator();
        JobSeeker jobSeeker = new JobSeeker();
        System.out.print("Enter First Name    : ");
        administrator.setFirstName(scanner.next());
        System.out.print("Enter Last Name     : ");
        administrator.setLastName(scanner.next());
        System.out.print("Enter Email         : ");
        administrator.setEmail(scanner.next());
        System.out.print("Enter Password      : ");
        administrator.setPassword(scanner.next());
        System.out.print("Enter Date Of Birth : ");
        administrator.setDateOfBirth(scanner.next());
        System.out.println("Gender            : ");
        administrator.setGender(scanner.next());
        System.out.println("Mobile number : ");
        administrator.setMobileNumber(scanner.next());
        return administrator;
    }

    public static String scanPassword(){
        System.out.print("Enter Password : ");
        String password = scanner.next();
        return password;
    }

    public static int scanChoice(){
        System.out.print("Enter Choice : ");
        int choice = scanner.nextInt();
        return choice;
    }
    public static String scanEmail(){
        System.out.print("Enter Email : ");
        String email = scanner.next();
        return email;
    }
    
    public static void applyjobs(JobSeeker user) throws SQLException{
       System.out.print("Enter job ID : ");
       String ID = scanner.next();
       boolean flag=false;
        user.setEligibleJobs();
       for(Job i : user.getEligibleJobs()){
        if(i.getId().equals(ID)){
            if(user.applyForJob(i)==true){
             System.out.println("****Application successful****"); 
            }
            else {
             System.out.println("****Application failed****");   
            }
            flag=true;
            break;
        }
        if(!flag){
         System.out.println("you aren't eligible for the job");   
        }
       }
    }
     
     public static void postJob(Recruiter user) throws SQLException{
        Job job = new Job();
        if(user.postJob(job)==true)  
        {
           System.out.println("****Job posted successfully****");
        }
        else {
            System.out.println("****Failed to post the job****");   
           }
    }
    
      public static void deleteJob(Recruiter user) throws SQLException{
        System.out.print("Enter job ID : ");
        String ID = scanner.next();
        boolean flag=false;
        for(Job i : user.getPostedJobs()){
         if(i.getId().equals(ID)){
             if(user.deleteJob(i)==true){
              System.out.println("****Job deletion successful****"); 
             }
             else {
              System.out.println("****Job deletion failed****");   
             }
             flag=true;
             break;
         }
         if(!flag){
          System.out.println("Job is not in the records");   
         }
        }
     }
     
    public static void modifyJobseeker(JobSeeker jobSeeker) throws SQLException {
       UserOutput.printUpdatesRequirement(jobSeeker);
       int choice = scanner.nextInt();
       switch(choice){
        case 1 : {
            System.out.print("Enter new firstname : ");
            String fn= scanner.next();
            jobSeeker.setFirstName(fn);
            break;
        }
        case 2 : {
          System.out.print("Enter new last name : ");  
          String ln = scanner.next();
          jobSeeker.setLastName(ln);
          break;
        }
        case 3 :{
           System.out.print("Enter new email : ");
           String email=scanner.next();
           jobSeeker.setEmail(email);
           break; 
        }
        case 4 : {
          System.out.print("Enter new password : ");
          String password = scanner.next();
          jobSeeker.setPassword(password);
          break;  
        }
        case 5 : {
           System.out.print("Enter new date of birth(YYYY-MM-DD) : ");
           String date = scanner.next();
           jobSeeker.setDateOfBirth(date);
           break;  
        }
        case 6 : {
          System.out.print("Enter age : ");
           int age = scanner.nextInt();
           jobSeeker.setAge(age);
           break;  
        }
        case 7 : {
            System.out.print("Enter experience : ");
            int exp=scanner.nextInt();
            jobSeeker.setExperience(exp);
            break;
        }
        case 8 : {
            System.out.print("Enter new mobile number : ");
            String mn = scanner.next();
            jobSeeker.setMobileNumber(mn);
            break;
        }
        case 9 : {
            Main.jobSeekerMenu();
        }
    }
        if(choice!=9){
          jobSeeker.deleteUser();
          jobSeeker.Register();  
          System.out.println("Successfully updated");
        }
        Main.jobSeekerMenu();
    }
    
     public static void modifyRecruiter(Recruiter recruiter) throws SQLException{
        UserOutput.printUpdatesRequirement();
        int choice = scanner.nextInt();
        switch(choice)
        {
            case(1):
            {
                System.out.print("Enter the new First Name : ");
                String name=scanner.next();
                recruiter.setFirstName(name);
                break;
            }
            case(2):
            {
                System.out.print("Enter new Last Name : ");
                String name=scanner.next();
                recruiter.setLastName(name);
                break;
            }
            case(3):
            {
                System.out.print("Enter new email : ");
                String email=scanner.next();
                recruiter.setEmail(email);
                break;
            }
            case(4):
            {
                System.out.print("Enter new password : ");
                String password=scanner.next();
                recruiter.setPassword(password);
                break;
            }
            case(5):
            {
                System.out.print("Enter new Date Of Birth (yyyy-MM-dd) : ");
                String dob=scanner.next();
                recruiter.setDateOfBirth(dob);
                break;
            }
            case(6):
            {
                System.out.print("Enter new Mobile Number : ");
                String mn=scanner.next();
                recruiter.setMobileNumber(mn);
                break;
            }
            case(7):
            {  
                Main.recruiterMenu();
            }
            
        }
           if(choice!=7)
           {
                recruiter.deleteUser();
                recruiter.Register();
                System.out.println("Successfully updated");
           }
           Main.recruiterMenu();
        
    }
    
    public static void modifyJob(Recruiter user,Job job) throws SQLException {
        UserOutput.printUpdatesRequirement(job);
        int choice = scanner.nextInt();
        switch(choice){
         case 1 : {
             System.out.print("Enter new Job Title : ");
             String jt= scanner.next();
             job.setJobTitle(jt);
             break;
         }
         case 2 : {
           System.out.print("Enter new location: ");  
           String ln = scanner.next();
           job.setLocation(ln);
           break;
         }
         case 3 :{
            System.out.print("Enter company name : ");
            String cn=scanner.next();
            job.setCompanyName(cn);
            break; 
         }
         case 4 : {
           System.out.print("Enter new deadline : ");
           String dl = scanner.next();
           job.setDeadline(dl);
           break;  
         }
         case 5 : {
            System.out.print("Enter number of Vacancies: ");
            int vac = scanner.nextInt();
            job.setNumberOfVacancies(vac);
            break;  
         }
         case 6:{
            System.out.print("Enter skills required : ");
            String sr = scanner.next();
            job.setSkillRequired(sr);
            break;  
         }
         case 7: {
             System.out.print("Enter Maximum age limit: ");
             int ma = scanner.nextInt();
             job.setMaxAge(ma);
             break; 
         }
         case 8 : {
            System.out.print("Enter experience : ");
            int exp=scanner.nextInt();
            job.setMinExperience(exp);
            break; 
            
         }
         case 9 : {
            System.out.print("Enter description : ");
            String desc = scanner.next();
            job.setDescription(desc);
            break;
         }
         case 10:{
            System.out.print("Enter job Id : ");
            String id = scanner.next();
            job.setId(id);
            break;
         }
         case 11 : {
             Main.recruiterJobMenu();
         }
     }
         if(choice!=11){
           RecruiterDb recruiterDb = new RecruiterDb();
           recruiterDb.updateJobRecord(job);
           System.out.println("Successfully updated");
         }
         Main.recruiterJobMenu();
     }


    public static void modifyAdministrator(Administrator user) throws SQLException {
        UserOutput.printUpdatesRequirement();
        System.out.print("Enter the field you wish to update : ");
        int choice = scanner.nextInt();
        switch(choice)
        {
            case(1):
            {
                System.out.print("Enter the new First Name : ");
                String name=scanner.next();
                user.setFirstName(name);
                break;
            }
            case(2):
            {
                System.out.print("Enter new Last Name : ");
                String name=scanner.next();
                user.setLastName(name);
                break;
            }
            case(3):
            {
                System.out.print("Enter new email : ");
                String email=scanner.next();
                user.setEmail(email);
                break;
            }
            case(4):
            {
                System.out.print("Enter new password : ");
                String password=scanner.next();
                user.setPassword(password);
                break;
            }
            case(5):
            {
                System.out.print("Enter new Date Of Birth (yyyy-MM-dd) : ");
                String dob=scanner.next();
                user.setDateOfBirth(dob);
                break;
            }
            case(6):
            {
                System.out.print("Enter new Mobile Number : ");
                String mn=scanner.next();
                user.setMobileNumber(mn);
                break;
            }
            case(7):
            {  
                Main.administratorMenu();
            }
            
        }
           if(choice!=7)
           {
                user.deleteUser();
                user.Register();
                System.out.println("Successfully updated");
           }
           Main.administratorMenu();
    }

    public static void selectApplicant(Recruiter user) throws SQLException {
        System.out.print("Enter JobSeeker Email : ");
        String email=scanEmail();
        System.out.print("Enter Job ID : ");
        String jobID = scanner.next();
        System.out.print("Description : ");
        String description = scanner.nextLine();
        
        user.selectApplicant(recruiterDb.getApplicant(email, jobID),description);
    }

    public static void display_applicantViaID(Recruiter user) {
        Info info=new Info();
        System.out.print("Enter Job ID : ");
        String ID =scanner.next();
        info.display_applicants_via_ID(user, ID);

    }
    public static void display_applicantViaTitle(Recruiter user) {
        Info info=new Info();
        System.out.print("Enter Job title : ");
        String title =scanner.next();
        info.display_applicants_via_title(user, title);
        
    }

    public static void viewdetails(Recruiter recruiter) throws SQLException {
        UserOutput.printApplicantDetails();
        int choice = scanChoice();
        if(choice==1){
        String email=scanEmail();
        Info info=new Info();
        JobSeeker jobSeeker=info.getApplicantDetails(email);
        jobSeeker.getDetails();
        }
        if(choice==2){
         Main.applicationsMenu();
        }
    }

   
     
  
}
