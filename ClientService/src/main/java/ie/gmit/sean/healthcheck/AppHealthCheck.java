package ie.gmit.sean.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import ie.gmit.sean.client.Client;


public class AppHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {

        return Result.healthy();
    }
}
