package seedu.address.model.person.util;

import java.util.function.Predicate;

import seedu.address.model.person.Membership;
import seedu.address.model.person.Person;

/**
 * Tests that a person has a membership based on the tier provided
 */
public class PersonContainsMembershipPredicate implements Predicate<Person> {
    private final Membership.Tier tier;

    public PersonContainsMembershipPredicate(Membership.Tier tier) {
        this.tier = tier;
    }

    @Override
    public boolean test(Person member) {
        if (!member.hasField(Membership.PREFIX)) {
            return false;
        }
        if (tier == Membership.getTierFromString("ALL")) {
            return member.hasField(Membership.PREFIX);
        } else {
            return member.getMembership().getTier().equals(tier);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsMembershipPredicate // instanceof handles nulls
                && tier.equals(((PersonContainsMembershipPredicate) other).tier)); // state check
    }

}
