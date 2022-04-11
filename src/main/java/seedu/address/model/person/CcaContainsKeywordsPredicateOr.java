package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Cca} matches any of the keywords given.
 */
public class CcaContainsKeywordsPredicateOr extends TagContainsKeywordsPredicateOr {
    public CcaContainsKeywordsPredicateOr(List<String> keywords) {
        super(keywords, person -> person.getCcaStrings());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CcaContainsKeywordsPredicateAnd // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
