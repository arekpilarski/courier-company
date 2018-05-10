package pl.kurs.Resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import pl.kurs.Entities.Package;
import pl.kurs.Entities.Package.State;
import pl.kurs.Services.CommonEJB;

@Path("/test/packages")
@Produces(MediaType.APPLICATION_XML)
public class PackageREST {

	@EJB
	CommonEJB bean;
	
	@GET
	public List<Package> getPackages(@QueryParam("start") String startPoint,
			@QueryParam("finish") String finishPoint) {
		if(startPoint == null || finishPoint == null)
			return bean.getAllPackages();
		return bean.getSpecifiedPackages(startPoint, finishPoint);
	}
	
	@GET
	@Path("/{packageID}")
	public Package getPackageById(@PathParam ("packageID") int packageID) {
		return bean.getPackageById(packageID);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Package createPackage(Package pack) {
		return bean.createPackage(pack);
	}
	

	@PUT
	@Path("/{packageID}")
	public Package updatePackageState(@PathParam ("packageID") int packageID,
			@QueryParam ("state") State newState,
			@QueryParam ("senderID") int senderID,
			@QueryParam ("recipientID") int recipientID) {
		if(newState!=null) 
			 return bean.updatePackageState(packageID, newState);
		if(senderID > 0 && recipientID > 0) {
			bean.addSenderToPackage(senderID, packageID);
			return bean.addRecipientToPackage(recipientID, packageID);
		}
		if(senderID > 0)
			return bean.addSenderToPackage(senderID, packageID);
		if(recipientID > 0)
			return bean.addRecipientToPackage(recipientID, packageID);
		return null;
	}
	
}
