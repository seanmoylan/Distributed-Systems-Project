import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDB {
    public static HashMap<Integer, User> Users = new HashMap<>();

    static {
        Users.put(1, new User(1, "Sean Moylan", "seanmoylan1@icloud.com", "password", " ", " "));
        Users.put(2, new User(2, "Conor Moylan", "conormoylan1@icloud.com", "password2", " ", " "));
    }

    public static List<User> getUsers(){
        return new ArrayList<User>(Users.values());
    }

    public static User getUser(Integer id){
        return Users.get(id);
    }

    public static void updateUser(Integer id, User user){
        Users.put(id, user);
    }

    public static void removeUser(Integer id){
        Users.remove(id);
    }
}

