package br.com.guilhermealvessilve.resource;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Random;

@Path("/number")
public class NumberResource {

    private static final int MAX_NUMBER = 100;

    private static final Logger LOGGER = Logger.getLogger(NumberResource.class);

    private volatile int lastNumber;

    @GET
    @Timed(
            name = "random_number",
            description = "[GET] Random number",
            unit = MetricUnits.MILLISECONDS
    )
    @Counted(
            name = "counted_number",
            description = "[GET] Counted random number"
    )
    public Integer getRandomNumber() throws InterruptedException {
        LOGGER.info("[GET] Calling getRandomNumber");
        final var random = new Random();
        final var number = random.nextInt(MAX_NUMBER);
        Thread.sleep(number * 100);
        lastNumber = number;
        return number;
    }

    @GET
    @Path("/last")
    @Gauge(
            name = "last_number",
            description = "[GET] Last number",
            unit = MetricUnits.NONE
    )
    public Integer getLastNumber() {
        LOGGER.info("[GET] Calling getLastNumber");
        return lastNumber;
    }
}
