package com.usermanagement.model;

import java.io.Serializable;


public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	private int userId;
	private String addressLine, city, state, pin,addressId, is_default,removeAddressId;

	public Address() {}
	
	
	public Address(int userId, String addressLine, String city, String state, String pin, String addressId,
			String is_default, String removeAddressId) {
		super();
		this.userId = userId;
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.addressId = addressId;
		this.is_default = is_default;
		this.removeAddressId = removeAddressId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setIsDefault(String is_default) {
		this.is_default = is_default;
	}

	public String getIsDefault() {
		return is_default;
	}
	public void setRemoveAddressId(String removeAddressId) {
		this.removeAddressId=removeAddressId;
		
	}
	public String getRemoveAddressId() {
		return removeAddressId;
	}


	@Override
	public String toString() {
		return "Address [userId=" + userId + ", addressLine=" + addressLine + ", city=" + city + ", state=" + state
				+ ", pin=" + pin + ", addressId=" + addressId + ", is_default=" + is_default + ", removeAddressId="
				+ removeAddressId + "]";
	}
	
	

	

	
	

}
