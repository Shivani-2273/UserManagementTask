package model;


public class User {

	private int userId;
	private boolean isAdmin;
	private String firstName,lastName,email,password,contactNo,gender,birthDate,languages;
	
	//private String base64Image;

	
	public User() {}

	public User(int userId, boolean isAdmin, String firstName, String lastName, String email, String password,
			String contactNo, String gender, String birthDate, String languages) {
		super();
		this.userId = userId;
		this.isAdmin = isAdmin;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contactNo = contactNo;
		this.gender = gender;
		this.birthDate = birthDate;
		this.languages = languages;
	}

	public void setUserId(int userId) {
		this.userId=userId;
	}
	public int getUserId() {
		return userId;
	}
	
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password=password;
	} 
	public String getPassword() {
		return password;
	}
	
	public void setGender(String gender) {
		this.gender=gender;
	}
	public String getGender() {
		return gender;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo=contactNo;
	}
	public String getContactNo() {
		return contactNo;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin=isAdmin;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
	public void setLanguages(String languages) {
		this.languages=languages;
	}
	public String getLanguages() {
		return languages;
	}
		public void setBirthDate(String birthDate) {
		this.birthDate=birthDate;
	}
	public String getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", isAdmin=" + isAdmin + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", contactNo=" + contactNo + ", gender=" + gender
				+ ", birthDate=" + birthDate + ", languages=" + languages + "]";
	}
	
}
