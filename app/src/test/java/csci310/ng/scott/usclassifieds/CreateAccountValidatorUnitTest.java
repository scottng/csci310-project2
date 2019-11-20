package csci310.ng.scott.usclassifieds;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateAccountValidatorUnitTest {
    CreateAccountValidator cav;

    @Before
    public void initialize() {
        cav = new CreateAccountValidator();
    }

    @Test
    public void emptyName_isCorrect() {
        assertTrue(cav.emptyName(""));
        assertFalse(cav.emptyName("Yolanda Buenaventura"));
    }

    @Test
    public void emptyEmail_isCorrect() {
        assertTrue(cav.emptyEmail(""));
        assertFalse(cav.emptyEmail("yolanda@usc.edu"));
        assertFalse(cav.emptyEmail("yolanda"));
        assertFalse(cav.emptyEmail("@usc.edu"));
        assertFalse(cav.emptyEmail("@"));
    }

    @Test
    public void nonvalidEmail_isCorrect() {
        assertTrue(cav.nonvalidEmail("yolanda@gmail.com"));
        assertTrue(cav.nonvalidEmail("yolanda"));
        // assertTrue(cav.nonvalidEmail("@usc.edu"));
        assertFalse(cav.nonvalidEmail("yolanda@usc.edu"));
        assertFalse(cav.nonvalidEmail("y@usc.edu"));
        // assertFalse(cav.nonvalidEmail("hello@usc.eduhello"));
    }

    @Test
    public void emptyPassword_isCorrect() {
        assertTrue(cav.emptyPassword(""));
        assertFalse(cav.emptyPassword("a"));
        assertFalse(cav.emptyPassword("abcdefg"));
    }

    @Test
    public void nonvalidPassword_isCorrect() {
        assertTrue(cav.nonvalidPassword(""));
        assertTrue(cav.nonvalidPassword("abcde"));
        assertFalse(cav.nonvalidPassword("abcdef"));
    }

    @Test
    public void emptyConfirmPassword_isCorrect() {
        assertTrue(cav.emptyConfirmPassword(""));
        assertFalse(cav.emptyConfirmPassword("abc"));
        assertFalse(cav.emptyConfirmPassword("abcdef"));
    }

    @Test
    public void nonmatchingPasswords_isCorrect() {
        assertTrue(cav.nonmatchingPasswords("helloooo", "goodbye"));
        assertTrue(cav.nonmatchingPasswords("abcdef", "abcdeff"));
        assertFalse(cav.nonmatchingPasswords("abcdef", "abcdef"));
        assertFalse(cav.nonmatchingPasswords("Jjrb25sz", "Jjrb25sz"));
    }
}