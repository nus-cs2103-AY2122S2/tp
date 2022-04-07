package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Unit and integration tests for the NameExistsPredicate class.
 */
class NameExistsPredicateTest {
    // Name of the default person in PersonBuilder is Amy Bee
    private static NameExistsPredicate predicate = new NameExistsPredicate(new Name("Amy Bee"));

    /**
     * Tests for the equals() method.
     */
    @Test
    public void equals() {
        NameExistsPredicate namePredicate1 = new NameExistsPredicate(new Name("Elle Meyer"));
        NameExistsPredicate namePredicate2 = new NameExistsPredicate(new Name("Elle MEYER"));
        NameExistsPredicate namePredicate3 = new NameExistsPredicate(new Name("Not Elle Meyer"));

        // same object -> returns true
        assertTrue(namePredicate1.equals(namePredicate1));

        // same values -> returns true
        NameExistsPredicate namePredicate1Copy = new NameExistsPredicate(new Name("Elle Meyer"));
        assertTrue(namePredicate1.equals(namePredicate1Copy));

        // different types -> returns false
        assertFalse(namePredicate1.equals(1));

        // null -> returns false
        assertFalse(namePredicate1.equals(null));

        // different casing -> returns false
        assertFalse(namePredicate1.equals(namePredicate2));

        // different person -> returns false
        assertFalse(namePredicate1.equals(namePredicate3));
    }

    /**
     * Tests the {@code test} method with a person with an identical name.
     */
    @Test
    void testSameNameSameCasing() {
        Person personToTest = new PersonBuilder().build();
        assertTrue(predicate.test(personToTest));
    }

    /**
     * Tests the {@code test} method with a person with the same name, but with different casing.
     */
    @Test
    void testSameNameDiffCasing() {
        Person personToTest = new PersonBuilder().withName("aMY bEE").build();
        assertTrue(predicate.test(personToTest));
    }

    /**
     * Tests the {@code test} method with a person with a different name.
     */
    @Test
    void testDiffName() {
        Person personToTest = new PersonBuilder().withName("Not Amy Bee").build();
        assertFalse(predicate.test(personToTest));
    }

}
