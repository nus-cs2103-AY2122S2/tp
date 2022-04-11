package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * A class that encapsulates the logic of exact word match between a person's attribute
 * and the description for that attribute (supplied by the user).
 */
public class ExactWordMatchPredicate implements Predicate<Person> {
    private final List<String> descriptions;
    private final Prefix prefix;

    /**
     * Constructor for ExactWordMatchPredicate
     * @param prefix Use to retrieve the corresponding {@link Person} attribute.
     * @param descriptions Descriptions that are tested against.
     */
    public ExactWordMatchPredicate(Prefix prefix, List<String> descriptions) {
        this.descriptions = descriptions;
        this.prefix = prefix;
    }

    /**
     * Conducts a case-insensitive check on the {@link seedu.address.model.person.Person Person}.
     * Checks if the Person's attribute (the attribute that corresponds to {@link #prefix})
     * has any word that matches exactly to any word in the given description.
     *
     * @param person Person to be tested.
     * @return True if person contains the word, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        if (PREFIX_TAG.equals(prefix)) {
            return person.getTags().stream()
                    .anyMatch(tag -> exactWordMatching(descriptions, tag.toString()));
        } else if (PREFIX_ADDRESS.equals(prefix)) {
            return exactWordMatching(descriptions, person.getAddress().toString());
        } else if (PREFIX_NAME.equals(prefix)) {
            return exactWordMatching(descriptions, person.getName().toString());
        } else if (PREFIX_EMAIL.equals(prefix)) {
            return exactWordMatching(descriptions, person.getEmail().toString());
        } else {
            // for phone prefix
            return exactWordMatching(StringUtil.removeSpaces(descriptions),
                    StringUtil.removeSpaces(person.getPhone().toString()));
        }
    }

    /**
     * Loops through the list of strings to see if there is any case-insensitive exact word matching between the
     * attribute and description.
     *
     * @param descriptions List of strings to be checked against. Guaranteed be non-null.
     * @param attribute Person attribute to be checked against. Guaranteed to be non-null.
     * @return True if any of the word in the description list matches any word in the person attribute.
     */
    private boolean exactWordMatching(List<String> descriptions, String attribute) {
        return descriptions.stream().anyMatch(description -> StringUtil.containsWordIgnoreCaseForTwoSentence(attribute,
                description));
    }
}
