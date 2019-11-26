package ie.gmit.sean;

import ie.gmit.sean.User;
import ie.gmit.sean.UserDB;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersRESTController {

    private final Validator validator;

    public UsersRESTController(Validator validator){
        this.validator = validator;
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
            return Response.noContent().build();
        }

    }

    @POST
    public Response createUser(User user)throws URISyntaxException {
        return getResponse(user);

    }

    @PUT
    @Path("/{user_id}")
    public Response updateUserByID(@PathParam("user_id") Integer id, User user) throws URISyntaxException {
        return getResponse(user);

    }

    private Response getResponse(User user) throws URISyntaxException {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        User e = UserDB.getUser(user.getUser_id());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<User> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (e != null) {
            UserDB.updateUser(user.getUser_id(), user);
            return Response.created(new URI("/employees/" + user.getUser_id()))
                    .build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{user_id}")
    public Response removeUserByID(@PathParam("user_id") Integer id){
        User user = UserDB.getUser(id);
        if(user != null){
            UserDB.removeUser(id);
            return Response.ok().build();
        }else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
