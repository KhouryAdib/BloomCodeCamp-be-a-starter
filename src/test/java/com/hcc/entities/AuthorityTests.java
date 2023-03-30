package com.hcc.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthorityTests {


    private Authority auth1;
    private Authority auth2;
    private Authority auth3;


    @BeforeEach
    public void setup() {
        auth1 = new Authority("ADMIN");
        auth2 = new Authority("LEARNER");
        auth3 = new Authority(auth2);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("ADMIN", auth1.getAuthority());
        assertNull(auth1.getUser());
        auth1.setAuthority("USER");
        assertEquals("USER", auth1.getAuthority());
        User user = new User();
        auth1.setUser(user);
        assertEquals(user, auth1.getUser());
    }

    @Test
    public void testEquals_SameObject_ShouldReturnTrue() {
        assertTrue(auth1.equals(auth1));
    }

    @Test
    public void testEquals_EqualObjects_ShouldReturnTrue() {
        assertTrue(auth2.equals(auth3));
    }

    @Test
    public void testEquals_DifferentObjects_ShouldReturnFalse() {
        assertFalse(auth1.equals(auth2));
    }

    @Test
    public void testHashCode_EqualObjects_ShouldReturnSameValue() {
        assertEquals(auth2.hashCode(), auth3.hashCode());
    }

    @Test
    public void testHashCode_DifferentObjects_ShouldReturnDifferentValue() {
        assertNotEquals(auth1.hashCode(), auth2.hashCode());
    }

    @Test
    public void testCompareTo_LessThanOther_ShouldReturnNegativeValue() {
        assertTrue(auth1.compareTo(auth2) < 0);
    }

    @Test
    public void testCompareTo_GreaterThanOther_ShouldReturnPositiveValue() {
        assertTrue(auth2.compareTo(auth1) > 0);
    }

    @Test
    public void testCompareTo_SameAsOther_ShouldReturnZero() {
        assertEquals(0, auth2.compareTo(auth3));
    }
}
