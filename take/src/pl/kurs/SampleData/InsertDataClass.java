package pl.kurs.SampleData;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.kurs.Entities.Address;
import pl.kurs.Entities.Courier;
import pl.kurs.Entities.Package;
import pl.kurs.Entities.Recipient;
import pl.kurs.Entities.Sender;
import pl.kurs.Entities.Package.Size;
import pl.kurs.Services.CommonEJB;

@Path("/test/insert")
public class InsertDataClass {

	@EJB
	CommonEJB bean;
	
	@GET
	public String insertData() {

		Sender sender = new Sender("Arek", "Thomson", "arekThomson@mail.com", "400-322-111");
		Recipient recipient = new Recipient("Thomas", "Thomson", "Thomson@mail.com", "477-322-111");
		
		Recipient recipient2 = new Recipient("Mary", "Greenwich", "marygreenwich@mail.com", "400-310-111");
		Sender sender2 = new Sender("Rob", "Wellman", "robwellman@mail.com", "478-322-111");
		
		Sender sender3 = new Sender("Marek", "Wieczorek", "wieczorek@mail.com", "290-321-111");
		Recipient recipient3 = new Recipient("Wojciech", "Strasz", "strasz@mail.com", "477-202-110");
		
		Recipient recipient4 = new Recipient("Iwona", "Luko", "lukoiw@mail.com", "433-310-111");
		Sender sender4 = new Sender("Anna", "Wolos", "wollos@mail.com", "478-879-111");
		
		Sender sender5 = new Sender("Luiza", "Fret", "lufret@mail.com", "400-322-122");
		Recipient recipient5 = new Recipient("Krzysztof", "Lodoski", "lodoskik@mail.com", "407-322-111");
		
		Recipient recipient6 = new Recipient("Ferdynand", "Kiepowicz", "kiepoferd@mail.com", "520-210-111");
		Sender sender6 = new Sender("Marta", "Sudo", "sudomart@mail.com", "478-300-111");
		

		Address address = new Address("Polska", "Œl¹sk", "Gliwice", "Wieczorka 12");
		Address address2 = new Address("Polska", "Mazowieckie", "Warszawa", "Kolbego 3");
		Address address3 = new Address("Polska", "Pomorskie", "Gdynia", "Berenta 10");
		Address address4 = new Address("Polska", "Œl¹sk", "Gliwice", "Chrobrego 3");
		Address address5 = new Address("Polska", "Œl¹sk", "Katowice", "Oriona 22");
		Address address6 = new Address("Polska", "Mazowieckie", "Warszawa", "Sikory 32");
		Address address7 = new Address("Polska", "Pomorskie", "Gdynia", "Roleska 13");
		Address address8 = new Address("Polska", "Œl¹sk", "Katowice", "Gliwicka 3");

		Courier courier = new Courier("Alek", "Makintosz", "400-500-444");
		Courier courier2 = new Courier("Thomas", "Roosvelt", "402-520-214");
		Courier courier3 = new Courier("Pawe³", "Root", "511-200-434");
		Courier courier4 = new Courier("Roman", "Flamenko", "677-655-333");

		
		Package pack = new Package(12.22f,"Interesuj¹ca paczka", Size.medium);
		Package pack2 = new Package(3.22f,"Niezbyt interesuj¹ca paczka", Size.small);
		Package pack3 = new Package(122.32f,"Bardzo interesuj¹ca paczka", Size.large);
		Package pack4 = new Package(11.22f,"Œrednio interesuj¹ca paczka", Size.medium);
		Package pack5 = new Package(31.22f,"W ogóle nieinteresuj¹ca paczka", Size.small);
		Package pack6 = new Package(100.32f,"Podejrzanie interesuj¹ca paczka", Size.large);
		
		// Assigning
		
//		pack.setSender(sender);
//		pack.setRecipient(recipient);
//		address2.addSender(sender);
//		address.addRecipient(recipient);
		
//		sender.setAddress(address);
//		recipient.setAddress(address);
		//pack.setSender(sender);
		//pack.setRecipient(recipient);
		//recipient.addPackage(pack);
		
		
		
		
		
		bean.createSender(sender);
		//bean.createRecipient(recipient);
		bean.createSender(sender2);
		bean.createRecipient(recipient2);
		bean.createSender(sender3);
		bean.createRecipient(recipient3);
		bean.createSender(sender4);
		bean.createRecipient(recipient4);
		bean.createSender(sender5);
		bean.createRecipient(recipient5);
		bean.createSender(sender6);
		bean.createRecipient(recipient6);

		bean.createCourier(courier);
		bean.createCourier(courier2);
		bean.createCourier(courier3);
		bean.createCourier(courier4);
		
		bean.createAddress(address);
		//bean.createAddress(address2);
		bean.createAddress(address3);
		bean.createAddress(address4);
		bean.createAddress(address5);
		bean.createAddress(address6);
		bean.createAddress(address7);
		bean.createAddress(address8);


		//bean.createPackage(pack);
		bean.createPackage(pack2);
		bean.createPackage(pack3);
		bean.createPackage(pack4);
		bean.createPackage(pack5);
		bean.createPackage(pack6);
		
		return "Samples inserted!";
	}
}
