package org.howard.edu.lsp.fexam.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for PasswordGeneratorService.
 * Tests singleton behavior, algorithm switching, and password generation requirements.
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    @Test
    public void checkInstanceNotNull() {
        // Verify that getInstance() returns a non-null instance
        assertNotNull(service);
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // Verify that both references point to the EXACT same object in memory
        assertSame(service, second);
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // Verify that calling generatePassword without setting an algorithm throws IllegalStateException
        assertThrows(IllegalStateException.class, () -> s.generatePassword(10));
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        // Verify the password has the correct length
        assertEquals(10, p.length());
        // Verify the password contains only digits (0-9)
        assertTrue(p.matches("[0-9]+"));
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        // Verify the password has the correct length
        assertEquals(12, p.length());
        // Verify the password contains only letters (A-Z, a-z) and digits (0-9)
        assertTrue(p.matches("[A-Za-z0-9]+"));
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        // Verify the password has the correct length
        assertEquals(8, p.length());
        // Verify the password contains only letters (A-Z, a-z)
        assertTrue(p.matches("[A-Za-z]+"));
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        // Test basic algorithm
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        assertTrue(p1.matches("[0-9]+"), "Basic algorithm should generate digits only");

        // Test letters algorithm
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        assertTrue(p2.matches("[A-Za-z]+"), "Letters algorithm should generate letters only");

        // Test enhanced algorithm
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        assertTrue(p3.matches("[A-Za-z0-9]+"), "Enhanced algorithm should generate letters and digits");
    }
}