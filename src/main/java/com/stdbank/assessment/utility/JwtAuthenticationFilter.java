package com.stdbank.assessment.utility;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;

@Provider
public class JwtAuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Check for the presence of the JWT token and validate it
    }
}