package ie.gmit.sean.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.protobuf.ByteString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;

public class User {
    @NotNull
    private int user_id;
    @NotBlank @Length(min = 2, max = 15)
    private String user_name;
    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;
    @NotBlank @Length(min = 2, max = 15)
    private String password;

    /*
        These variables are only to be accessed by the constructor class
        when hashing the password. Method can be found in Client.java
     */
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
    @XmlElement
    public int getUser_id() {
        return user_id;
    }

    @XmlElement
    @JsonProperty
    public String getUser_name() {
        return user_name;
    }

    @JsonProperty
    @XmlElement
    public String getEmail() {
        return email;
    }

    @JsonProperty
    @XmlElement
    public String getPassword() {
        return password;
    }

    @JsonProperty
    @XmlElement
    public ByteString getHashed_password() {
        return hashed_password;
    }

    @JsonProperty
    @XmlElement
    public ByteString getSalt() {
        return salt;
    }

    public void setHashed_password(ByteString hashed_password) {
        this.hashed_password = hashed_password;
    }

    public void setSalt(ByteString salt) {
        this.salt = salt;
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
