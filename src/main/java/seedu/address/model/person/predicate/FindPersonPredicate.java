package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Tests if a {@link seedu.address.model.person.Person Person}
 * matches any of the description given.
 */
public class FindPersonPredicate implements Predicate<Person> {

    /**
     * Descriptor from {@link FindCommandParser}.
     */
    private final ArgumentMultimap descriptor;

    /**
     * Constructs Predicate function.
     *
     * @param descriptor Description to search a person by.
     */
    public FindPersonPredicate(ArgumentMultimap descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Conducts a case-insensitive check on the {@link seedu.address.model.person.Person Person}.
     * Checks if the Person's attributes (the attribute that corresponds to {@link Prefix}) matches any of
     * the following criteria.
     *
     * Tag uses exact word check, where "find t/redherring" would only match with the name "redherring".
     *
     * Contacted date uses a special check where "find c/1" would result in choosing person that had not been
     * contacted for more than 1 day ago.
     *
     * Memo uses a partial word check where "find m/memo" would result in matching with person that has the memo
     * "This is a Memo".
     *
     * The other remaining attributes (phone, address, email, name) uses a partial word match where "find
     * n/kay" would result in a match with "kay", "kaylee", "kayla", "okay" and "pokaya".
     * Note, using the same prefix more than once in a single query is the same as running the find command more than
     * once and combining the outcome of both filtered result into a single output list. For example, "find
     * n/benedict n/alex" would match with anybody that has the name "alex" or "benedict".
     *
     * @param person Person to be tested.
     * @return True if person contains the word, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        List<Prefix> prefixes = descriptor.getAllAvailablePrefix();
        for (Prefix prefix : prefixes) {
            if (!testPersonAttribute(person, prefix)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the attribute that corresponds with the attribute matches with the predicate.
     * @param person Person to be tested.
     * @param attribute The prefix that identifies which attribute of the person should the predicate be
     *               testing against.
     * @return True if the person's attribute passes the test, false otherwise.
     */
    private boolean testPersonAttribute(Person person, Prefix attribute) {
        Predicate<Person> predicateToTestAgainst;
        if (attribute.equals(PREFIX_CONTACTED_DATE)) {
            predicateToTestAgainst = new ContactedDateMatchPredicate(descriptor);
        } else if (attribute.equals(PREFIX_TAG)) {
            predicateToTestAgainst = new ExactWordMatchPredicate(attribute,
                    descriptor.getAllValues(attribute));
        } else if (attribute.equals(PREFIX_MEMO)) {
            predicateToTestAgainst = new MemoMatchPredicate(descriptor.getAllValues(attribute));
        } else {
            predicateToTestAgainst = new PartialWordMatchPredicate(attribute,
                    descriptor.getAllValues(attribute));
        }
        return predicateToTestAgainst.test(person);
    }

    /**
     * Equal method to check if two {@link FindPersonPredicate} are equal.
     *
     * @param other Other PersonContainsKeywordsPredicate object.
     * @return True if both {@link FindPersonPredicate} object are the same, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonPredicate // instanceof handles nulls
                && descriptor.equals(((FindPersonPredicate) other).descriptor)); // state check
    }
}
