package seedu.address.model.person;

import java.util.List;

public class AddressContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public AddressContainsKeywordsPredicate(List<String> keywords) {
        super(keywords, person -> person.getAddress().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
