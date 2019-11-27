package ie.gmit.sean.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.protobuf.ByteString;
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

    // Imutable
    private ByteString hashed_password;
    private ByteString salt;

    public User(){

    }

    public User(int user_id, String user_name, String email, String password){
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
    }

    public User(int user_id, String user_name, String email, ByteString hashed_password, ByteString salt){
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.hashed_password = hashed_password;
        this.salt = salt;
    }

    @JsonProperty
    public int getUser_id() {
        return user_id;
    }

    @JsonProperty
    public String getUser_name() {
        return user_name;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public ByteString getHashed_password() {
        return hashed_password;
    }

    @JsonProperty
    public ByteString getSalt() {
        return salt;
    }

    public String toString(){
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", hashed_password=" + hashed_password +
                ", salt=" + salt + '}';
    }
}
