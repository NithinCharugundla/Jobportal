package USER;

import java.sql.SQLException;

/*
 *  User class is an Abstract class where Register and Login are some abstract methods 
 *  where its implementation depends on the type of User who uses it
 *  Here type of user means : jobSeeker,Recuiter or Administrator where there will some details differs for each type
 *  these classes inherits User class
 */

public abstract class User{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String mobileNumber;
    private String dateOfBirth;
    private String userType;
    private String isLoggedIn;
    
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    /*
     * data fields above are the basic details of user(any of jobSeeker,Recuiter,Administrator) who uses Job Portal System
     */
    public User(){

    }
    public User(String email){
        this.email = email;
    }
    public User(String firstName, String lastName, String email, String password, String gender, String mobileNumber,
            String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        
    }
    public User(String firstName, String lastName, String email, String password, String gender, String mobileNumber,
            String dateOfBirth, String userType, String isLoggedIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.userType = userType;
        this.isLoggedIn = isLoggedIn;
    }
    /*Constructor for User class with all arguments */
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /*Above are the getter and setter methods for all data fields */
    
    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
                + ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", dateOfBirth=" + dateOfBirth + "]";
    } 
    /*below are the abstract methods in User class */
    public abstract boolean Login(String email,String password) throws SQLException;
    public abstract boolean Register() throws SQLException;
    public abstract boolean Logout() throws SQLException;
    public abstract boolean deleteUser() throws SQLException;
    public abstract void getDetails();
    public String isLoggedIn() {
        return isLoggedIn;
    }
    public void setLoggedIn(String isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
  
    
}