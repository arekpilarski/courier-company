package pl.kurs.Resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import pl.kurs.Entities.Route;
import pl.kurs.Services.CommonEJB;

@Path("/test/routes")
@Produces(MediaType.APPLICATION_XML)
public class RouteREST {

	@EJB
	CommonEJB bean;
	
	@GET
	public List<Route> getAllRoutes() {
		return bean.getAllRoutes();
	}
	
	@GET
	@Path("/{routeID}")
	public Route getRouteById(@PathParam("routeID") int routeID) {
		return bean.getRouteById(routeID);
	}
	
	@PUT
	@Path("/{routeID}")
	public Route addCourierToRoute (@PathParam ("routeID") int routeID,
			@QueryParam("courierID") int courierID) {
		return bean.addCourierToRoute(courierID, routeID);
	}
}
