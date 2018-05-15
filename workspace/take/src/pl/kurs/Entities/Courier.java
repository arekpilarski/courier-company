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
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Courier implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@XmlAttribute
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courierId;
	private String firstName;
	private String lastName;
	@Column(nullable=false, unique=true)
	private String phoneNumber;
	
	@OneToMany(mappedBy = "courier",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@XmlTransient
	private List<Route> routes = new ArrayList<Route>();
	
	public Courier () {
		
	}
	
	public Courier(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public void addRoute (Route route) {
		this.routes.add(route);
		route.setCourier(this);
	}
	
	public void removeRoute (Route route) {
		this.routes.remove(route);
		route.setCourier(null);
	}
	
	
	public int getCourierId() {
		return courierId;
	}
	public void setCourierId(int courierId) {
		this.courierId = courierId;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public boolean equalss(Courier otherCourier) {
		return this.phoneNumber.equals(otherCourier.getPhoneNumber());
	}
	
}
