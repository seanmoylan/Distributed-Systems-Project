package ie.gmit.sean.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class UserLogin {

    @NotNull
    private int user_Id;
    @NotBlank
    private String password;



    public UserLogin() {
    }

    @JsonProperty
    public int getUser_id() {
        return user_Id;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }
}