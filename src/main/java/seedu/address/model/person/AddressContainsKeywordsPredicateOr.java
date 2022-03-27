package seedu.address.model.person;

import java.util.List;

public class AddressContainsKeywordsPredicateOr extends FieldContainsKeywordsPredicateOr {

    public AddressContainsKeywordsPredicateOr(List<String> keywords) {
        super(keywords, person -> person.getAddress().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicateOr // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
