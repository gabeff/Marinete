package br.com.marineteapp.application;

import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import br.com.marineteapp.provider.AuthenticationFilter;
import br.com.marineteapp.provider.MarineteMapperProvider;

@ApplicationPath("/rest")
public class MarineteApplication extends ResourceConfig {
 
    private static final Logger LOGGER = null;

	public MarineteApplication() {
        // Register resources and providers using package-scanning.
        packages("br.com.marineteapp.resource","br.com.marineteapp.provider");
 
        // Register my custom provider - not needed if it's in my.package.
        register(AuthenticationFilter.class);
        //register(MarineteMapperProvider.class);
        register(JacksonFeature.class);
        // Register an instance of LoggingFilter.
        register(new LoggingFilter(LOGGER, true));
 
        // Enable Tracing support.
        property(ServerProperties.TRACING, "ALL");
    }
}