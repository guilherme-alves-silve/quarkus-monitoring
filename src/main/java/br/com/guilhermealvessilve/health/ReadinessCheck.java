package br.com.guilhermealvessilve.health;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    private static final long WAITING_TIME = 5000L;
    private static final Logger LOGGER = Logger.getLogger(ReadinessCheck.class);

    private volatile boolean ready = false;

    public void onStart(@Observes StartupEvent event) throws InterruptedException {
        new Thread(() -> {
            try {
                LOGGER.info("Waiting the application to be ready");
                Thread.sleep(WAITING_TIME);
                ready = true;
            } catch (final InterruptedException ex) {
                LOGGER.error("An error has occurred, error: " + ex.getMessage(), ex);
            }
        }).start();
    }

    @Override
    public HealthCheckResponse call() {

        if (ready) {
            return HealthCheckResponse.up("Ready");
        }

        return HealthCheckResponse.down("Waiting");
    }
}
