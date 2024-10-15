package unq.po2.tpFinal;

public abstract class User {
	private String fullName;
	private String email;
	private String phoneNumber;
	
	public User(String fullName, String email, String phoneNumber) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
