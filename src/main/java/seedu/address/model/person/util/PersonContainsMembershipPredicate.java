package seedu.address.model.person.util;

import java.util.function.Predicate;

import seedu.address.model.person.Membership;
import seedu.address.model.person.Person;

/**
 * Tests that a person has a membership based on the tier provided
 */
public class PersonContainsMembershipPredicate implements Predicate<Person> {
    private final String tier;

    public PersonContainsMembershipPredicate(String tier) {
        this.tier = tier.toUpperCase();
    }

    @Override
    public boolean test(Person member) {
        if (!member.hasField(Membership.PREFIX)) {
            return false;
        }
        if (tier == "ALL") {
            return member.hasField(Membership.PREFIX);
        } else {
            return member.getMembership().getValue().toUpperCase().equals(tier);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsMembershipPredicate // instanceof handles nulls
                && tier.equals(((PersonContainsMembershipPredicate) other).tier)); // state check
    }

}
