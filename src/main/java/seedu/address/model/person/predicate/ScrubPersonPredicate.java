package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;


/**
 * Tests if a {@link seedu.address.model.person.Person Person}
 * matches any of the description given for scrubbing.
 */
public class ScrubPersonPredicate implements Predicate<Person> {
    private final ArgumentMultimap argMultimap;

    /**
     * Constructor for ScrubPersonPredicate.
     *
     * @param argMultimap Description to scrub a person by.
     */
    public ScrubPersonPredicate(ArgumentMultimap argMultimap) {
        this.argMultimap = argMultimap;
    }

    /**
     * Conducts a case-insensitive check on the {@link seedu.address.model.person.Person Person}.
     * Checks if the Person's attributes (the attribute that corresponds to {@link Prefix})
     * has any word that matches exactly to any word in the given description. Only three attributes
     * are allowed for scrubbing, and they are phone, email and tags. For email, a {@link DomainMatchPredicate} is
     * used, for phone and tags, {@link ExactWordMatchPredicate} is used.
     *
     * @param person Person to be tested.
     * @return True if person contains the word, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        List<Prefix> listOfPrefixes = argMultimap.getAllAvailablePrefix();
        return listOfPrefixes.stream().allMatch(prefix -> createPredicate(prefix).test(person));
    }

    /**
     * Creates predicate that allows partial matching for email attributes and exact matching for tag
     * attributes and special phone matching for phone attributes.
     *
     * @param prefix Prefix representing the attribute that is target for testing.
     * @return Appropriate predicate that meets the matching requirement of different attributes (see
     * {@link #test(Person)} for more information).
     */
    private Predicate<Person> createPredicate(Prefix prefix) {
        Predicate<Person> predicateToTestPersonAgainst;
        if (prefix.equals(PREFIX_EMAIL)) {
            predicateToTestPersonAgainst = new DomainMatchPredicate(argMultimap.getAllValues(prefix));
        } else {
            // When prefix is a t/.
            predicateToTestPersonAgainst = new ExactWordMatchPredicate(prefix, argMultimap.getAllValues(prefix));
        }
        return predicateToTestPersonAgainst;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScrubPersonPredicate // instanceof handles nulls
                && argMultimap.equals(((ScrubPersonPredicate) other).argMultimap)); // state check
    }
}
