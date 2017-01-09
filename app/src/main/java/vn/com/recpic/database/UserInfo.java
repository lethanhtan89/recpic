package vn.com.recpic.database;

/**
 * Created by Administrator on 04/01/2017.
 */

public class UserInfo {
    private int UserId;
    private String UserName;
    private String PassWord;
    private String ConfirmPassword;
    private String Images;
    private String Gender;
    private String Office;
    private String City;

    public UserInfo(){

    }

    public UserInfo(int UserId, String UserName, String Password, String confirmPassword, String Images, String Gender, String Office, String City){
        this.UserId = UserId;
        this.UserName= UserName;
        this.PassWord = Password;
        this.ConfirmPassword = confirmPassword;
        this.Images = Images;
        this.Gender = Gender;
        this.Office = Office;
        this.City = City;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getOffice() {
        return Office;
    }

    public void setOffice(String office) {
        Office = office;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
