package br.com.guilhermealvessilve.resource;

import br.com.guilhermealvessilve.service.OpenTracingService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("/opentracing")
public class OpenTracingResource {

    private static final Logger LOGGER = Logger.getLogger(OpenTracingResource.class);

    private final OpenTracingService service;

    @Inject
    public OpenTracingResource(OpenTracingService service) {
        this.service = service;
    }

    @GET
    public String tracing() {
        LOGGER.info("Tracing this endpoint");
        service.execute();
        service.executeAsync();
        return "tracing";
    }
}
