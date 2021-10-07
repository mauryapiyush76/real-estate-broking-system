package com.casestudy.rebsa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "properties")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propertyId;

	@Column(name = "address")
	private String address;

	@Column(name = "property_type")
	private String propertyType;

	@Column(name = "floor_space")
	private String floorSpace;

	@Column(name = "city")
	private String city;

	@Column(name = "offer_type")
	private String offerType;

	@Column(name = "price")
	private double price;

	public Property(String address, String propertyType, String floorSpace, String city, String offerType,
			double price) {
		super();
		this.address = address;
		this.propertyType = propertyType;
		this.floorSpace = floorSpace;
		this.city = city;
		this.offerType = offerType;
		this.price = price;
	}

	public Property() {
		super();
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getFloorSpace() {
		return floorSpace;
	}

	public void setFloorSpace(String floorSpace) {
		this.floorSpace = floorSpace;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	@Column(name = "price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Property [address=" + address + ", propertyType=" + propertyType + ", floorSpace=" + floorSpace
				+ ", city=" + city + ", offerType=" + offerType + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((floorSpace == null) ? 0 : floorSpace.hashCode());
		result = prime * result + ((offerType == null) ? 0 : offerType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((propertyType == null) ? 0 : propertyType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (floorSpace == null) {
			if (other.floorSpace != null)
				return false;
		} else if (!floorSpace.equals(other.floorSpace))
			return false;
		if (offerType == null) {
			if (other.offerType != null)
				return false;
		} else if (!offerType.equals(other.offerType))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (propertyType == null) {
			if (other.propertyType != null)
				return false;
		} else if (!propertyType.equals(other.propertyType))
			return false;
		return true;
	}

}
