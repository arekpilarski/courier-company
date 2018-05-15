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
public class Route implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@XmlAttribute
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int routeId;
	
	@ManyToOne
	private Courier courier;
	
	@OneToMany(mappedBy = "route",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@XmlTransient
	List<Package> packagesToDeliver = new ArrayList<Package>();
	
	public void addPackage(Package pack) {
		packagesToDeliver.add(pack);
		pack.setRoute(this);
	}
	
	public void removePackage (Package pack) {
		packagesToDeliver.remove(pack);
		pack.setRoute(null);
	}
	
	public Courier getCourier() {
		return courier;
	}
	
	public void setCourier(Courier courier) {
		if(sameAsFormer(courier)) return;
		Courier oldCourier = this.courier;
		this.courier = courier;
		if(oldCourier != null)
			oldCourier.removeRoute(this);
		if(courier != null)
			courier.addRoute(this);
	}
	
	public boolean sameAsFormer(Courier newCourier) {
		if(this.courier == newCourier) return true;
		if(this.courier == null || newCourier == null) return false;
		return this.courier.equalss(newCourier);
	}
}
