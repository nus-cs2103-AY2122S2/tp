package seedu.address.model.person;

import java.util.List;

public class PhoneContainsKeywordsPredicateOr extends FieldContainsKeywordsPredicateOr {

    public PhoneContainsKeywordsPredicateOr(List<String> keywords) {
        super(keywords, person -> person.getPhone().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicateOr // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
