package pl.kurs.Services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.kurs.Entities.Address;
import pl.kurs.Entities.Courier;
import pl.kurs.Entities.Package;
import pl.kurs.Entities.Recipient;
import pl.kurs.Entities.Route;
import pl.kurs.Entities.Sender;
import pl.kurs.Entities.Package.State;

@Singleton
public class CommonEJB {

	@PersistenceContext(name="testing")
	EntityManager em;
	
	// -------------TestSender-------------------------
	
	
	//POST ADD 
	public Sender createSender(Sender sender) {
			Sender existingSender;
			try {
				existingSender = (Sender) em.createQuery("SELECT s FROM Sender s WHERE s.email LIKE '"
						+ sender.getEmail() + "'").getSingleResult();
			} catch(Exception exc) {
				em.persist(sender);
				return sender;
			}
			return existingSender;
	}
	
//	//PUT UPDATE
//	public TestSender updateSender(TestSender sender) {
//		if (em.find(TestSender.class, sender.getID()) == null)
//			return null;
//		em.merge(sender);
//		return sender;
//	}
	
	// PUT -> ADD SENDER TO ADDRESS
	public Sender addSenderToAddress(int senderID, int addressID) {
		Sender sender = em.find(Sender.class, senderID);
		Address address = em.find(Address.class,addressID);
		if(sender == null || address == null) return null;
		sender.setAddress(address);
		em.merge(sender);
		return sender;
	}
	
	// GET ALL
	@SuppressWarnings("unchecked")
	public List<Sender> getAllSenders() {
		List<Sender> senders = em.createQuery("SELECT s FROM Sender s")
				.getResultList();
		return senders;	
	}

	// GET BY ID
	public Sender getSenderByID(int id) {
		return em.find(Sender.class, id);
	}


	//DELETE 
	public String removeSender(int id) {
		Sender senderToRemove = em.find(Sender.class, id);
		if(senderToRemove == null)
			return "Sender of given ID not found!";
		em.remove(senderToRemove);
		return "Successfully removed!";
	}

	// ---------------TestAddress------------------------------
	
	// GET ALL
	@SuppressWarnings("unchecked")
	public List<Address> getAllAddresses() {
		List<Address> addresses = em.createQuery("SELECT adr FROM Address adr").getResultList();
		return addresses;
	}
	
	
	//POST ADD - ADDS NEW OR RETURNS EXISTING ONE 
	public Address createAddress(Address address) {
		Address existingAddress;
		try {
			existingAddress = (Address) em.createQuery("SELECT adr FROM Address adr "
					+ "WHERE adr.country LIKE '" + address.getCountry() + "'"
							+ " AND adr.region LIKE '" + address.getRegion() + "'"
									+ " AND adr.city LIKE '" + address.getCity() + "'"
											+ " AND adr.streetAndNumber LIKE '" + address.getStreetAndNumber() + "'").getSingleResult();
		} catch (Exception exc) {
			em.persist(address);
			return address;
		}
		return existingAddress;
	}
	
	//UPDATE
	public String updateAddress(Address address) {
		Address addressToUpdate = em.find(Address.class, address.getID());
		if (addressToUpdate == null)
			return "Address has not been found in the database!";
		em.merge(addressToUpdate);
		return "Address has been updated!";
	}


	public Address getAddressByID(int addressID) {
		return em.find(Address.class, addressID);
	}

	
	//---------------------TestRecipient-----------------------
	
	//POST ADD 
		public Recipient createRecipient(Recipient recipient) {
				Recipient existingRecipient;
				try {
					existingRecipient = (Recipient) em.createQuery("SELECT r FROM Recipient r WHERE r.email LIKE '"
							+ recipient.getEmail() + "'").getSingleResult();
					
				} catch(Exception exc) {
					em.persist(recipient);
					return recipient;
				}
				return existingRecipient;
		}
	
//	public TestRecipient createRecipient(TestRecipient recipient) {
//		TestRecipient existingRecipient;
//		try {
//			existingRecipient = (TestRecipient) em.createQuery("SELECT r FROM Recipient r "
//					+ "INNER JOIN TestAddress adr ON r.recipientID = adr.recipientID "
//					+ "WHERE r.firstName LIKE '" + recipient.getFirstName() + "'"
//							+ " AND r.lastName LIKE '" + recipient.getLastName() + "'"
//									+ " AND adr."
//							)
//		}
//	}
		
//		//PUT UPDATE
//		public TestRecipient updateRecipient(TestRecipient recipient) {
//			if (em.find(TestRecipient.class, recipient.getID()) == null)
//				return null;
//			em.merge(recipient);
//			return recipient;
//		}
		
		// PUT -> ADD RECIPIENT TO ADDRESS
		public Recipient addRecipientToAddress(int recipientID, int addressID) {
			Recipient recipient = em.find(Recipient.class, recipientID);
			Address address = em.find(Address.class,addressID);
			if(recipient == null || address == null) return null;
			recipient.setAddress(address);
			em.merge(recipient);
			return recipient;
		}
		
		// GET ALL
		@SuppressWarnings("unchecked")
		public List<Recipient> getAllRecipients() {
			List<Recipient> recipient = em.createQuery("SELECT r FROM Recipient r")
					.getResultList();
			return recipient;	
		}

		// GET BY ID
		public Recipient getRecipientByID(int id) {
			return em.find(Recipient.class, id);
		}


		//DELETE 
		public String removeRecipient(int id) {
			Recipient recipientToRemove = em.find(Recipient.class, id);
			if(recipientToRemove == null)
				return "Recipient of given ID not found!";
			em.remove(recipientToRemove);
			return "Successfully removed!";
		}
		
		// -------------------TestCourier------------------------

		@SuppressWarnings("unchecked")
		public List<Courier> getAllCouriers() {
			return em.createQuery("SELECT c FROM Courier c").getResultList();
		}

		public Courier createCourier(Courier courier) {
			Courier existingCourier;
			try {
				existingCourier = (Courier) em.createQuery("SELECT c FROM Courier c WHERE c.phoneNumber LIKE '" +
						 courier.getPhoneNumber() + "'").getSingleResult();
				return existingCourier;
			} catch (Exception exc) {
				em.persist(courier);
				return courier;
			}
			
		}

		public Courier getCourierById(int courierID) {
			return em.find(Courier.class, courierID);
		}
		
		//--------------------TestPackage-----------------------------

		@SuppressWarnings("unchecked")
		public List<Package> getAllPackages() {
			return em.createQuery("SELECT p FROM Package p").getResultList();
		}
		
		public Package getPackageById(int packageID) {
			return em.find(Package.class, packageID);
		}

		
		public Package createPackage(Package pack) {
			Package existingPackage;
			try {
				existingPackage = (Package) em.createQuery("SELECT p FROM Package p WHERE p.weight =" + pack.getWeight() 
				+ " AND p.description LIKE '" + pack.getDescription() + "'").getSingleResult();
				
				if(existingPackage.getSender() != null || existingPackage.getRecipient() != null)
					return existingPackage;
			} catch (Exception exc) {
				em.persist(pack);
				return pack;
			}
			em.persist(pack);
			return pack;
			
		}
		
//		public Package createPackage(Package pack) {
//			em.persist(pack);
//			return pack;
//		}

		public Package updatePackageState(int packageID, State newState) {
			Package packageToUpdate = em.find(Package.class, packageID);
			if(packageToUpdate == null) return null;
			packageToUpdate.setState(newState);
			em.merge(packageToUpdate);
			return packageToUpdate;
		}

		public Package addSenderToPackage(int senderID, int packageID) {
			Sender sender = em.find(Sender.class, senderID);
			Package pack = em.find(Package.class, packageID);
			if(sender == null || pack == null) return null;
			pack.setSender(sender);
			em.merge(pack);
			return pack;
		}
			
		public Package addRecipientToPackage(int recipientID, int packageID) {
			Recipient recipient = em.find(Recipient.class, recipientID);
			Package pack = em.find(Package.class, packageID);
			if(recipient == null || pack == null) return null;
			pack.setRecipient(recipient);
			em.merge(pack);
			return pack;
			
		}
		
		//-----------------------TestRoute----------------------------

		@SuppressWarnings("unchecked")
		public List<Route> getAllRoutes() {
			return em.createQuery("SELECT ro FROM Route ro").getResultList();
		}
	
		public Route getRouteById(int routeID) {
			return em.find(Route.class, routeID);
		}
		
		public Route createRoute(Route route) {
			em.persist(route);
			return route;
		}

		public Route addCourierToRoute(int courierID, int routeID) {
			Courier courier = em.find(Courier.class, courierID);
			Route route = em.find(Route.class, routeID);
			if(courier == null || route == null) return null;
			route.setCourier(courier);
			em.merge(route);
			return route;
		}

		public List<Package> getSpecifiedPackages(String startPoint, String finishPoint) {
			List <Package> allPackages = getAllPackages();
			List<Package> specifiedPackages = new ArrayList<Package>();
			for (Package pack : allPackages) {
				if(pack.getSender().getAddress().getCity().equals(startPoint) 
						&& pack.getRecipient().getAddress().getCity().equals(finishPoint))
					specifiedPackages.add(pack);
			}
			return specifiedPackages;
		}
	
}
