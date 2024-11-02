package unq.po2.tpFinal;

import java.time.LocalDateTime;

public class Tenant extends User {

	public Tenant(String fullName, String email, String phoneNumber, LocalDateTime createdOn) {
		super(fullName, email, phoneNumber, createdOn);
	}

}
