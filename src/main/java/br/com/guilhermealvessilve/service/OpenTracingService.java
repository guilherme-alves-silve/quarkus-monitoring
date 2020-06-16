package br.com.guilhermealvessilve.service;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OpenTracingService {

    private static final Logger LOGGER = Logger.getLogger(OpenTracingService.class);

    public void execute() {
        LOGGER.info("Logging the service");
    }

    public void executeAsync() {
        new Thread(() -> {
            LOGGER.info("Logging the service async");
        }).start();
    }
}
