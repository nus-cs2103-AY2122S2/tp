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
                new PersonContainsMembershipPredicate(Membership.Tier.GOLD);
        PersonContainsMembershipPredicate secondPredicate =
                new PersonContainsMembershipPredicate(Membership.Tier.SILVER);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsMembershipPredicate firstPredicateCopy =
                new PersonContainsMembershipPredicate(Membership.Tier.GOLD);
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
                new PersonContainsMembershipPredicate(Membership.Tier.GOLD);
        Person tempPerson = PersonUtil.AMY.addMembership(new Membership("GOLD"));
        assertTrue(predicate.test(tempPerson));

        //Silver
        predicate = new PersonContainsMembershipPredicate(Membership.Tier.SILVER);
        tempPerson = PersonUtil.AMY.addMembership(new Membership("SILVER"));
        assertTrue(predicate.test(tempPerson));

        // Bronze
        predicate = new PersonContainsMembershipPredicate(Membership.Tier.BRONZE);
        tempPerson = PersonUtil.AMY.addMembership(new Membership("BRONZE"));
        assertTrue(predicate.test(tempPerson));

        // All
        predicate = new PersonContainsMembershipPredicate(Membership.Tier.ALL);
        tempPerson = PersonUtil.AMY.addMembership(new Membership("SILVER"));
        assertTrue(predicate.test(tempPerson));
    }

    @Test
    public void test_nameDoesNotContainsMembership_returnsFalse() {
        // Gold
        PersonContainsMembershipPredicate predicate =
                new PersonContainsMembershipPredicate(Membership.Tier.GOLD);
        Person tempPerson = PersonUtil.AMY.addMembership(new Membership("SILVER"));
        assertFalse(predicate.test(tempPerson));

        //Silver
        predicate = new PersonContainsMembershipPredicate(Membership.Tier.SILVER);
        tempPerson = PersonUtil.AMY.addMembership(new Membership("BRONZE"));
        assertFalse(predicate.test(tempPerson));

        // Bronze
        predicate = new PersonContainsMembershipPredicate(Membership.Tier.BRONZE);
        tempPerson = PersonUtil.AMY.addMembership(new Membership("GOLD"));
        assertFalse(predicate.test(tempPerson));

        // All
        predicate = new PersonContainsMembershipPredicate(Membership.Tier.ALL);
        tempPerson = PersonUtil.AMY;
        assertFalse(predicate.test(tempPerson));
    }
}
