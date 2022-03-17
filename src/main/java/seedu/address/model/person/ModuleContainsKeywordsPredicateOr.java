package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Module} matches any of the keywords given.
 */
public class ModuleContainsKeywordsPredicateOr extends TagContainsKeywordsPredicateOr {

    public ModuleContainsKeywordsPredicateOr(List<String> keywords) {
        super(keywords, person -> person.getModuleStrings());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternshipContainsKeywordsPredicateAnd // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
