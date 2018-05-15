package pl.kurs.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Package implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@XmlAttribute
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int packageId;
	private float weight;
	private String description;
	private Size size;
	private State state;
	
	@ManyToOne
	private Sender sender;
	
	@ManyToOne
	private Recipient recipient;
	
	@ManyToOne
	private Route route;
	
	public enum Size {
		small,
		medium,
		large
	}
	
	public enum State {
		//Waiting at start point.
		atStartPoint,
		//Package is on its way to the recipient.
		inTransit,
		//Recipient was not present, package awaiting in nearby post office/stand.
		waitingForPickUp,
		//Package was successfully delivered.
		delivered,
		//Recipient didn't pick up the package. It is returned to the sender.
		returned
	}

	public Package() {
		
	}
	
	public Package(float weight, String description, Size size) {
		super();
		this.weight = weight;
		this.description = description;
		this.size = size;
		this.state = State.atStartPoint;
	}

	
	
	public Sender getSender() {
		return sender;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void setSender(Sender sender) {
		if(this.sender != null) return;
		this.sender = sender;
		if(sender!= null)
			sender.addPackage(this);
	}
	
	public void setRecipient(Recipient recipient) {
		if(this.recipient != null) return;
		this.recipient = recipient;
		if(recipient!= null)
			recipient.addPackage(this);
	}

	public void setRoute(Route route) {
		if(this.route != null) return;
		this.route = route;
		if(route!= null)
			route.addPackage(this);
		
	}
	
}
