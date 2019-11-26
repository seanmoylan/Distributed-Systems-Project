package ie.gmit.sean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {
    @NotNull
    int user_id;
    @NotBlank @Length(min = 2, max = 15)
    String user_name;
    @Pattern(regexp=".+@.+\\.[a-z]+")
    String email;
    @NotBlank @Length(min = 2, max = 15)
    String password;
    @NotBlank @Length(min = 2, max = 15)
    String hashed_password;
    @NotBlank @Length(min = 2, max = 15)
    String salt;

    public User(){

    }

    public User(int user_id, String user_name, String email, String password, String hashed_password, String salt){
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.hashed_password = hashed_password;
        this.salt = salt;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String toString(){
        return "ie.gmit.sean.User [id = "+ user_id+", user_name = "+user_name+
                ", email = "+email+", password = "+password+
                ", hashed_password = "+ hashed_password+", salt = "+salt;
    }
}
