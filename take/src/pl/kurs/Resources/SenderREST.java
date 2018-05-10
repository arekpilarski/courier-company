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

import pl.kurs.Entities.Sender;
import pl.kurs.Services.CommonEJB;

@Path("/test/senders")
@Produces(MediaType.APPLICATION_XML)
public class SenderREST {

	@EJB
	CommonEJB bean;
	
	@GET
	public List<Sender> getAllSenders() {
		return bean.getAllSenders();
	}
	
	@GET	
	@Path("/{id}")
	public Sender getSenderByID(@PathParam ("id") int id) {
		return bean.getSenderByID(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Sender createSender(Sender sender) {
		return bean.createSender(sender);
	}
	
//	@PUT
//	@Path("/{id}")
//	@Consumes(MediaType.APPLICATION_XML)
//	public TestSender updateSender(@PathParam ("id") int id, 
//			@QueryParam ("addressID") int addressID,
//			TestSender sender) {
//		if(addressID >= 0)
//			return bean.addSenderToAddress(id, addressID);
//		sender.setID(id);
//		return bean.updateSender(sender);
//	}
	
	@PUT
	@Path("/{senderID}")
	public Sender addSenderToAddress(@PathParam ("senderID") int senderID,
			@QueryParam("addressID") int addressID) {
		if(addressID >= 0)
			return bean.addSenderToAddress(senderID, addressID);
		return null;
	}
	
	@DELETE
	@Path("/{id}")
	public String removeSender(@PathParam ("id") int id) {
		return bean.removeSender(id);
		
	}
	
}
