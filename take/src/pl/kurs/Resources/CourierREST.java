package pl.kurs.Resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.kurs.Entities.Courier;
import pl.kurs.Services.CommonEJB;

@Path("/test/couriers")
@Produces(MediaType.APPLICATION_XML)
public class CourierREST {

	@EJB
	CommonEJB bean;
	
	@GET
	public List<Courier> getAllCouriers() {
		return bean.getAllCouriers();
	}
	
	@GET
	@Path("/{courierID}")
	public Courier getCourierById(@PathParam ("courierID") int courierID) {
		return bean.getCourierById(courierID);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Courier createCourier(Courier courier) {
		return bean.createCourier(courier);
	}
}
