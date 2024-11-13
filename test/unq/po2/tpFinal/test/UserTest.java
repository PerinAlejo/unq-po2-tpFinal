package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import unq.po2.tpFinal.domain.User;

public class UserTest {

    private User user;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdOn;

    @BeforeEach
    public void setUp() {
        fullName = "Juan De los Palotes";
        email = "usuario@falso.com";
        phoneNumber = "123-456-7890";
        createdOn = LocalDateTime.of(2023, 1, 1, 10, 0);
        
        user = new User(fullName, email, phoneNumber, createdOn) {};
    }

    @Test
    public void testGetFullName() {
        assertEquals(fullName, user.getFullName());
    }

    @Test
    public void testGetEmail() {
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals(phoneNumber, user.getPhoneNumber());
    }

    @Test
    public void testGetCreatedOn() {
        assertEquals(createdOn, user.getCreatedOn());
    }
}
