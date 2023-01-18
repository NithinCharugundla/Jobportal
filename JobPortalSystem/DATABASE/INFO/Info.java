package DATABASE.INFO;


import java.sql.*;

import DATABASE.UserDb;
import USER.ADMINISTRATOR.Administrator;
import USER.JOBSEEKER.JobSeeker;
import USER.RECRUITER.Recruiter;
public class Info extends UserDb {

   public void count(Administrator user) throws SQLException{
       
        resultSet =  statement.executeQuery("select usertype,count(email) from user group by usertype;");
      
         int js= resultSet.getInt(2);
         resultSet.next();
         int rc =resultSet.getInt(2);
         resultSet.next();
         int ad =resultSet.getInt(2);
         int total =js+rc+ad;
         System.out.println("                   Total logins : "+total);
         System.out.println("Jobseekers : "+js+"    Recruiters : "+rc+"Administraters : "+ad);
   }
  public void display_company() throws SQLException{
   int k=1;
   ResultSet resultSet = statement.executeQuery("select distinct company_name from jobs;");
   while(resultSet.next()){
     System.out.println(k+"."+resultSet.getString(1)); 
     k++;
   }
  }
 public void display_jobs() throws SQLException{
   ResultSet rs =statement.executeQuery("Select * from job;");
   System.out.println("--------------------------------------------------------------------------");
   System.out.printf("| %5s| %20s| %20s| %20s|\n","ID","Job title","loacation","company");
   System.out.println("--------------------------------------------------------------------------");
   while(rs.next()){
   System.out.printf("| %5s| %20s| %20s| %20s|\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
   }
   System.out.println("--------------------------------------------------------------------------");
 }
 public void display_users(Administrator user) throws SQLException{
  int c=0;
  ResultSet rs =statement.executeQuery("select * from users;");
  while(rs.next()){
    System.out.printf("| %30s| %20s| %10s",rs.getString("email"),rs.getString("usertype"),rs.getString("loginstatus"));
    if(rs.getString("loginstatus")=="1"){
      c++;
    }
  }
  System.out.println("Active users : "+c);
 }
public void display_applicants(Recruiter user) {
  String Query="Select a.email,a.id,a.jobtitle,j.skillrequired,j.minexperience from applicants a,jobseeker j where a.companyname=b.companyname and a.companyname='"+user.getCompanyName()+"';";
  ResultSet rs;
  try {
    rs = statement.executeQuery(Query);
  System.out.printf("| %30s| %10s| %20s | %20s| %11s|","email","job ID","Job title","skill needed","experience");
  while(rs.next()){
  System.out.printf("| %30s| %10s| %20s | %20s| %11s|",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));  
  }
} catch (SQLException e) {
  System.out.println(e);
  }
}
public void display_applicants_via_ID(Recruiter user,String ID){
String Query="Select a.email,a.id,a.jobtitle,j.skill1,j.skill2,j.skill3,j.minexperience from applicants a,jobseeker j where a.email=j.email and a.id='"+ID+"' and a.companyname='"+user.getCompanyName()+"';";
ResultSet rs;
try {
  rs = statement.executeQuery(Query);

System.out.printf("| %30s| %10s| %20s | %20s| %20s| %20s| %11s|","email","job ID","Job title","skill1","skill2","skill3","experience");
while(rs.next()){
System.out.printf("| %30s| %10s| %20s | %20s| %20s| %20s| %11s|",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));  
}
}
 catch (SQLException e) {
  System.out.println(e);
}
}
public void display_applicants_via_title(Recruiter user, String title) {
  String Query="Select a.email,a.id,a.jobtitle,j.skillrequired,j.minexperience from applicants a,jobseeker j where a.email=j.email and a.jobtitle='"+title+"' and a.companyname='"+user.getCompanyName()+"';"; 
  ResultSet rs;
  try {
    rs = statement.executeQuery(Query);
  
  System.out.printf("| %30s| %10s| %20s | %20s| %11s|","email","job ID","Job title","skill needed","experience");
  while(rs.next()){
  System.out.printf("| %30s| %10s| %20s | %20s| %11s|",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));  
  }
  }
   catch (SQLException e) {
    System.out.println(e);
  }
}
public JobSeeker getApplicantDetails(String email) throws SQLException {
  ResultSet rs = statement.executeQuery("Select u.*,j.* from user u, Jobseeker j where u.email=j.email and u.email='"+email+"';");
  rs.next();
 JobSeeker user = new JobSeeker(rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"),rs.getString("password"),rs.getString("gender"),rs.getString("mobilenumber"),rs.getString("dateofbirth"),rs.getString("usertype"),rs.getString("loginstatus"),rs.getInt("age"),rs.getString("college"),rs.getString("qualification"),rs.getDouble("percentage"),rs.getString("skill1"),rs.getString("skill2"),rs.getString("skill3"),rs.getInt("experience"));
   return user;
}

}
