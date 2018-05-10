package pl.kurs.Resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import pl.kurs.Entities.Recipient;
import pl.kurs.Services.CommonEJB;

@Path("/test/recipients")
@Produces(MediaType.APPLICATION_XML)
public class RecipientREST {

	@EJB
	CommonEJB bean;
	
	@GET
	public List<Recipient> getAllRecipients() {
		return bean.getAllRecipients();
	}
	
	@GET
	@Path("/{id}")
	public Recipient getRecipientByID(@PathParam ("id") int id) {
		return bean.getRecipientByID(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Recipient createRecipient(Recipient recipient) {
		return bean.createRecipient(recipient);
	}
	
//	@PUT
//	@Path("/{id}")
//	@Consumes(MediaType.APPLICATION_XML)
//	public TestRecipient updateRecipient(@PathParam ("id") int id,
//			TestRecipient recipient) {
//		recipient.setID(id);
//		return bean.updateRecipient(recipient);
//	}
	
	@PUT
	@Path("/{recipientID}")
	public Recipient addRecipientToAddress(@PathParam ("recipientID") int recipientID,
			@QueryParam("addressID") int addressID) {
		if(addressID >= 0)
			return bean.addRecipientToAddress(recipientID, addressID);
		return null;
	}
	
	@DELETE
	@Path("/{id}")
	public String removeRecipient(@PathParam ("id") int id) {
		return bean.removeRecipient(id);
		
	}
	
}
