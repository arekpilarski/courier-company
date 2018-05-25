package pl.kurs.Services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/client")
@Produces("text/plain")
public class Sender {

	@GET
	@Path("/test")
	public String test() {
		return "HELLO";
	}
}
