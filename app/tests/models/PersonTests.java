package models;

import models.contracts.Person;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTests {
    @Test
    public void testCreateMemberWithValidName() {
        Person member = new PersonImpl("JaneDoe") {
        };
        assertEquals("JaneDoe", member.getName());
    }

    @Test
    public void testCreateMemberWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PersonImpl("JD");
        });
    }
}
