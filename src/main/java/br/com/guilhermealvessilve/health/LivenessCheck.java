package br.com.guilhermealvessilve.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class LivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
                .name("Quarkus Microprofile Health")
                .up()
                .withData("plugin", "1.0")
                .withData("systemTime", System.currentTimeMillis())
                .build();
    }
}
