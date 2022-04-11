package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicateOr extends FieldContainsKeywordsPredicateOr {

    public NameContainsKeywordsPredicateOr(List<String> keywords) {
        super(keywords, person -> person.getName().fullName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicateOr // instanceof handles nulls
                && super.equals(other)); // state check
    }

}
