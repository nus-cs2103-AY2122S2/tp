package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameFacultyRoleContainsAnyKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameFacultyRoleContainsAnyKeywordsPredicate firstPredicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                firstPredicateKeywordList);
        NameFacultyRoleContainsAnyKeywordsPredicate secondPredicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameFacultyRoleContainsAnyKeywordsPredicate firstPredicateCopy =
                new NameFacultyRoleContainsAnyKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

    }

    @Test
    public void test_facultyContainsKeywords_returnsTrue() {
        // One keyword
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Collections.singletonList("Computing"));
        assertTrue(predicate.test(new PersonBuilder().withFaculty("Computing").build()));

        // Multiple keywords
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("Medicine", "Others"));
        assertTrue(predicate.test(new PersonBuilder().withFaculty("Medicine").build()));
        assertTrue(predicate.test(new PersonBuilder().withFaculty("Others").build()));

        // Only one matching keyword, other non-faculty
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("Business", "Professor"));
        assertTrue(predicate.test(new PersonBuilder().withFaculty("Business").build()));

        // Mixed-case keywords
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("cOmpUtinG", "bUsinEsS"));
        assertTrue(predicate.test(new PersonBuilder().withFaculty("Computing").build()));
        assertTrue(predicate.test(new PersonBuilder().withFaculty("Business").build()));

    }

    @Test
    public void test_facultyDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withFaculty("Computing").build()));

        // Non-matching keyword
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("CHS"));
        assertFalse(predicate.test(new PersonBuilder().withFaculty("Business").build()));

    }

    @Test
    public void test_roleContainsKeywords_returnsTrue() {
        // One keyword
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Collections.singletonList("Professor"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Professor").build()));

        // Only one matching keyword
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("Tutor", "TA"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Tutor").build()));

        // Mixed-case keywords
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("oTHErS", "lEctureR"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Others").build()));
        assertTrue(predicate.test(new PersonBuilder().withRole("Lecturer").build()));
    }

    @Test
    public void test_roleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withRole("Professor").build()));

        // Non-matching keyword
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(Arrays.asList("TA"));
        assertFalse(predicate.test(new PersonBuilder().withRole("Researcher").build()));

    }

    @Test
    public void test_nameFacultyRoleContainKeywords_returnsTrue() {
        // Keywords match faculty only
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Arrays.asList("CDE"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("67891")
                .withEmail("doesNotMatch@gmail.com").withFaculty("CDE").withRole("Professor")
                .withTelegram("@doesNotMatch").build()));

        // Keywords match name only
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Arrays.asList("TA"));
        assertTrue(predicate.test(new PersonBuilder().withName("TA").withPhone("67891")
                .withEmail("doesNotMatch@gmail.com").withFaculty("Computing").withRole("Professor")
                .withTelegram("@doesNotMatch").build()));

        // Keywords match role only
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Arrays.asList("Admin"));
        assertTrue(predicate.test(new PersonBuilder().withName("Bob").withPhone("67891")
                .withEmail("doesNotMatch@gmail.com").withFaculty("Computing").withRole("Admin")
                .withTelegram("@doesNotMatch").build()));

        // Keywords match name, faculty and role
        predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Arrays.asList("Admin", "Bob", "Computing"));
        assertTrue(predicate.test(new PersonBuilder().withName("Bob").withPhone("67891")
                .withEmail("doesNotMatch@gmail.com").withFaculty("Computing").withRole("Admin")
                .withTelegram("@doesNotMatch").build()));
    }

    @Test
    public void test_nameFacultyRoleDoNotContainKeywords_returnsFalse() {
        // Keywords match phone, email and address, but does not match name, faculty and role
        NameFacultyRoleContainsAnyKeywordsPredicate predicate = new NameFacultyRoleContainsAnyKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Computing", "Professor", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withFaculty("Business").withRole("Admin")
                .withTelegram("@MainStreet").build()));
    }
}
