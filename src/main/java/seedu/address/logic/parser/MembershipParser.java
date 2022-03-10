package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Membership;

/**
 * Utility class for parsing a {@code String membership} into a {@code Membership}.
 */
public class MembershipParser {
    /**
     * Parses a {@code String membership} into a {@code Membership}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code membership} is invalid.
     */
    public static Membership parse(String membership) throws ParseException {
        requireNonNull(membership);
        String trimmed = membership.trim();
        return new Membership(trimmed);
    }

    /**
     * Parses {@code Collection<String> membership} into a {@code Set<Membership>}.
     */
    public static Set<Membership> parseCollection(Collection<String> memberships) throws ParseException {
        requireNonNull(memberships);
        final Set<Membership> membershipSet = new HashSet<>();
        for (String membershipName : memberships) {
            membershipSet.add(parse(membershipName));
        }
        return membershipSet;
    }
}
