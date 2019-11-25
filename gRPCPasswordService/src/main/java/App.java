import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(App.class);

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        e.jersey().register(new UsersRESTController(e.getValidator()));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
