package unq.po2.tpFinal;

import java.time.LocalDateTime;

public abstract class User {
	private String fullName;
	private String email;
	private String phoneNumber;
	private LocalDateTime createdOn;
	
	public User(String fullName, String email, String phoneNumber, LocalDateTime createdOn) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.createdOn = createdOn;
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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    
}
