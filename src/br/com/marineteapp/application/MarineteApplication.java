package br.com.marineteapp.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import br.com.marineteapp.provider.AuthenticationFilter;

@ApplicationPath("/rest")
public class MarineteApplication extends ResourceConfig {
 
	public MarineteApplication() {
        // Register resources and providers using package-scanning.
        packages("br.com.marineteapp.resource","br.com.marineteapp.provider");
 
        // Register custom providers
        register(AuthenticationFilter.class);
        
        // Register JSON handler
        register(JacksonFeature.class);
    }
}