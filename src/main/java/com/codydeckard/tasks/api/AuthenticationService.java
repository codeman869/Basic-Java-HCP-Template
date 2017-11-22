package com.codydeckard.tasks.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

//import org.eclipse.persistence.oxm.MediaType;

@Path("/auth")
@Produces({ MediaType.APPLICATION_JSON })
public class AuthenticationService {
	
	@GET
	@Path("/")
	public String getRemoteUser(@Context SecurityContext ctx) {
		String retval = "anonymous";
		
		try {
			retval = ctx.getUserPrincipal().getName();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return retval;
	}

}
