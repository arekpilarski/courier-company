package pl.kurs.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sender implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
	@XmlAttribute
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int senderId;
	private String firstName;
	private String lastName;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false, unique=true)
	private String phoneNumber;
	
	@ManyToOne
	private Address address;
	
	@OneToMany (mappedBy = "sender",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@XmlTransient
	private List<Package> sentPackages = new ArrayList<Package>();
	
	public Sender() {
		
	}

	public Sender(String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public void addPackage(Package pack) {
		sentPackages.add(pack);
		pack.setSender(this);
	}

	public void removePackage(Package pack) {
		sentPackages.remove(pack);
		pack.setSender(null);
	}
	
	
	public int getID() {
		return senderId;
	}

	public void setID(int sender_id) {
		this.senderId = sender_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		if(sameAsFormer(address)) return;
		Address oldAddress = this.address;
		this.address = address;
		if(oldAddress!=null)
			oldAddress.removeSender(this);
		if(address!=null)
			address.addSender(this);
	}
	
	private boolean sameAsFormer(Address newAddress) {
		if(this.address == newAddress) return true; //If both are nulls
		if((this.address == null) || (newAddress == null)) return false;
	    return this.address.equalss(newAddress);
	  }

	
	
}