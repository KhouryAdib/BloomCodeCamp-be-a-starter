package com.hcc.entities;

import com.hcc.enums.AuthorityEnum;
import com.hcc.exceptions.NullAttributeException;
import com.hcc.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTests {

    /*
    Things to test for
     */


    private User user;

    @BeforeEach
    public void setup() {
        List<Authority> authorities = Arrays.asList(new Authority("ADMIN" , new User()), new Authority("LEARNER", new User()));
        user = new User(1L, new Date(), "testuser", "testpassword", authorities);
    }

    @Test
    public void testGetters_ValidUser_ShouldReturnNotNull() {
        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals(new Date().getTime(), user.getCohortStartDate().getTime(), 1000);
        Assertions.assertEquals("testuser", user.getUsername());
        Assertions.assertEquals("testpassword", user.getPassword());
        Assertions.assertEquals(2, user.getAuthorities().size());
    }

    @Test
    public void testToString_ValidUser_ShouldParametersAsStrings() {
        //given
        String expected = "User{id=1, cohortStartDate=" + user.getCohortStartDate() +
                ", username='testuser', password='testpassword', authorities=[LEARNER, ADMIN]}";

        //then
        Assertions.assertEquals(expected, user.toString());
    }

    @Test
    public void testGetters_InvalidUser_ShouldThrowNullAttributeException(){
        //given
        User user = new User();

        //then
        Assertions.assertThrows(NullAttributeException.class, () -> {
            user.getId();
        });

        Assertions.assertThrows(NullAttributeException.class, () -> {
            user.getCohortStartDate();
        });

        Assertions.assertThrows(NullAttributeException.class, () -> {
            user.getUsername();
        });

        Assertions.assertThrows(NullAttributeException.class, () -> {
            user.getPassword();
        });

        Assertions.assertThrows(NullAttributeException.class, () -> {
            user.getAuthorities();
        });
    }

    @Test
    public void testSetters_ValidInput_ShouldUpdateObjectFields(){
        // given
        User user = new User();
        Date expectedCohortStartDate = new Date();
        String expectedUsername = "testuser";
        String expectedPassword = "testpassword";
        List<Authority> expectedAuthorities = Arrays.asList(new Authority("ADMIN", new User()), new Authority("LEARNER", new User()));

        // when
        user.setCohortStartDate(expectedCohortStartDate);
        user.setUsername(expectedUsername);
        user.setPassword(expectedPassword);
        user.setAuthorities(expectedAuthorities);

        // then
        Assertions.assertEquals(expectedCohortStartDate, user.getCohortStartDate());
        Assertions.assertEquals(expectedUsername, user.getUsername());
        Assertions.assertEquals(expectedPassword, user.getPassword());
        Assertions.assertEquals(expectedAuthorities.size(), user.getAuthorities().size());
        for (int i = 0; i < expectedAuthorities.size(); i++) {
            Assertions.assertEquals(expectedAuthorities.get(i), user.getAuthoritiesStrings().get(i));
        }
    }

    @Test
    public void testSetters_InvalidInput_ShouldThrowIllegalArgumentException(){
        User user = new User();

        // Test for invalid username value
        assertThrows(IllegalArgumentException.class, () -> user.setUsername(""));

        // Test for invalid password value
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(""));

    }

    @Test
    public void testEquals_SameObject_ShouldReturnTrue(){
        User user = new User();
        assertTrue(user.equals(user));
    }

    @Test
    public void testEquals_DifferentObjectsSameFields_ShouldReturnTrue(){
        User user1 = new User();
        user1.setId(Long.valueOf(0));
        user1.setCohortStartDate(new Date(1234567890L));
        user1.setUsername("testUser");
        user1.setPassword("password");
        user1.setAuthorities(Arrays.asList(new Authority("ADMIN_ROLE", new User()), new Authority("LEARNER_ROLE", new User())));

        User user2 = new User();
        user2.setId(0L);
        user2.setCohortStartDate(new Date(1234567890L));
        user2.setUsername("testUser");
        user2.setPassword("password");
        user2.setAuthorities(Arrays.asList(new Authority("ADMIN_ROLE", new User()), new Authority("LEARNER_ROLE", new User())));

        assertTrue(user1.equals(user2));
    }

    @Test
    public void testEquals_DifferentObjectsDifferentFields_ShouldReturnFalse(){
        User user1 = new User();
        user1.setId(0L);
        user1.setCohortStartDate(new Date(1234567890L));
        user1.setUsername("testUser1");
        user1.setPassword("password");
        user1.setAuthorities(Arrays.asList(new Authority("LEARNER_ROLE", new User())));

        User user2 = new User();
        user2.setId(1L);
        user2.setCohortStartDate(new Date(9876543210L));
        user2.setUsername("testUser2");
        user2.setPassword("password123");
        user2.setAuthorities(Arrays.asList(new Authority("ADMIN_ROLE", new User())));

        assertFalse(user1.equals(user2));
    }

    @Test
    public void testSetCohortStartDate_NullInput_ShouldThrowNullAttributeException() {
        try {
            User user = new User();
            user.setCohortStartDate(null);
            fail("Expected NullAttributeException to be thrown");
        } catch (NullAttributeException e) {
            assertEquals("User.setCohortStartDate: cohortStartDate is null", e.getMessage());
        }
    }

    @Test
    public void testSetUsername_NullInput_ShouldThrowNullAttributeException() {
        try {
            User user = new User();
            user.setUsername(null);
            fail("Expected NullAttributeException to be thrown");
        } catch (NullAttributeException e) {
            assertEquals("User.setUsername: username is null", e.getMessage());
        }
    }

    @Test
    public void testSetPassword_NullInput_ShouldThrowNullAttributeException() {
        try {
            User user = new User();
            user.setPassword(null);
            fail("Expected NullAttributeException to be thrown");
        } catch (NullAttributeException e) {
            assertEquals("User.setPassword: password is null", e.getMessage());
        }
    }

    @Test
    public void testSetAuthorities_NullInput_ShouldThrowNullAttributeException() {
        try {
            User user = new User();
            user.setAuthorities(null);
            fail("Expected NullAttributeException to be thrown");
        } catch (NullAttributeException e) {
            assertEquals("User.setAuthorities: authStrList is null", e.getMessage());
        }
    }

}