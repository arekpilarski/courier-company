package pl.kurs.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@XmlAttribute
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private String country;
	private String region;
	private String city;
	private String streetAndNumber;
	
	@OneToMany(mappedBy = "address",
			cascade = CascadeType.ALL,
			orphanRemoval=true,
			fetch = FetchType.LAZY)
	@XmlTransient
	private List<Sender> sendersLivingHere = new ArrayList<Sender>();
	
	@OneToMany(mappedBy = "address",
			cascade = CascadeType.ALL,
			orphanRemoval=true,
			fetch = FetchType.LAZY)	
	@XmlTransient
	private List<Recipient> recipientsLivingHere = new ArrayList<Recipient>();
	
	
	public Address() {
		
	}
	
	public Address(String country, String region, String city, String streetAndNumber) {
		super();
		this.country = country;
		this.region = region;
		this.city = city;
		this.streetAndNumber = streetAndNumber;
	}
	
	public void addSender(Sender sender) {
		sendersLivingHere.add(sender);
		sender.setAddress(this);
	}
	
	public void removeSender(Sender sender) {
		sendersLivingHere.remove(sender);
		sender.setAddress(null);
	}
	
	public void addRecipient(Recipient recipient) {
		recipientsLivingHere.add(recipient);
		recipient.setAddress(this);
	}
	
	public void removeRecipient(Recipient recipient) {
		recipientsLivingHere.remove(recipient);
		recipient.setAddress(null);
	}
	
	

	public int getID() {
		return addressId;
	}
	public void setID(int address_id) {
		this.addressId = address_id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreetAndNumber() {
		return streetAndNumber;
	}
	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}

	public List<Sender> getSendersLivingHere() {
		return sendersLivingHere;
	}

	public void setSendersLivingHere(List<Sender> sendersLivingHere) {
		this.sendersLivingHere = sendersLivingHere;
	}
	

	public boolean equalss(Address compareAddress) {
		if(compareAddress.getCity().equals(this.getCity()) 
				&& compareAddress.getCountry().equals(this.getCountry())
				&& compareAddress.getRegion().equals(this.getRegion())
				&& compareAddress.getStreetAndNumber().equals(this.getStreetAndNumber()))
			return true;
		return false;
	}
	
	
}
