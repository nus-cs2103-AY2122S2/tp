package manageezpz.model.person;

import static manageezpz.testutil.TypicalPersons.ALICE;
import static manageezpz.testutil.TypicalPersons.BENSON;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class PersonMultiplePredicateTest {
    // Equals method
    @Test
    void personMultiplePredicate_equalsOwnObject_true() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(List.of("Alice"), null, null);
        assertTrue(predicate1.equals(predicate1));
    }

    @Test
    void personalMultiplePredicate_equalsOtherObject_false() {
        Object object = new Object();
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(List.of("Alice"), null, null);
        assertFalse(predicate1.equals(object));
    }

    @Test
    void personalMultiplePredicate_equalsNull_false() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(List.of("Alice"), null, null);
        assertFalse(predicate1.equals(null));
    }

    @Test
    void personalMultiplePredicate_equalsWithSameNames_true() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(List.of("Alice"), null, null);
        PersonMultiplePredicate predicate2 = new PersonMultiplePredicate(List.of("Alice"), null, null);
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    void personalMultiplePredicate_equalsWithDifferentNames_false() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(List.of("Alice"), null, null);
        PersonMultiplePredicate predicate2 = new PersonMultiplePredicate(List.of("Benson"), null, null);
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    void personalMultiplePredicate_equalsWithSamePhone_true() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(null, ALICE.getPhone().toString(),
                null);
        PersonMultiplePredicate predicate2 = new PersonMultiplePredicate(null, ALICE.getPhone().toString(),
                null);
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    void personalMultiplePredicate_equalsWithDifferentPhone_false() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(null, ALICE.getPhone().toString(),
                null);
        PersonMultiplePredicate predicate2 = new PersonMultiplePredicate(null, BENSON.getPhone().toString(),
                null);
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    void personalMultiplePredicate_equalsWithSameEmail_true() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(null, null,
                ALICE.getEmail().toString());
        PersonMultiplePredicate predicate2 = new PersonMultiplePredicate(null, null,
                ALICE.getEmail().toString());
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    void personalMultiplePredicate_equalsWithDifferentEmail_false() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(null, null,
                ALICE.getEmail().toString());
        PersonMultiplePredicate predicate2 = new PersonMultiplePredicate(null, null,
                BENSON.getEmail().toString());
        assertFalse(predicate1.equals(predicate2));
    }


    // Test with person class
    @Test
    void personalMultiplePredicate_containsName_true() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(List.of("Alice"), null, null);
        assertTrue(predicate1.test(ALICE));

        // Test with different mix of caps
        PersonMultiplePredicate predicate2 = new PersonMultiplePredicate(List.of("AlIcE"), null, null);
        assertTrue(predicate2.test(ALICE));

        // More than 1 keyword
        PersonMultiplePredicate predicate3 = new PersonMultiplePredicate(List.of("alice", "benson"), null,
                null);
        assertTrue(predicate3.test(ALICE));
    }

    @Test
    void personalMultiplePredicate_doesNotContainName_false() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(List.of("Al1ce"), null, null);
        assertFalse(predicate1.test(ALICE));
    }

    @Test
    void personalMultiplePredicate_samePhone_true() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(null,
                ALICE.getPhone().toString(), null);
        assertTrue(predicate1.test(ALICE));
    }

    @Test
    void personalMultiplePredicate_differentPhone_false() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(null, BENSON.getPhone().toString(),
                null);
        assertFalse(predicate1.test(ALICE));
    }

    @Test
    void personalMultiplePredicate_sameEmail_true() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(null, null,
                ALICE.getEmail().toString());
        assertTrue(predicate1.test(ALICE));
    }

    @Test
    void personalMultiplePredicate_differentName_true() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(null, null,
                BENSON.getEmail().toString());
        assertFalse(predicate1.test(ALICE));
    }

    @Test
    void personalMultiplePredicate_multipleOptions_true() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(List.of("alice"),
                ALICE.getPhone().toString(), ALICE.getEmail().toString());
        assertTrue(predicate1.test(ALICE));
    }
}
