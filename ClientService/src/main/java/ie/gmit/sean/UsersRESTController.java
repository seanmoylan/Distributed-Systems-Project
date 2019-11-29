package ie.gmit.sean;

import com.google.protobuf.Message;
import ie.gmit.sean.api.User;
import ie.gmit.sean.api.UserLogin;
import ie.gmit.sean.client.Client;
import ie.gmit.sean.database.UserDB;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Path("/users")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UsersRESTController {
    private static  Logger logger = Logger.getLogger(Client.class.getName());

    private final Validator validator;
    private Client client;

    private final String HOST = "localhost";
    private final int PORT = 9999;

    public UsersRESTController(Validator validator){
        this.validator = validator;
        this.client = new Client(HOST, PORT);
    }

    public UsersRESTController(Validator validator, Client client){
        this.validator = validator;
        this.client = client;
    }

    @GET
    public Response getUsers(){
        //Return All users
        return Response.ok(UserDB.getUsers()).build();
    }

    @GET
    @Path("/{user_id}")
    public Response getUserById(@PathParam("user_id") Integer id){
        User user = UserDB.getUser(id);
        if(user != null){
            return Response.ok(user).build();
        }else{
            return Response.status(NOT_FOUND).entity("No User with id: "+id).build();
        }

    }

    @POST
    public Response createUser(User user)throws URISyntaxException {
        // validation
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        User u = UserDB.getUser(user.getUser_id());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<User> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (u == null) {
            //UserDB.createUser(user.getUser_id(), user);
            client.Hash(user);
            logger.info("USER: hashedPassword =  " + user.getHashed_password()+" Password = " + user.getPassword());
            return Response.created(new URI("/users/" + user.getUser_id()))
                    .build();
        } else
            return Response.status(NOT_FOUND).entity("Could not create user with id "+user.getUser_id()+" Id already in use!").build();

    }

    @PUT
    @Path("/{user_id}")
    public Response updateUserByID(@PathParam("user_id") Integer id, User user) throws URISyntaxException {
        // validation
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        User e = UserDB.getUser(user.getUser_id());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<User> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            client.Hash(user);

            return Response.ok(user).build();
        } else
            return Response.status(NOT_FOUND).entity("Could not update user of id "+id+", user not found!").build();

    }



    @DELETE
    @Path("/{user_id}")
    public Response removeUserByID(@PathParam("user_id") Integer id){
        User user = UserDB.getUser(id);
        if(user != null){
            UserDB.removeUser(id);
            return Response.ok().build();
        }else
            return Response.status(NOT_FOUND).entity("No user of id "+id+" found!").build();
    }

    @POST
    @Path("/login")
    public Response loginUser(UserLogin userLogin) {
        // validation
        Set<ConstraintViolation<UserLogin>> violations = validator.validate(userLogin);
        User e = UserDB.getUser(userLogin.getUser_id());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<UserLogin> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            if (client.Validate(userLogin.getPassword(), e.getHashed_password(), e.getSalt())) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(NOT_FOUND).entity("Password or ID are Invalid!").build();
            }

        } else {
            return Response.status(NO_CONTENT).entity("Password or ID are Invalid!").build();
        }


    }

}
