package ie.gmit.sean;


import ie.gmit.sean.healthcheck.AppHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class UserServiceApplication extends Application<ClientConfig> {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceApplication.class);

    public static void main(String[] args) throws Exception {
        new UserServiceApplication().run(args);
    }

    @Override
    public void run(ClientConfig clientConfig, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        e.jersey().register(new UsersRESTController(e.getValidator()));

        // Health Check
        e.healthChecks().register("APIHealthCheck", new AppHealthCheck());
    }
}
