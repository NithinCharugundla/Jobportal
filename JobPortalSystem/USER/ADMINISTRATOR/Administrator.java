package USER.ADMINISTRATOR;

import java.io.FileReader;  
import java.io.IOException;
import java.sql.SQLException;


import java.util.ArrayList;

import com.opencsv.CSVReader;

import DATABASE.AdministratorDb;
import USER.User;
/*
 * Administrator class inherits User class
 */
import USER.JOBSEEKER.JobSeeker;

public class Administrator extends User{
    private String AdministratorKey;
    private static AdministratorDb administratorDb = new AdministratorDb();

    public Administrator(String firstName, String lastName, String email, String password, String gender,
            String mobileNumber, String dateOfBirth, String userType, String isLoggedIn) {
        super(firstName, lastName, email, password, gender, mobileNumber, dateOfBirth, userType, isLoggedIn);
    }

    public Administrator(String firstName, String lastName, String email, String password, String gender,
            String mobileNumber, String dateOfBirth, String administratorKey) {
        super(firstName, lastName, email, password, gender, mobileNumber, dateOfBirth);
        AdministratorKey = administratorKey;
    }

    public Administrator(String firstName, String lastName, String email, String password, String gender,
            String mobileNumber, String dateOfBirth) {
        super(firstName, lastName, email, password, gender, mobileNumber, dateOfBirth);
    }

    public Administrator() {

    }

    @Override
    public boolean Login (String email, String password) throws SQLException {
        if(getEmail().equals(email)){
          if(getPassword().equals(password)){
            setLoggedIn("true");
            return administratorDb.updateUserLoginStatus(email, password);
          }  
        } 
        return false;
    }

    @Override
    public boolean Register() throws SQLException {
       
        return(administratorDb.addUserRecord(this) &&
        administratorDb.addAdministratorRecord(this));
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
        this.setUserType("administrator");
        this.setLoggedIn("true");
        
           
            return(administratorDb.addUserRecord(this)); 
        }  
        catch (Exception e)   
        {  
        e.printStackTrace(); 
        return false ;
        }  
        
        
    }

    

    @Override
    public boolean Logout() throws SQLException {
         setLoggedIn("false");
        return administratorDb.updateUserLoginStatus(this.getEmail(), "false");
    }

    @Override
    public boolean deleteUser() throws SQLException {
        return(administratorDb.deleteUserRecord(this) && administratorDb.deleteAdministratorRecord(this));
    }

    public String getAdministratorKey() {
        return AdministratorKey;
    }

    public void setAdministratorKey(String administratorKey) {
        AdministratorKey = administratorKey;
    }
    /* methods, a administrator has access to do  */
    public ArrayList<JobSeeker> getAllJobSeekerList() throws SQLException{
       return administratorDb.getAllJobSeekers();
    }

    public boolean approveUser(User user){
        //TODO
        return false;
    }
    public boolean removeUser(User user){
        //TODO
        return false;
    }

    public void getDetails() {
        System.out.println("Name            : "+this.getFirstName()+" "+getLastName());
        System.out.println("Date of birth   : "+this.getDateOfBirth());
        System.out.println("Gender          : "+this.getGender());
        System.out.println("Email           : "+this.getEmail());
        System.out.println("Contact number  : "+this.getMobileNumber());
    }

    /*more methods to add come here */
}
