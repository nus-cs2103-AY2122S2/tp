package seedu.address.model.person;

import java.util.List;

public class EmailContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        super(keywords, person -> person.getEmail().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && super.equals(other)); // state check
    }

}
