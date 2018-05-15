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

import pl.kurs.Entities.Address;
import pl.kurs.Services.CommonEJB;

@Path("/test/addresses")
@Produces(MediaType.APPLICATION_XML)
public class AddressREST {

	@EJB
	CommonEJB bean;
	
	@GET
	public List<Address> getAllAddresses() {
		return bean.getAllAddresses();
	}
	
	@GET
	@Path("/{addressID}")
	public Address getAddressByID(@PathParam ("addressID") int addressID) {
		return bean.getAddressByID(addressID);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Address createAddress(Address address) {
		return bean.createAddress(address);
	}
	
}
