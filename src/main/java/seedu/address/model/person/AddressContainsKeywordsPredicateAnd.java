package seedu.address.model.person;

import java.util.List;

public class AddressContainsKeywordsPredicateAnd extends FieldContainsKeywordsPredicateAnd {

    public AddressContainsKeywordsPredicateAnd(List<String> keywords) {
        super(keywords, person -> person.getAddress().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicateAnd // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
