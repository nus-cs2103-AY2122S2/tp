package seedu.address.model.person;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Education} matches any of the keywords given.
 */
public class EducationContainsKeywordsPredicate extends TagContainsKeywordsPredicate{

    public EducationContainsKeywordsPredicate(List<String> keywords) {
        super(keywords, person -> person.getEducationStrings());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EducationContainsKeywordsPredicate // instanceof handles nulls
                && super.equals(other)); // state check
    }


}
