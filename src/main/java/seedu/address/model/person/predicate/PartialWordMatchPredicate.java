package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * A class that encapsulates the logic of partial word match between a Person's attribute and the
 * description for that attribute (supplied by the user).
 */
public class PartialWordMatchPredicate implements Predicate<Person> {
    private final List<String> descriptions;
    private final Prefix prefix;

    /**
     * Constructor for PartialWordMatchPredicate
     * @param prefix Use to retrieve the corresponding {@link Person} attribute.
     * @param descriptions Descriptions that are tested against.
     */
    public PartialWordMatchPredicate(Prefix prefix, List<String> descriptions) {
        this.descriptions = descriptions;
        this.prefix = prefix;
    }

    /**
     * Conducts a case-insensitive partial match on both the attribute and description.
     * For example: "memo" would match with "This is a memo".
     *
     * @param attribute Person attribute that is tested.
     * @param descriptions Description that is supplied by the user.
     * @return True if there is a partial match between attribute and description, false otherwise.
     */
    private boolean caseInsensitivePartialMatch(String attribute, List<String> descriptions) {
        return descriptions.stream().anyMatch(description ->
            attribute.toLowerCase().contains(description.toLowerCase()));
    }

    /**
     * Conducts a case-insensitive check on the {@link seedu.address.model.person.Person Person}.
     * Checks if the Person's attribute (the attribute that corresponds to {@link #prefix})
     * has any word that partially matches the given description.
     *
     * @param person Person to be tested.
     * @return True if person contains the word, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        if (PREFIX_EMAIL.equals(prefix)) {
            return caseInsensitivePartialMatch(person.getEmail().toString(), descriptions);
        } else if (PREFIX_NAME.equals(prefix)) {
            return caseInsensitivePartialMatch(person.getName().toString(), descriptions);
        } else if (PREFIX_PHONE.equals(prefix)) {
            return caseInsensitivePartialMatch(StringUtil.removeSpaces(person.getPhone().toString()),
                    StringUtil.removeSpaces(descriptions));
        } else if (PREFIX_TAG.equals(prefix)) {
            return person.getTags().stream().anyMatch(tag -> caseInsensitivePartialMatch(tag.toString(), descriptions));
        } else {
            return caseInsensitivePartialMatch(person.getAddress().toString(), descriptions);
        }
    }
}
