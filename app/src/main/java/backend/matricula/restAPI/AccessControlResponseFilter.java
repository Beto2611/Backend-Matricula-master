package backend.matricula.restAPI;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AccessControlResponseFilter implements ContainerResponseFilter {
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        containerResponseContext.getHeaders().add("Access-Control-Max-Age", "10");  // # of seconds
    }
}