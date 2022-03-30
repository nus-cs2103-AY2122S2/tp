package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.util.PersonContainsMembershipPredicate;
import seedu.address.testutil.PersonUtil;

public class PersonContainsMembershipPredicateTest {

    @Test
    public void equals() {
        PersonContainsMembershipPredicate firstPredicate =
                new PersonContainsMembershipPredicate("gold");
        PersonContainsMembershipPredicate secondPredicate =
                new PersonContainsMembershipPredicate("silver");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsMembershipPredicate firstPredicateCopy =
                new PersonContainsMembershipPredicate("gold");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personContainsMembership_returnsTrue() {
        // Gold
        PersonContainsMembershipPredicate predicate =
                new PersonContainsMembershipPredicate("GOLD");
        Person tempPerson = PersonUtil.AMY.addMembership(new Membership("GOLD"));
        assertTrue(predicate.test(tempPerson));

        //Silver
        predicate = new PersonContainsMembershipPredicate("SILVER");
        tempPerson = PersonUtil.AMY.addMembership(new Membership("SILVER"));
        assertTrue(predicate.test(tempPerson));

        // Bronze
        predicate = new PersonContainsMembershipPredicate("BRONZE");
        tempPerson = PersonUtil.AMY.addMembership(new Membership("BRONZE"));
        assertTrue(predicate.test(tempPerson));

        // All
        predicate = new PersonContainsMembershipPredicate("ALL");
        tempPerson = PersonUtil.AMY.addMembership(new Membership("SILVER"));
        assertTrue(predicate.test(tempPerson));
    }

    @Test
    public void test_nameDoesNotContainsMembership_returnsFalse() {
        // Gold
        PersonContainsMembershipPredicate predicate =
                new PersonContainsMembershipPredicate("GOLD");
        Person tempPerson = PersonUtil.AMY.addMembership(new Membership("SILVER"));
        assertFalse(predicate.test(tempPerson));

        //Silver
        predicate = new PersonContainsMembershipPredicate("SILVER");
        tempPerson = PersonUtil.AMY.addMembership(new Membership("BRONZE"));
        assertFalse(predicate.test(tempPerson));

        // Bronze
        predicate = new PersonContainsMembershipPredicate("BRONZE");
        tempPerson = PersonUtil.AMY.addMembership(new Membership("GOLD"));
        assertFalse(predicate.test(tempPerson));

        // All
        predicate = new PersonContainsMembershipPredicate("ALL");
        tempPerson = PersonUtil.AMY;
        assertFalse(predicate.test(tempPerson));

        // Random predicate
        predicate = new PersonContainsMembershipPredicate("random");
        tempPerson = PersonUtil.AMY.addMembership(new Membership("GOLD"));
        assertFalse(predicate.test(tempPerson));

        // No value
        predicate = new PersonContainsMembershipPredicate("");
        tempPerson = PersonUtil.AMY.addMembership(new Membership("GOLD"));
        assertFalse(predicate.test(tempPerson));
    }
}
